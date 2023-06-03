<script setup>
import { ref } from "vue";
import { useRouter } from "vue-router";
import { useDataStore } from "../store/useDataStore.js";
const dataStore = useDataStore();
const router = useRouter();
if (new Date().getTime() - Number.parseInt(localStorage.getItem("endDate")) > 1000 * 60 * 60 * 24) {
    localStorage.removeItem("endOk");
    localStorage.removeItem("error");
}
const error = ref(dataStore.error || localStorage.getItem("error") || false);
const endOk = ref(localStorage.getItem("endOk") || false);
if (endOk.value && location.pathname !== '/endOk') router.push("/endOk");
else if (error.value && location.pathname !== '/' && location.pathname !== '/endKo') router.push("/endKo");
</script>

<template></template>

<style scoped></style>
