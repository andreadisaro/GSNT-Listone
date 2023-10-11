<script setup>
import { ref, watch } from "vue";
import { useDataStore } from "../store/useDataStore";
import Item from "./Item.vue";
import ItemJournalist from "./ItemJournalist.vue";
const dataStore = useDataStore();
const catRefs = ref([]);
watch(
  () => dataStore.goToRef,
  (newVal) => {
    if (newVal) {
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
        <div class="flex font-extrabold" :class="i > 0 ? 'my-8' : ''">
          {{ catI }}
        </div>
        <div
          v-for="(editor, editorI, j) in category"
          :key="editorI"
          class="flex-1"
          :class="j > 0 ? 'mt-4' : ''"
        >
          <div class="flex flex-row">
            <div class="w-6">A</div>
            <div class="w-6">P</div>
            <div class="w-6">
              {{ editor.length && editor[0].bookType ? "L" : "B" }}
            </div>
            <div class="font-semibold flex-1">{{ editorI }}</div>
          </div>
          <div v-for="item in editor" :key="'item' + item.id" class="flex-1">
            <Item :item="item" />
          </div>
        </div>
      </div>
      <div>
        <div class="flex font-extrabold my-8">{{ $t("Redazione") }}</div>
        <div class="flex flex-row">
          <div class="w-6" v-for="day in dataStore.days" :key="day + 'title'">
            {{ day }}
          </div>
          <div class="font-semibold flex-1">&nbsp;</div>
        </div>
        <div
          v-for="(jd, jdI) in dataStore.journalistsDays"
          :key="jdI"
          class="flex-1"
        >
          <ItemJournalist :item="jd" :journalist="jdI" />
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped></style>
