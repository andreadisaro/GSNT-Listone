<script setup>
import { ref, watch } from "vue";
import { useDataStore } from "../store/useDataStore";
import Item from "./Item.vue";
import ItemJournalist from "./ItemJournalist.vue";
import SvgIcon from "./SvgIcon.vue";
const dataStore = useDataStore();
const catRefs = ref([]);
const redazioneRef = ref(null);
const legendaRef = ref(null);
watch(
  () => dataStore.goToRef,
  (newVal) => {
    if (newVal) {
      if (newVal === "redazione") {
        redazioneRef.value.scrollIntoView({
          behavior: "smooth",
          block: "start",
        });
        return;
      } else if (newVal === "legenda") {
        legendaRef.value.scrollIntoView({
          behavior: "smooth",
          block: "start",
        });
        return;
      }
      catRefs.value[newVal].scrollIntoView({
        behavior: "smooth",
        block: "start",
      });
    }
  }
);
</script>

<template>
  <div
    class="w-full text-center items-stretch flex flex-col p-8 pt-20 min-h-[95vh]"
  >
    <div class="w-full items-center flex-1">
      <div
        v-for="(category, catI, i) in dataStore.items"
        style="scroll-margin-top: 5rem"
        :key="catI"
        :ref="
          (el) => {
            catRefs[i] = el;
          }
        "
      >
        <div
          class="flex font-extrabold justify-end titoloRosso text-xl"
          :class="i > 0 ? 'my-8' : ''"
        >
          {{ catI }}
        </div>
        <div
          v-for="(editor, editorI, j) in dataStore.editors"
          :key="editorI"
          class="flex-1"
          :class="j > 0 ? 'mt-4' : ''"
        >
          <template v-if="category[editor.id]">
            <div class="flex flex-row space-x-2">
              <div class="w-6 sfondoVerdeSemiTrasparente py-1">
                <SvgIcon name="look" class="h-6 w-6 my-auto" />
              </div>
              <div class="w-6 sfondoVerdeSemiTrasparente py-1">
                <SvgIcon
                  name="buy"
                  class="h-6 w-6 my-auto"
                  v-if="category[editor.id].length && category[editor.id][0].bookType"
                />
                <SvgIcon name="try" class="h-6 w-6 my-auto" v-else />
              </div>
              <div class="w-6 sfondoVerdeSemiTrasparente py-1">
                <img
                  src="../assets/book-24.png"
                  class="my-auto"
                  v-if="category[editor.id].length && category[editor.id][0].bookType"
                />
                <SvgIcon name="buy" class="h-6 w-6 my-auto" v-else />
              </div>
              <div class="font-semibold flex-1 pt-1">
                {{ editor.name || editorI }}
              </div>
            </div>
            <div v-for="item in category[editor.id]" :key="'item' + item.id" class="flex-1">
              <Item :item="item" />
            </div>
          </template>
        </div>
      </div>
      <div>
        <div
          class="flex font-extrabold justify-end titoloRosso text-xl my-8"
          ref="redazioneRef"
        >
          {{ $t("REDAZIONE") }}
        </div>
        <div class="flex flex-row space-x-2">
          <div
            class="w-6 font-bold sfondoVerdeSemiTrasparente"
            v-for="day in dataStore.days"
            :key="day + 'title'"
          >
            {{ day }}
          </div>
        </div>
        <div
          v-for="(jd, jdI) in dataStore.journalistsDays"
          :key="jdI"
          class="flex-1"
        >
          <ItemJournalist :item="jd" :journalist="jdI" />
        </div>
      </div>
      <div>
        <div
          class="flex font-extrabold justify-end titoloRosso text-xl my-8"
          ref="legendaRef"
        >
          {{ $t("LEGENDA") }}
        </div>
        <div class="flex flex-col space-y-1">
          <div class="flex flex-1 text-start">
            <SvgIcon name="look" class="h-6 w-6 my-auto mr-2" /><span
              >Vorrei almeno buttarci un occhio</span
            >
          </div>
          <div class="flex flex-1 text-start">
            <SvgIcon name="try" class="h-6 w-6 my-auto mr-2" /><span
              >Voglio farci una partita in Fiera</span
            >
          </div>
          <div class="flex flex-1 text-start">
            <SvgIcon name="buy" class="h-6 w-6 my-auto mr-2" /><span
              >Questo torna a casa con me<br />
              (R = Regalo C = Crowfunding)</span
            >
          </div>
          <div class="flex flex-1 text-start">
            <img src="../assets/book-24.png" class="my-auto mr-2" /><span
              >R= Romanzo L = Librogame<br />
              G = Giornale F = Fumetto Game<br />
              D = Dizionario W = Wargame 3D</span
            >
          </div>
          <div class="flex flex-1 text-start">
            <SvgIcon name="Forbidden" class="h-6 w-6 my-auto mr-2" /><span
              >Il Titolo non è provabile o comprabile</span
            >
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped></style>
