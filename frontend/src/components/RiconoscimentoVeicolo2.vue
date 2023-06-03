<script setup>
import { ref, onMounted } from "vue";
import Button from "./Button.vue";
import Input from "./Input.vue";
import Select from "./Select.vue";
import { useRouter } from "vue-router";
import { useDataStore } from "../store/useDataStore";
const dataStore = useDataStore();
const router = useRouter();

const nome = ref(dataStore.nome || localStorage.getItem("nome"));
const nomeOk = ref(false);
const cognome = ref(dataStore.cognome || localStorage.getItem("cognome"));
const cognomeOk = ref(false);
const km = ref(dataStore.km || localStorage.getItem("km"));
const kmOk = ref(false);
const numPasseggeriAdultiValue =
  dataStore.numPasseggeriAdulti ||
  localStorage.getItem("numPasseggeriAdulti") ||
  "1";
const numPasseggeriBambiniValue =
  dataStore.numPasseggeriBambini ||
  localStorage.getItem("numPasseggeriBambini") ||
  "0";
const passeggeriAdulti = ref({
  label: numPasseggeriAdultiValue,
  value: numPasseggeriAdultiValue,
});
const passeggeriBambini = ref({
  label: numPasseggeriBambiniValue,
  value: numPasseggeriBambiniValue,
});

onMounted(() => {
  var _paq = (window._paq = window._paq || []);
  _paq.push(['FormAnalytics::scanForForms', document.getElementById('formRv' + (dataStore.getIsVariantOriginal ? '2A' : '3B'))]);
  _paq.push(['FormAnalytics::trackForm', document.getElementById('formRv' + (dataStore.getIsVariantOriginal ? '2A' : '3B'))]);
})

const okFunction = () => {
  dataStore.setNome(nome.value);
  dataStore.setCognome(cognome.value);
  dataStore.setKm(km.value);
  dataStore.setNumPasseggeriAdulti(passeggeriAdulti.value.value);
  dataStore.setNumPasseggeriBambini(passeggeriBambini.value.value);
  var _paq = (window._paq = window._paq || []);
  _paq.push(['FormAnalytics::trackFormSubmit', document.getElementById('formRv' + (dataStore.getIsVariantOriginal ? '2A' : '3B'))]);
  dataStore.getIsVariantOriginal ? router.push("/rv3") : dataStore.setSendData(true);
};
</script>

<template>
  <form autocomplete="on" :id="'formRv' + (dataStore.getIsVariantOriginal ? '2A' : '3B')">
    <div class="w-full text-center items-stretch flex flex-col py-8 pt-20 min-h-[95vh]">
      <div class="w-full items-center px-2 flex flex-row justify-between align-middle">
        <img src="../assets/arrow_back_ios.svg" alt="Back" class="back h-6 w-6 my-auto" @click="$router.back()"
          v-if="!dataStore.getIsVariantOriginal" />
        <div class="h-6 w-6 my-auto" v-else />
        <div class="text-[25px] font-bold italic w-full flex-1">
          {{ $t("dati.tuoi_dati") }}
        </div>
        <div class="h-6 w-6 my-auto" />
      </div>
      <div class="w-full px-8 flex flex-row justify-between align-middle pt-4">
        <div class="relative w-full sm:w-1/2 bg-gray-200 rounded h-2 my-auto">
          <div :style="{ width: dataStore.getIsVariantOriginal ? '66%' : '100%' }"
            class="absolute top-0 h-2 rounded shim-green"></div>
        </div>
        <div class="ml-2">{{ dataStore.getIsVariantOriginal ? '2/3' : '2/2' }}</div>
      </div>
      <div class="w-full flex-1 px-8 pt-4">
        <div class="pt-8">
          <Input v-model="nome" :placeholder="$t('dati.nome')" :text="$t('dati.nome')" :required="true"
            @is-valid="(valid) => (nomeOk = valid)" name="firstName" />
        </div>
        <div class="pt-8">
          <Input v-model="cognome" :placeholder="$t('dati.cognome')" :text="$t('dati.cognome')" :required="true"
            @is-valid="(valid) => (cognomeOk = valid)" name="lastName" />
        </div>
        <div class="pt-8">
          <Input v-model="km" :placeholder="$t('dati.km')" :text="$t('dati.km')" :required="true"
            validation-regex="^[0-9]{1,6}$" @is-valid="(valid) => (kmOk = valid)" name="km" />
        </div>
        <div class="pt-8">
          <Select v-model="passeggeriAdulti" :text="$t('dati.passeggeri_adulti')" :options="[
            { label: '1', value: '1' },
            { label: '2', value: '2' },
            { label: '3', value: '3' },
            { label: '4', value: '4' },
            { label: '5', value: '5' },
            { label: '6', value: '6' },
            { label: '7', value: '7' },
            { label: '8', value: '8' },
            { label: '9', value: '9' },
          ]" name="num_adults" />
        </div>
        <div class="pt-8">
          <Select v-model="passeggeriBambini" :text="$t('dati.passeggeri_bambini')" :options="[
            { label: '0', value: '0' },
            { label: '1', value: '1' },
            { label: '2', value: '2' },
            { label: '3', value: '3' },
            { label: '4', value: '4' },
            { label: '5', value: '5' },
            { label: '6', value: '6' },
            { label: '7', value: '7' },
            { label: '8', value: '8' },
          ]" name="num_children" />
        </div>
      </div>
      <div class="w-full px-8 pb-8">
        <div class="text-xs italic text-right font-light">
          {{ $t("dati.campi_obbligatori") }}
        </div>
      </div>
      <div class="w-full px-8">
        <Button :text="$t('avanti')" :disabled="nomeOk === false || cognomeOk === false || kmOk === false"
          :onclick="($event) => okFunction()" />
      </div>
    </div>
  </form>
</template>

<style scoped>
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
