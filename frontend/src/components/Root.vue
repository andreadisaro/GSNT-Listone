<script setup>
import { useDataStore } from "../store/useDataStore";
import { APISettings } from "../api/config.js";
import { useLoadingStore } from "../store/useLoadingStore";
import { useModalStore } from "../store/useModalStore";
import { useRouter } from "vue-router";
import { ref, watch, nextTick } from "vue";
// non rimuovere, serve per il pdf
import jsPDF from "jspdf";
import pdfMake from "pdfmake";
import pdfFonts from "pdfmake/build/vfs_fonts";
import htmlToPdfmake from "html-to-pdfmake";
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
  */
if (!fake) {
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
} else {
  dataStore.setFakeItems();
}
loadingStore.addLoading();
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
watch(
  () => dataStore.makePdf,
  (newValue) => {
    if (newValue) {
      //loadingStore.addLoading();
      nextTick(() => {
        console.log("genero pdf", html2Pdf.value.$el.innerHTML);
        //html to pdf format
        var html = htmlToPdfmake(html2Pdf.value.$el.innerHTML);

        const documentDefinition = { content: html };
        pdfMake.vfs = pdfFonts.pdfMake.vfs;
        pdfMake.createPdf(documentDefinition).open();
        dataStore.makePdf = false;
      });
    }
  }
);
async function beforeDownload({ html2pdf, options, pdfContent }) {
  await html2pdf()
    .set(options)
    .from(pdfContent)
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
    .save();
}
</script>

<template>
  <Menu v-if="dataStore.showMenu" />
  <List v-else />
  <ListForPrint ref="html2Pdf" />
</template>

<style scoped></style>
