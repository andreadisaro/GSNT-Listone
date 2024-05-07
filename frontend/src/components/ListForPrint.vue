<script setup>
import { useDataStore } from "../store/useDataStore";
import ItemForPrint from "./ItemForPrint.vue";
import ItemJournalist from "./ItemJournalist.vue";
import SvgIcon from "./SvgIcon.vue";
import { computed } from "vue";
const dataStore = useDataStore();

const computedItems = computed(() => {
  let ret = {};
  for (const catI in dataStore.items) {
    ret[catI] = {};
    for (const [index, [editorI, editor]] of Object.entries(
      Object.entries(dataStore.items[catI])
    )) {
      if (index % 2 == 0) {
        ret[catI][editorI] = editor;
      } else {
        if (!ret[catI + "Dx"]) {
          ret[catI + "Dx"] = {};
        }
        ret[catI + "Dx"][editorI] = editor;
      }
    }
  }
  console.log("computedItems", ret);
  return ret;
});
</script>

<template>
  <section
    class="pdf-content w-full text-center items-stretch flex flex-col min-h-[95vh]"
  >
    <section class="w-full items-center flex-1 p-8 pt-20 printPdfBody">
      <section v-for="(category, catI, i) in computedItems" :key="catI">
        <section
          class="flex font-extrabold justify-end titoloRosso text-xl"
          v-if="catI.indexOf('Dx') == -1"
        >
          {{ catI }}
        </section>
        <section class="row" v-if="catI.indexOf('Dx') == -1">
          <section class="column">
            <section
              v-for="(editor, editorI, j) in category"
              :key="editorI"
              class="page-break pt-4"
            >
              <section class="flex flex-row space-x-2">
                <section class="w-6 sfondoVerdeSemiTrasparente py-1">
                  <SvgIcon name="look" class="h-6 w-6 my-auto" />
                </section>
                <section class="w-6 sfondoVerdeSemiTrasparente py-1">
                  <SvgIcon
                    name="buy"
                    class="h-6 w-6 my-auto"
                    v-if="editor.length && editor[0].bookType"
                  />
                  <SvgIcon name="try" class="h-6 w-6 my-auto" v-else />
                </section>
                <section class="w-6 sfondoVerdeSemiTrasparente py-1">
                  <img
                    src="../assets/book-24.png"
                    class="my-auto"
                    v-if="editor.length && editor[0].bookType"
                  />
                  <SvgIcon name="buy" class="h-6 w-6 my-auto" v-else />
                </section>
                <section class="font-semibold flex-1">
                  {{ dataStore.editors[editorI]?.name || editorI }}
                </section>
              </section>
              <section
                v-for="(item, i) in editor"
                :key="'item' + item.id"
                class="flex-1"
              >
                <ItemForPrint :item="item" />
              </section>
            </section>
          </section>
          <section class="column">
            <section
              v-for="(editor, editorI, j) in computedItems[catI + 'Dx']"
              :key="editorI"
              class="page-break pt-4"
            >
              <section class="flex flex-row space-x-2">
                <section class="w-6 sfondoVerdeSemiTrasparente py-1">
                  <SvgIcon name="look" class="h-6 w-6 my-auto" />
                </section>
                <section class="w-6 sfondoVerdeSemiTrasparente py-1">
                  <SvgIcon
                    name="buy"
                    class="h-6 w-6 my-auto"
                    v-if="editor.length && editor[0].bookType"
                  />
                  <SvgIcon name="try" class="h-6 w-6 my-auto" v-else />
                </section>
                <section class="w-6 sfondoVerdeSemiTrasparente py-1">
                  <img
                    src="../assets/book-24.png"
                    class="my-auto"
                    v-if="editor.length && editor[0].bookType"
                  />
                  <SvgIcon name="buy" class="h-6 w-6 my-auto" v-else />
                </section>
                <section class="font-semibold flex-1">
                  {{ dataStore.editors[editorI]?.name || editorI }}
                </section>
              </section>
              <section
                v-for="(item, i) in editor"
                :key="'item' + item.id"
                class="flex-1"
              >
                <ItemForPrint :item="item" />
              </section>
            </section>
          </section>
        </section>
      </section>
      <section>
        <section
          class="flex font-extrabold justify-end titoloRosso text-xl my-8"
          ref="redazioneRef"
        >
          {{ $t("REDAZIONE") }}
        </section>
        <section class="flex flex-row space-x-2">
          <section
            class="w-6 font-bold sfondoVerdeSemiTrasparente"
            v-for="day in dataStore.days"
            :key="day + 'title'"
          >
            {{ day }}
          </section>
        </section>
        <section
          v-for="(jd, jdI) in dataStore.journalistsDays"
          :key="jdI"
          class="flex-1"
        >
          <ItemJournalist :item="jd" :journalist="jdI" />
        </section>
      </section>
      <section>
        <section
          class="flex font-extrabold justify-end titoloRosso text-xl my-8"
          ref="legendaRef"
        >
          {{ $t("LEGENDA") }}
        </section>
        <section class="flex flex-col space-y-1">
          <section class="flex">
            <SvgIcon name="look" class="h-6 w-6 my-auto mr-2" /><span
              >Vorrei almeno buttarci un occhio</span
            >
          </section>
          <section class="flex">
            <SvgIcon name="try" class="h-6 w-6 my-auto mr-2" /><span
              >Voglio farci una partita in Fiera</span
            >
          </section>
          <section class="flex">
            <SvgIcon name="buy" class="h-6 w-6 my-auto mr-2" /><span
              >Questo torna a casa con me</span
            >
          </section>
          <section class="flex">
            <img src="../assets/book-24.png" class="my-auto mr-2" /><span
              class="text-start"
              >R= Romanzo L = Librogame<br />
              G = Giornale F = Fumetto Game</span
            >
          </section>
          <section class="flex">
            <SvgIcon name="Forbidden" class="h-6 w-6 my-auto mr-2" /><span
              >Il Titolo non è provabile o comprabile</span
            >
          </section>
        </section>
      </section>
    </section>
  </section>
</template>

<style scoped>
.row {
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  width: 100%;
}

.column {
  display: flex;
  flex-direction: column;
  flex-basis: 100%;
  flex: 1;
}

.printPdfBody {
  background-color: #ecf4e5;
  background-image: url("/meeple20.png");
  background-position: center;
  background-repeat: repeat-y;
  background-attachment: fixed;
  background-size: 130%;
}

.page-break {
  page-break-inside: avoid;
}
</style>
