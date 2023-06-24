<script setup>
import Header from "./components/Header.vue";
import "typeface-inter";
import Loading from "./components/Loading.vue";
</script>

<template>
  <Loading />
  <Header />
  <router-view></router-view>
  <!--Cookie /-->
</template>

<style scoped></style>
