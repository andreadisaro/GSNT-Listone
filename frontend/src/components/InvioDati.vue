<script setup>
import { APISettings } from "../api/config.js";
import { useLoadingStore } from "../store/useLoadingStore";
import { useRouter } from "vue-router";
import { useDataStore } from "../store/useDataStore";
const dataStore = useDataStore();
const router = useRouter();
const loadingStore = useLoadingStore();
const errorFunction = () => {
  dataStore.setErrorType("generico");
  dataStore.setShowErrorCaption2(false);
  router.push("/endKo");
};

loadingStore.addLoading();
fetch("/public/api/altri_dati", {
  method: "POST",
  headers: APISettings.headers,
  body: JSON.stringify({
    token: dataStore.getToken,
    targa: dataStore.targa,
    telaio: dataStore.telaio,
    prodotto: dataStore.prodotto,
    nome: dataStore.nome,
    cognome: dataStore.cognome,
    km: dataStore.km,
    num_passeggeri_adulti: Number.parseInt(dataStore.numPasseggeriAdulti),
    num_passeggeri_bambini: Number.parseInt(dataStore.numPasseggeriBambini),
    problema: dataStore.evento,
    sotto_evento:
      dataStore.evento == "I" ? "0011" : dataStore.sottoEvento,
    luogo_di_fermo_lat: dataStore.luogoDelFermoLat,
    luogo_di_fermo_lon: dataStore.luogoDelFermoLon,
    comune: dataStore.comune,
    provincia: dataStore.provincia,
    via: dataStore.via,
    civico: dataStore.civico,
    autostrada: dataStore.autostrada ? "1" : "0",
    estero: dataStore.estero ? "1" : "0",
  }),
})
  .then(function (response) {
    var res = {
      success: "1",
      message_code: "string",
      message: "string",
      response: {
        copertura: "1",
        num_dossier: "123456",
      },
    };
    if (response.status != 200 && !dataStore.demo) {
      console.error(response.status);
      if (!dataStore.demo) errorFunction();
    } else {
      response.json().then((json) => {
        if (!dataStore.demo) res = json;
        console.log(res);
        if (res.response.num_dossier) dataStore.setDossier(res.response.num_dossier);
        if (res.success == "0") {
          console.error(res.message);
          if (!dataStore.demo) errorFunction();
        }
        else if (res.response.copertura == "1") {
          localStorage.setItem("endOk", "true");
          localStorage.setItem("endDate", new Date().getTime());
          router.push("/endOk");
        } else {
          dataStore.setErrorType("datiInseriti");
          dataStore.setShowErrorCaption2(true);
          router.push("/endKo");
          var _paq = (window._paq = window._paq || []);
          if (dataStore.autostrada) {
            _paq.push(['trackEvent', 'WayOut', 'InvioDati', 'Autostrada'])
          }
          else if (dataStore.estero) {
            _paq.push(['trackEvent', 'WayOut', 'InvioDati', 'Estero'])
          }
          else {
            _paq.push(['trackEvent', 'WayOut', 'InvioDati', 'SottoEventoNonCoperto'])
          }
        }
      });
    }
  })
  .finally(() => {
    loadingStore.removeLoading();
    dataStore.setSendData(false);
  });
</script>

<template></template>

<style scoped></style>
