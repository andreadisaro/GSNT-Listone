<script setup>
import { ref, onMounted } from "vue";
import Button from "./Button.vue";
import Input from "./Input.vue";
import { APISettings } from "../api/config.js";
import { useLoadingStore } from "../store/useLoadingStore";
import { useRouter } from "vue-router";
import { useDataStore } from "../store/useDataStore";
const dataStore = useDataStore();
const router = useRouter();
const loadingStore = useLoadingStore();

const numCell = ref(dataStore.numCell || localStorage.getItem("numCell"));
const targa = ref(dataStore.targa || localStorage.getItem("targa"));
const telaio = ref(dataStore.telaio || localStorage.getItem("telaio"));
const telaioOk = ref(false);
const numCellOk = ref(false);

const errorFunction = () => {
  dataStore.setErrorType("generico");
  dataStore.setShowErrorCaption2(false);
  router.push("/endKo");
};

onMounted(() => {
  var _paq = (window._paq = window._paq || []);
  _paq.push(['FormAnalytics::scanForForms', document.getElementById('formRv1Telaio' + (dataStore.getIsVariantOriginal ? 'A' : 'B'))]);
  _paq.push(['FormAnalytics::trackForm', document.getElementById('formRv1Telaio' + (dataStore.getIsVariantOriginal ? 'A' : 'B'))]);
})

const okFunction = () => {
  dataStore.setNumCell(numCell.value);
  dataStore.setTarga(targa.value);
  loadingStore.addLoading();
  var _paq = (window._paq = window._paq || []);
  _paq.push(['FormAnalytics::trackFormSubmit', document.getElementById('formRv1Telaio' + (dataStore.getIsVariantOriginal ? 'A' : 'B'))]);
  fetch("/public/api/telaio", {
    method: "POST",
    headers: APISettings.headers,
    body: JSON.stringify({
      token: dataStore.getToken,
      telaio: telaio.value,
      num_cell: numCell.value,
    }),
  })
    .then(function (response) {
      var res = {
        success: "1",
        message_code: "string",
        message: "string",
        response: {
          veicolo_identificato: "1",
          copertura: "1",
          prodotto: "prodotto_bmw",
        },
      };
      dataStore.setTelaio(telaio.value);
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
          else if (res.response.veicolo_identificato == "1") {
            if (res.response.copertura == "1") {
              dataStore.setProdotto(res.response.prodotto);
              router.push(dataStore.getIsVariantOriginal ? "/rv2" : "/rv3");
            } else {
              dataStore.setErrorType("else");
              dataStore.setShowErrorCaption2(true);
              router.push("/endKo");
              _paq.push(['trackEvent', 'WayOut', 'RV1Telaio', 'TelaioNonCoperto'])
            }
          } else {
            dataStore.setErrorType("riconosciuto");
            dataStore.setShowErrorCaption2(true);
            router.push("/endKo");
            _paq.push(['trackEvent', 'WayOut', 'RV1Telaio', 'TelaioNonRiconosciuto'])
          }
        });
      }
    })
    .finally(() => {
      loadingStore.removeLoading();
    });
};
</script>

<template>
  <form autocomplete="on" :id="'formRv1Telaio' + (dataStore.getIsVariantOriginal ? 'A' : 'B')">
    <div class="w-full text-center items-stretch flex flex-col py-8 pt-20 min-h-[95vh]">
      <div v-if="dataStore.getIsVariantOriginal">
        <div class="w-full items-center px-2 flex flex-row justify-between align-middle">
          <img src="../assets/arrow_back_ios.svg" alt="Back" class="back h-6 w-6 my-auto" @click="$router.back()" />
          <div class="text-[25px] font-bold italic w-full flex-1">
            {{ $t("veicolo.title") }}
          </div>
          <div class="h-6 w-6 my-auto" />
        </div>
        <div class="w-full px-8 flex flex-row justify-between align-middle pt-4">
          <div class="relative w-full sm:w-1/2 bg-gray-200 rounded h-2 my-auto">
            <div style="width: 33%" class="absolute top-0 h-2 rounded shim-green"></div>
          </div>
          <div class="ml-2">1/3</div>
        </div>
      </div>
      <div v-else>
        <div class="text-2xl italic w-full flex-1 text-[#08335C]">
          {{ $t("welcome.title") }}
        </div>
        <div class="w-full items-center flex-1">
          <img src="../assets/assistant.png" alt="Assistant" class="imgAssistant mt-10" />
        </div>
      </div>
      <div class="w-full flex-1 px-8 pt-4">
        <div class="text-[17px]" style="color: #9e0d0d">
          {{ $t("veicolo.caption_telaio") }}
        </div>
        <div class="pt-8">
          <Input v-model="numCell" :placeholder="$t('dati.num_cell')" :text="$t('dati.num_cell')" :required="true"
            validation-regex="^([+]?[\s0-9]+)?(\d{3}|[(]?[0-9]+[)])?([-]?[\s]?[0-9])+$"
            @is-valid="(valid) => (numCellOk = valid)" name="phone" />
        </div>
        <div class="pt-8">
          <Input v-model="targa" :placeholder="$t('dati.targa_placeholder')" :text="$t('dati.targa')" :required="true"
            readonly name="plate" />
        </div>
        <div class="pt-8">
          <Input v-model="telaio" :placeholder="$t('dati.telaio_placeholder')" :text="$t('dati.telaio')" :required="true"
            validation-regex="^[A-HJ-NPR-Z0-9]{17}$" @is-valid="(valid) => (telaioOk = valid)" name="VIN" />
          <div class="flex flex-row-reverse items-center">
            <div class="flex flex-row-reverse items-center" @click="dataStore.setNumCell(numCell);
            dataStore.setTarga(targa);
            if (telaio) dataStore.setTelaio(telaio);
            $router.push('/telaio')">
              <img src="/src/assets/info.svg" alt="Info" class="h-5 w-5 my-auto" />
              <div class="text-xs italic text-right font-light">
                {{ $t("telaio.dove") }}&nbsp;
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="w-full px-8 pb-8">
        <div class="text-xs italic text-right font-light">
          {{ $t("dati.campi_obbligatori") }}
        </div>
      </div>
      <div class="w-full px-8">
        <Button :text="$t('avanti')" :disabled="!numCellOk || !telaioOk" :onclick="($event) => okFunction()" />
      </div>
    </div>
  </form>
</template>

<style scoped>
.imgAssistant {
  height: 91px;
  width: 54px;
  margin-left: auto;
  margin-right: auto;
}

.shim-green {
  position: relative;
  overflow: hidden;
  background-color: rgba(1, 98, 77, 0.73);
}

.shim-green::after {
  position: absolute;
  top: 0;
  right: 0;
  bottom: 0;
  left: 0;
  transform: translateX(-100%);
  background-color: #ebebeb;
  content: "";
}
</style>
