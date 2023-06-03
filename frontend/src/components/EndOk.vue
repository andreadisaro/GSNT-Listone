<script setup>
import { ref } from "vue";
import { useDataStore } from "../store/useDataStore";
const dataStore = useDataStore();

const dossier = ref(dataStore.dossier || localStorage.getItem("dossier"));
</script>

<template>
  <div class="w-full text-center items-stretch flex flex-col p-8 pt-20 min-h-[95vh]">
    <div class="w-full items-center px-2 flex flex-row justify-between align-middle">
      <div class="text-[25px] font-bold italic w-full flex-1">
        {{ $t("end.title") }}
      </div>
    </div>
    <div class="w-full items-center flex-1">
      <img src="../assets/success.svg" alt="Success" class="m-auto py-4" />
    </div>
    <div class="text-[25px] font-bold w-full flex-1 py-4" style="color: #086551">
      {{ $t("end.caption") }}
    </div>
    <div class="text-[20px] italic w-full flex-1">
      {{ $t("end.dossier") }}&nbsp;{{ dossier }}<br /><br />
      {{ $t("end.sms1") }}&nbsp;<b>{{ $t("end.sms2") }}</b>&nbsp;{{ $t("end.sms3") }}
    </div>
    <div class="w-full items-end flex-1">
      <img src="../assets/carro.png" alt="Carro" class="imgCarro" />
    </div>
  </div>
</template>

<style scoped>
.imgCarro {
  margin-left: auto;
  margin-right: 0;
}
</style>
