<script setup>
import { useDataStore } from "../store/useDataStore";
import { APISettings } from "../api/config.js";
import { useLoadingStore } from "../store/useLoadingStore";
import { useModalStore } from "../store/useModalStore";
import { useRouter } from "vue-router";
import { ref, watch } from "vue";
import Menu from "./Menu.vue";
const loadingStore = useLoadingStore();
const modalStore = useModalStore();
const dataStore = useDataStore();
const router = useRouter();
const errorFunction = () => {
  modalStore.showInfoModal(
    "Errore",
    "Errore di connessione, riprova più tardi"
  );
};
const votes = ref([]);
const _votes = ref({});
if (Object.entries(dataStore.items).length == 0) loadingStore.addLoading();
dataStore.setItems(errorFunction);
loadingStore.addLoading();
fetch(`/be/public/api/votes`, {
  method: "GET",
  headers: APISettings.headers,
}).then(function (response) {
  if (response.status != 200) {
    console.error(response.status);
    errorFunction();
    router.push("/");
  } else {
    response.json().then(async (resVotes) => {
      _votes.value = resVotes;
      while (dataStore.items.length == 0) {
        await new Promise((resolve) => setTimeout(resolve, 100));
      }
      updateVotes(resVotes);
    });
  }
}).finally(() => {
  loadingStore.removeLoading();
});

const updateVotes = (resVotes) => {
  console.log("resVotes",resVotes);
  let votesTmp = [];
  for (const vote of resVotes) {
    if (votesTmp[vote.id.idItem]) {
      votesTmp[vote.id.idItem] = votesTmp[vote.id.idItem] + 1;
    } else {
      votesTmp[vote.id.idItem] = 1;
    }
  }
  console.log("votesTmp",votesTmp);
  let ret = {};
  for (const catI in dataStore.items) {
    ret[catI] = [];
    for (const [index, [editorI, editor]] of Object.entries(
      Object.entries(dataStore.items[catI])
    )) {
      for (const item of editor) {
        if (votesTmp[item.id]) {
          ret[catI][item.id] = { ...item, votes: votesTmp[item.id] };
        }
      }
    }
  }
  for (const [cat, catI] of Object.entries(ret)) {
    ret[cat] = ret[cat].sort((a, b) => {
      if (a.votes < b.votes) {
        return 1;
      }
      if (a.votes > b.votes) {
        return -1;
      }
      if (a.name < b.name) {
        return -1;
      }
      if (a.name > b.name) {
        return 1;
      }
      return 0;
    });
  }
  console.log("updateVotes", ret);
  votes.value = ret;
}

watch(() => dataStore.items, () => {
  updateVotes(_votes.value);
});
</script>

<template>
  <Menu v-if="dataStore.showMenu" />
  <div v-else>
    <div
      class="w-full text-center items-stretch flex flex-col p-8 pt-20 min-h-[95vh]"
    >
      <div class="w-full items-center flex-1">
        <div
          v-for="(category, catI, i) in votes"
          style="scroll-margin-top: 5rem"
          :key="catI"
        >
          <div
            class="flex font-extrabold justify-end titoloRosso text-xl"
            :class="i > 0 ? 'my-8' : ''"
          >
            {{ catI }}
          </div>
          <template
            v-for="(item, itemI, j) in category"
            :key="itemI"
          >
            <div
              v-if="item"
              class="flex-1"
              :class="j > 0 ? 'mt-4' : ''"
            >
              <div class="flex">
                <div class="flex font-bold justify-start text-xl">
                  {{ item.name }}
                </div>
                <div class="flex-1"></div>
                <div class="flex font-extrabold text-end text-xl">
                  {{ item.votes }}
                </div>
              </div>
            </div>
          </template>
        </div>
      </div>
    </div>      
  </div>
</template>

<style scoped>
</style>
