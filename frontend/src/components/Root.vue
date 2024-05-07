<script setup>
import { useDataStore } from "../store/useDataStore";
import { APISettings } from "../api/config.js";
import { useLoadingStore } from "../store/useLoadingStore";
import { useModalStore } from "../store/useModalStore";
import { useRouter } from "vue-router";
import { ref, watch, nextTick } from "vue";
import html2pdf from "html2pdf.js";
import List from "./List.vue";
import ListForPrint from "./ListForPrint.vue";
import Menu from "./Menu.vue";
const loadingStore = useLoadingStore();
const modalStore = useModalStore();
const dataStore = useDataStore();
const router = useRouter();
let fake = router.currentRoute.value.query.fake;
const html2Pdf = ref(null);
const errorFunction = () => {
  modalStore.showInfoModal(
    "Errore",
    "Errore di connessione, riprova più tardi"
  );
};
/*
loadingStore.addLoading();
fetch("/be/public/api/event", {
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
  */
if (!fake) {
  if (Object.entries(dataStore.items).length == 0) loadingStore.addLoading();
  fetch("/be/public/api/items", {
    method: "GET",
    headers: APISettings.headers,
  })
    .then(function (response) {
      if (response.status != 200) {
        console.error(response.status);
        if (Object.entries(dataStore.items).length == 0) errorFunction();
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
} else {
  dataStore.setFakeItems();
}
fetch("/be/public/api/journalists", {
  method: "GET",
  headers: APISettings.headers,
}).then(function (response) {
  if (response.status != 200) {
    console.error(response.status);
    //errorFunction();
  } else {
    response.json().then((res) => {
      console.log(res);
      dataStore.setJournalistsDays(res);
    });
  }
});
watch(
  () => dataStore.makePdf,
  (newValue) => {
    if (newValue) {
      loadingStore.addLoading();
      nextTick(() => {
        console.log("genero pdf");
        setTimeout(
          () =>
            html2pdf()
              .set({
                margin: 0,
                filename: "PlayListone24.pdf",
                pagebreak: { mode: "avoid-all" },
                image: {
                  type: "jpeg",
                  quality: 2,
                },
                html2canvas: {
                  scale: 2,
                  letterRendering: true,
                },
                jsPDF: {
                  unit: "in",
                  format: "a4",
                  orientation: "portrait",
                },
              })
              .from(html2Pdf.value.$el.innerHTML)
              .toPdf()
              .get("pdf")
              .then((pdf) => {
                const totalPages = pdf.internal.getNumberOfPages();
                for (let i = 1; i <= totalPages; i++) {
                  pdf.setPage(i);
                  pdf.setFontSize(10);
                  pdf.setTextColor(150);
                  pdf.text(
                    "Pagina " + i + " di " + totalPages,
                    pdf.internal.pageSize.getWidth() * 0.88,
                    pdf.internal.pageSize.getHeight() - 0.3
                  );
                }
              })
              .save()
              .then(() => {
                loadingStore.removeLoading();
                dataStore.makePdf = false;
              }),
          500
        );
      });
    }
  }
);
</script>

<template>
  <ListForPrint ref="html2Pdf" class="hidden" />
  <Menu v-if="dataStore.showMenu" />
  <List v-else />
</template>

<style scoped></style>
