<script setup>
import { useDataStore } from "../store/useDataStore";
const dataStore = useDataStore();
import SvgIcon from "./SvgIcon.vue";
</script>

<template>
  <div
    class="w-full items-center px-4 flex flex-row justify-between align-middle header py-1 z-40"
  >
    <!--SvgIcon
      :name="dataStore.eventLogo"
      class="h-12 w-12 my-auto"
      v-if="dataStore.eventLogo"
    /-->
    <img src="../assets/lucca.jpg" class="h-12 w-12 my-auto" />
    <!--div class="text-[23px] font-bold w-full flex-1 text-center">
      {{ dataStore.eventTitle }}
    </div-->
    <div class="text-[23px] font-bold w-full flex-1 text-center">
      LUCCA C&G 2023
    </div>
    <SvgIcon
      name="Hamburger_icon"
      class="h-6 w-6 my-auto fill-white"
      @click="dataStore.showMenu = !dataStore.showMenu"
    />
  </div>
</template>

<style scoped>
.header {
  color: #fff;
  background-color: #5ca040;
  filter: drop-shadow(0px 4px 4px rgba(0, 0, 0, 0.25));
  position: fixed;
}
</style>
