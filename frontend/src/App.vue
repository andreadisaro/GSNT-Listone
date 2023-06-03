<script setup>
import Header from "./components/Header.vue";
import "typeface-inter";
import Loading from "./components/Loading.vue";
import InvioDati from "./components/InvioDati.vue";
import { useDataStore } from "./store/useDataStore";
import ErrorGuard from "./components/ErrorGuard.vue";
import Cookie from "./components/Cookie.vue";
const dataStore = useDataStore();
</script>

<template>
  <Loading />
  <Header />
  <router-view></router-view>
  <InvioDati v-if="dataStore.sendData" />
  <ErrorGuard />
  <Cookie />
</template>

<style scoped></style>
