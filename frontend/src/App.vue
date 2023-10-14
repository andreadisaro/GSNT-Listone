<script setup>
import Header from "./components/Header.vue";
import "typeface-inter";
import Loading from "./components/Loading.vue";
import InfoModal from "./components/InfoModal.vue";
</script>

<template>
  <Loading />
  <Header />
  <router-view></router-view>
  <InfoModal />
  <!--Cookie /-->
</template>

<style scoped></style>
