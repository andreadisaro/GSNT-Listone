<script setup>
import Header from "./components/Header.vue";
import "typeface-inter";
import Loading from "./components/Loading.vue";
import InfoModal from "./components/InfoModal.vue";
import ConfirmPDFModal from "./components/ConfirmPDFModal.vue";
</script>

<template>
  <Loading />
  <Header />
  <router-view></router-view>
  <InfoModal />
  <ConfirmPDFModal />
  <!--Cookie /-->
</template>

<style scoped></style>
