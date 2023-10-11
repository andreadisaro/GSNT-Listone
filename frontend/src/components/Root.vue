<script setup>
//import { useRouter } from "vue-router";
import { useDataStore } from "../store/useDataStore";
import { APISettings } from "../api/config.js";
import { useLoadingStore } from "../store/useLoadingStore";
import Item from "./Item.vue";
import ItemJournalist from "./ItemJournalist.vue";
const loadingStore = useLoadingStore();
const dataStore = useDataStore();
//const router = useRouter();
const errorFunction = () => {
  alert("Errore di connessione, riprova più tardi");
};
loadingStore.addLoading();
dataStore.loadData();
fetch("/public/api/event", {
  method: "GET",
  headers: APISettings.headers
}).then(function (response) {
  if (response.status != 200) {
    console.error(response.status);
    errorFunction();
  } else {
    response.json().then((res) => {
      //console.log(res);
      dataStore.setEventTitle(res.title);
      dataStore.setEventLogo(res.logo);
    });
  }
}).finally(() => {
  loadingStore.removeLoading();
});
loadingStore.addLoading();
fetch("/public/api/items", {
  method: "GET",
  headers: APISettings.headers
}).then(function (response) {
  if (response.status != 200) {
    console.error(response.status);
    errorFunction();
  } else {
    response.json().then((res) => {
      console.log(res);
      dataStore.setItems(res);
    });
  }
}).finally(() => {
  loadingStore.removeLoading();
});
fetch("/public/api/journalists", {
  method: "GET",
  headers: APISettings.headers
}).then(function (response) {
  if (response.status != 200) {
    console.error(response.status);
    errorFunction();
  } else {
    response.json().then((res) => {
      console.log(res);
      dataStore.setJournalistsDays(res);
    });
  }
}).finally(() => {
  loadingStore.removeLoading();
});
</script>

<template>
  <div class="w-full text-center items-stretch flex flex-col p-8 pt-20 min-h-[95vh]">
    <div class="w-full items-center flex-1">
      <div v-for="(category, catI, i) in dataStore.items" :key="catI">
        <div class="flex font-extrabold" :class="i > 0 ? 'my-8' : ''">{{ catI }}</div>
        <div v-for="(editor, editorI, j) in category" :key="editorI" class="flex-1" :class="j > 0 ? 'mt-4' : ''">
          <div class="flex flex-row">
            <div class="w-6">
              A
            </div>
            <div class="w-6">
              P
            </div>
            <div class="w-6">
              {{ editor.length && editor[0].bookType ? 'L' : 'B' }}
            </div>
            <div class="font-semibold flex-1">{{ editorI }}</div>
          </div>
          <div v-for="item in editor" :key="'item' + item.id" class="flex-1">
            <Item :item="item" />
          </div>
        </div>
      </div>
      <div>
        <div class="flex font-extrabold my-8">{{ $t('Redazione') }}</div>
        <div class="flex flex-row">
          <div class="w-6" v-for="day in dataStore.days" :key="day + 'title'">
            {{ day }}
          </div>
          <div class="font-semibold flex-1">&nbsp;</div>
        </div>
        <div v-for="(jd, jdI) in dataStore.journalistsDays" :key="jdI" class="flex-1">
          <ItemJournalist :item="jd" :journalist="jdI" />
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped></style>
