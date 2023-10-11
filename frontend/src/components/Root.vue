<script setup>
import { useDataStore } from "../store/useDataStore";
import { APISettings } from "../api/config.js";
import { useLoadingStore } from "../store/useLoadingStore";
import List from "./List.vue";
import Menu from "./Menu.vue";
const loadingStore = useLoadingStore();
const dataStore = useDataStore();
const errorFunction = () => {
  alert("Errore di connessione, riprova più tardi");
};
loadingStore.addLoading();
fetch("/public/api/event", {
  method: "GET",
  headers: APISettings.headers,
})
  .then(function (response) {
    if (response.status != 200) {
      console.error(response.status);
      errorFunction();
    } else {
      response.json().then((res) => {
        //console.log(res);
        dataStore.setEventTitle(res.title);
        dataStore.setEventLogo(res.logo);
      });
    }
  })
  .finally(() => {
    loadingStore.removeLoading();
  });
loadingStore.addLoading();
fetch("/public/api/items", {
  method: "GET",
  headers: APISettings.headers,
})
  .then(function (response) {
    if (response.status != 200) {
      console.error(response.status);
      errorFunction();
    } else {
      response.json().then((res) => {
        console.log(res);
        dataStore.setItems(res);
      });
    }
  })
  .finally(() => {
    loadingStore.removeLoading();
  });
fetch("/public/api/journalists", {
  method: "GET",
  headers: APISettings.headers,
})
  .then(function (response) {
    if (response.status != 200) {
      console.error(response.status);
      errorFunction();
    } else {
      response.json().then((res) => {
        console.log(res);
        dataStore.setJournalistsDays(res);
      });
    }
  })
  .finally(() => {
    loadingStore.removeLoading();
  });
</script>

<template>
  <Menu v-if="dataStore.showMenu" />
  <List v-else />
</template>

<style scoped></style>
