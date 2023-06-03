<script setup>
import { useDataStore } from "../store/useDataStore";
import LanguageSelector from "./LanguageSelector.vue";
const dataStore = useDataStore();
</script>

<template>
  <div class="w-full items-center px-4 flex flex-row justify-between align-middle header py-1 z-40">
    <img :src="dataStore.getImgCliente" alt="LogoAuto" class="h-12 w-12 my-auto" />
    <div class="text-[23px] font-bold w-full flex-1 text-center">
      {{ $t("header.title") }}
    </div>
    <div class="h-12 w-12 my-auto" v-if="dataStore.getIsVariantOriginal" />
    <div class="h-6 w-12 my-auto" v-else>
      <LanguageSelector />
    </div>
  </div>
</template>

<style scoped>
.header {
  color: #fff;
  background-color: #0e5497;
  filter: drop-shadow(0px 4px 4px rgba(0, 0, 0, 0.25));
  position: fixed;
}
</style>
