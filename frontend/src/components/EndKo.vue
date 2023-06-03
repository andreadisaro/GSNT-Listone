<script setup>
import { ref } from "vue";
import { useDataStore } from "../store/useDataStore";
const dataStore = useDataStore();
const errorType = ref(dataStore.errorType || localStorage.getItem("errorType"));
const dossier = ref(dataStore.dossier || localStorage.getItem("dossier"));
const showErrorCaption2 = ref(
  dataStore.showErrorCaption2 || localStorage.getItem("showErrorCaption2")
);
dataStore.setError(true);
</script>

<template>
  <div class="w-full text-center items-stretch flex flex-col p-8 pt-20 min-h-[95vh]">
    <div class="w-full items-center px-2 flex flex-row justify-between align-middle">
      <div class="text-[25px] font-bold italic w-full flex-1">
        {{ $t("end.title_ko") }}
      </div>
    </div>
    <div class="text-[20px] italic w-full flex-1 pt-12">
      {{ $t(`end.caption_ko1_${errorType}`) }}
      <div v-if="dossier">
        <br /><br />{{ $t("end.dossier") }}&nbsp;{{ dossier }}
      </div>
      <div v-if="showErrorCaption2 == true">
        <br /><br />{{ $t("end.caption_ko2") }}
      </div>
    </div>
    <div class="w-full items-end flex-1">
      <img src="../assets/carro_ko.png" alt="Carro" class="imgCarro" />
    </div>
  </div>
</template>

<style scoped>
.imgCarro {
  margin-left: auto;
  margin-right: 0;
}
</style>
