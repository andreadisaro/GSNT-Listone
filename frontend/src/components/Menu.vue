<script setup>
import { nextTick } from "vue";
import { useDataStore } from "../store/useDataStore";
const dataStore = useDataStore();
const vaiACategoria = (i) => {
  dataStore.showMenu = false;
  nextTick(() => {
    dataStore.goToRef = i;
  });
};
</script>

<template>
  <div
    class="w-full text-center items-stretch flex flex-col p-8 pt-20 min-h-[95vh]"
  >
    <div class="w-full items-center flex-1">
      <div class="flex font-extrabold justify-around">Menu</div>
      <div class="flex">Vai a:</div>
      <div
        v-for="(category, catI, i) in dataStore.items"
        :key="catI"
        class="ml-4"
      >
        <div
          class="flex font-extrabold"
          :class="i > 0 ? 'my-2' : ''"
          @click="vaiACategoria(i)"
        >
          {{ catI }}
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped></style>
