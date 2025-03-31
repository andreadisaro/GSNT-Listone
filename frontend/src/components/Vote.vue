<script setup>
import { useDataStore } from "../store/useDataStore";
import { APISettings } from "../api/config.js";
import { useLoadingStore } from "../store/useLoadingStore";
import { useModalStore } from "../store/useModalStore";
import { useRouter } from "vue-router";
import { ref } from "vue";
const loadingStore = useLoadingStore();
const modalStore = useModalStore();
const dataStore = useDataStore();
dataStore.showMenuButton = false;
const router = useRouter();
const errorFunction = () => {
  modalStore.showInfoModal(
    "Errore",
    "Errore di connessione, riprova più tardi"
  );
};
const idItem = router.currentRoute.value.params.id;
const item = ref(null);
if (idItem) {
  loadingStore.addLoading();
  fetch(`/be/public/api/item/${idItem}`, {
    method: "GET",
    headers: APISettings.headers,
  }).then(function (response) {
    if (response.status != 200) {
      console.error(response.status);
      errorFunction();
      router.push("/");
    } else {
      response.json().then((resItem) => {
        if (resItem.idBgg) {
          fetch(`https://boardgamegeek.com/xmlapi2/thing?id=${resItem.idBgg}`, {
            method: "GET",
            //headers: APISettings.headers,
          }).then(function (response) {
            if (response.status != 200) {
              item.value = { ...resItem, bgg: null };
            } else {
              response.text().then((xmlText) => {
                const parser = new DOMParser();
                const xmlDoc = parser.parseFromString(xmlText, "text/xml");
                const json = xmlToJson(xmlDoc);
                if (json.items?.item?.thumbnail?.text) {
                  json.items.item.thumbnail.text = json.items.item.thumbnail.text.trim();
                }
                if (json.items?.item?.image?.text) {
                  json.items.item.image.text = json.items.item.image.text.trim();
                }
                item.value = { ...resItem, bgg: json };
                //console.log({ ...resItem, bgg: json });
              });
            }
          });
        }
        else {
          item.value = { ...resItem, bgg: null };
        }
      });
    }
  }).finally(() => {
    loadingStore.removeLoading();
  });
}
else {
  router.push("/");
}

const xmlToJson = (xml) => {
  let obj = {};
  if (xml.nodeType === 1) { // element
    if (xml.attributes.length > 0) {
      obj["@attributes"] = {};
      for (let j = 0; j < xml.attributes.length; j++) {
        const attribute = xml.attributes.item(j);
        obj["@attributes"][attribute.nodeName] = attribute.nodeValue;
      }
    }
  } else if (xml.nodeType === 3) { // text
    obj = xml.nodeValue;
  }
  if (xml.hasChildNodes()) {
    for (let i = 0; i < xml.childNodes.length; i++) {
      const item = xml.childNodes.item(i);
      let nodeName = item.nodeName;
      if (nodeName === "#text") {
        nodeName = "text";
      }
      if (nodeName === "@attributes") {
        nodeName = "attributes";
      }
      if (typeof obj[nodeName] === "undefined") {
        obj[nodeName] = xmlToJson(item);
      } else {
        if (typeof obj[nodeName].push === "undefined") {
          const old = obj[nodeName];
          obj[nodeName] = [];
          obj[nodeName].push(old);
        }
        obj[nodeName].push(xmlToJson(item));
      }
    }
  }
  return obj;
}

const vota = () => {
  const votes = localStorage.getItem("votes");
  if (votes) {
    const votesArray = JSON.parse(votes);
    if (votesArray.length > 0 && votesArray.find((v) => v === idItem)) {
      modalStore.showInfoModal(
        "Errore",
        "Hai già votato per questo titolo"
      );
      router.push("/");
      return;
    }
  }
  loadingStore.addLoading();
  fetch(`/be/public/api/vote/${idItem}`, {
    method: "POST",
    headers: APISettings.headers,
  }).then(function (response) {
    if (response.status != 200) {
      console.error(response.status);
      errorFunction();
    } else {
      response.json().then((res) => {
        modalStore.showInfoModal(
          "Voto effettuato",
          "Grazie!<br/>Il tuo voto è stato registrato con successo<br/><br/>Ora sarai reindirizzato al nostro listone, che puoi utilizzare per tenere traccia dei titoli più interessanti della fiera"
        );
        if (votes) {
          const votesArray = JSON.parse(votes);
          votesArray.push(idItem);
          localStorage.setItem("votes", JSON.stringify(votesArray));
        } else {
          localStorage.setItem("votes", JSON.stringify([idItem]));
        }
        router.push("/");
      });
    }
  }).finally(() => {
    loadingStore.removeLoading();
  });
}
</script>

<template>
  <template v-if="item">
    <div class="w-full text-center items-stretch flex flex-col p-8 pt-20 min-h-[95vh]">
      <div class="w-full flex">
        <div class="flex font-extrabold justify-start text-xl">
          Titolo:
        </div>
        <div class="flex-1"></div>
        <div class="flex font-extrabold text-end titoloRosso text-xl">
          {{ item.name }}
        </div>
      </div>
      <div class="w-full flex">
        <div class="flex font-extrabold justify-start text-xl">
          Editore:
        </div>
        <div class="flex-1"></div>
        <div class="flex font-extrabold text-end titoloRosso text-xl">
          {{ item.editor.name }}
        </div>
      </div>
      <div class="w-full flex">
        <div class="flex font-extrabold justify-start text-xl">
          Categoria:
        </div>
        <div class="flex-1"></div>
        <div class="flex font-extrabold text-end titoloRosso text-xl">
          {{ item.category.name }}
        </div>
      </div>
      <div class="w-full items-center flex-1 mt-8">
        <div class="flex justify-center">
          <img :src="item.bgg?.items?.item?.image?.text" class="w-1/2" />
        </div>
        <div class="flex justify-center my-8">
          <div class="text-lg">Vuoi votare per questo titolo?<br/>Premi il <b>bottone qui sotto!</b><br/></div>
        </div>
        <div class="flex justify-center my-8">
          <button class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded" @click="vota">
            Vota
          </button>
        </div>
      </div>
    </div>
  </template>
</template>

<style scoped>
</style>
