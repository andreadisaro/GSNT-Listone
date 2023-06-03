<script setup>
import { useRouter } from "vue-router";
import { useDataStore } from "../store/useDataStore";
import { APISettings } from "../api/config.js";
import { useLoadingStore } from "../store/useLoadingStore";
import { useI18n } from "vue-i18n";
const i18n = useI18n();
const loadingStore = useLoadingStore();
const dataStore = useDataStore();
const router = useRouter();
const errorFunction = () => {
  dataStore.setErrorType("generico");
  dataStore.setShowErrorCaption2(false);
  router.push("/endKo");
};
let token = router.currentRoute.value.query.t;
if (token == null) {
  token = localStorage.getItem("token");
}
dataStore.setToken(token);
let demo = router.currentRoute.value.query.demo;
dataStore.setDemo(demo);
if ((token == null || token == "null") && !demo) {
  console.error("Token null");
  errorFunction();
}
else {
  loadingStore.addLoading();
  let variant = localStorage.getItem("variant");
  dataStore.setVariant(variant);
  if (variant == "original") {
    router.push("/welcome");
  }
  else if (variant == "b") {
    router.push("/rv1");
  }
  fetch("/public/api/decripta", {
    method: "POST",
    headers: APISettings.headers,
    body: JSON.stringify({
      token: dataStore.getToken,
      canale: "WEB",
    }),
  }).then(function (response) {
    var res = {
      success: "1",
      message_code: "string",
      message: "string",
      response: {
        num_cell: "3331234567",
        num_assistenza: "111",
        //num_assistenza: "0266165966",//Kymco
        //num_assistenza: "800541760",//Voge
        //num_assistenza: "0266165140",//CFMoto
        //num_assistenza: "0266165651",//Subaru
        //num_assistenza: "0266165176",//Yadea
        //num_assistenza: "0266165326",//BMW
        //num_assistenza: "0266165548",//Volvo
        //num_assistenza: "0266165610",//Ducati
        //num_assistenza: "0266165329",//BMWMotorrad
        //num_assistenza: "0266165846",//Lifan
      },
    };
    if (response.status != 200 && !dataStore.demo) {
      console.error(response.status);
      if (!dataStore.demo) errorFunction();
    } else {
      response.json().then((json) => {
        if (!dataStore.demo) res = json;
        console.log(res);
        if (res.success == "0") {
          console.error(res.message);
          if (!dataStore.demo) errorFunction();
        }
        dataStore.setNumCell(res.response.num_cell);
        dataStore.setNumChiamato(res.response.num_assistenza.replace(/ /g, ''));
        if (dataStore.getPrivacyCliente) {
          for (let l in dataStore.getPrivacyCliente) {
            i18n.mergeLocaleMessage(l, dataStore.getPrivacyCliente[l]);
          }
        }
      });
    }
  }).finally(() => {
    loadingStore.removeLoading();
  });
}
</script>

<template></template>

<style scoped></style>
