<script setup>
import { ref, onMounted, watch } from "vue";
import Button from "./Button.vue";
import Select from "./Select.vue";
import Radio from "./Radio.vue";
import Location from "./Location.vue";
import { useI18n } from "vue-i18n";
import { useRouter } from "vue-router";
import { useDataStore } from "../store/useDataStore";
const dataStore = useDataStore();
const router = useRouter();
const { t } = useI18n();

let autostradaValue = dataStore.autostrada;
if (autostradaValue == null && localStorage.getItem("autostrada")) {
  autostradaValue = localStorage.getItem("autostrada") === "true";
}
const autostrada = ref(autostradaValue);
let esteroValue = dataStore.estero;
if (esteroValue == null && localStorage.getItem("estero")) {
  esteroValue = localStorage.getItem("estero") === "true";
}
const estero = ref(esteroValue);
const eventoValue = dataStore.evento || localStorage.getItem("evento") || "I";
const evento = ref({
  label: eventoValue == "I" ? t("dati.incidente") : t("dati.guasto"),
  value: eventoValue,
});
const sottoeventi = [
  {
    label: t("dati.batteria"),
    value: "0020",
  },
  {
    label: t("dati.gomme"),
    value: "0015",
  },
  {
    label: t("dati.mancanza_carburante"),
    value: "0014",
  },
  {
    label: t("dati.errato_carburante"),
    value: "0023",
  },
  {
    label: t("dati.motore"),
    value: "0003",
  },
  {
    label: t("dati.chiavi"),
    value: "0016",
  },
  {
    label: t("dati.altro"),
    value: "0003",
  },
];

const sottoeventoValue =
  dataStore.sottoEvento || localStorage.getItem("sottoEvento");
const sottoevento = ref(
  sottoeventoValue
    ? {
      label: sottoeventi.find((se) => se.value === sottoeventoValue).label,
      value: sottoeventoValue,
    }
    : {
      label: t("dati.batteria"),
      value: "0020",
    }
);
const localizzazione = ref("");
const localizzazioneOk = ref(false);

/*
Sotto Evento	Descrizione	Evento
0003	MOTORE                       	G
0011	INCIDENTE                     	I
0014	MANCANZA CARBURANTE   	G
0015	RUOTE E GOMME      	G
0016	MANCANZA CHIAVI      	G
0020	BATTERIA                    	G
0023	ERRATO CARBURANTE   	G
*/

const errorFunction = () => {
  dataStore.setErrorType("generico");
  dataStore.setShowErrorCaption2(false);
  router.push("/endKo");
};

onMounted(() => {
  var _paq = (window._paq = window._paq || []);
  _paq.push(['FormAnalytics::scanForForms', document.getElementById('formRv' + (dataStore.getIsVariantOriginal ? '3A' : '2B'))]);
  _paq.push(['FormAnalytics::trackForm', document.getElementById('formRv' + (dataStore.getIsVariantOriginal ? '3A' : '2B'))]);
})

watch(
  () => evento.value,
  (value) => {
    if (value.value === "G") {
      _paq.push(['FormAnalytics::scanForForms']);
    }
  }
);

watch(
  () => autostrada.value,
  (value) => {
    if (!value && !estero.value) {
      _paq.push(['FormAnalytics::scanForForms']);
    }
  }
);

watch(
  () => estero.value,
  (value) => {
    if (!value && !autostrada.value) {
      _paq.push(['FormAnalytics::scanForForms']);
    }
  }
);

const okFunction = () => {
  dataStore.setEvento(evento.value.value);
  dataStore.setSottoEvento(sottoevento.value.value);
  dataStore.setAutostrada(autostrada.value);
  dataStore.setEstero(estero.value);
  var _paq = (window._paq = window._paq || []);
  _paq.push(['FormAnalytics::scanForForms', document.getElementById('formRv' + (dataStore.getIsVariantOriginal ? '3A' : '2B'))]);
  _paq.push(['FormAnalytics::trackFormSubmit', document.getElementById('formRv' + (dataStore.getIsVariantOriginal ? '3A' : '2B'))]);
  dataStore.getIsVariantOriginal ? dataStore.setSendData(true) : router.push("/rv2");
};
</script>

<template>
  <form autocomplete="on" :id="'formRv' + (dataStore.getIsVariantOriginal ? '3A' : '2B')">
    <div class="w-full text-center items-stretch flex flex-col py-8 pt-20 min-h-[95vh]">
      <div class="w-full items-center px-2 flex flex-row justify-between align-middle">
        <img src="../assets/arrow_back_ios.svg" alt="Back" class="back h-6 w-6 my-auto" @click="$router.back()"
          v-if="dataStore.getIsVariantOriginal" />
        <div class="h-6 w-6 my-auto" v-else />
        <div class="text-[25px] font-bold italic w-full flex-1">
          {{ $t("dati.tuoi_dati") }}
        </div>
        <div class="h-6 w-6 my-auto" />
      </div>
      <div class="w-full px-8 flex flex-row justify-between align-middle pt-4">
        <div class="relative w-full sm:w-1/2 bg-gray-200 rounded h-2 my-auto">
          <div :style="{ width: dataStore.getIsVariantOriginal ? '100%' : '50%' }"
            class="absolute top-0 h-2 rounded shim-green"></div>
        </div>
        <div class="ml-2">{{ dataStore.getIsVariantOriginal ? '3/3' : '1/2' }}</div>
      </div>
      <div class="w-full flex-1 px-8 pt-4">
        <div class="pt-8">
          <Select v-model="evento" :text="$t('dati.problema_veicolo')" :options="[
            {
              label: $t('dati.incidente'),
              value: 'I',
            },
            {
              label: $t('dati.guasto'),
              value: 'G',
            },
          ]" name="event" />
        </div>
        <div class="pt-8" v-if="evento.value === 'G'">
          <Select v-model="sottoevento" :text="$t('dati.tipo_di_guasto')" :options="sottoeventi" name="subevent" />
        </div>
        <div class="pt-8">
          <div class="text-lg font-bold text-left">{{ $t('dati.sei_autostrada') }}<sup>*</sup></div>
          <div class="w-full items-center flex flex-row justify-between align-middle px-8 pt-4">
            <Radio v-model="autostrada" :text="t('dati.si')" name="highway" :value="true" />
            <Radio v-model="autostrada" :text="t('dati.no')" name="highway" :value="false" />
          </div>
        </div>
        <div class="pt-8">
          <div class="text-lg font-bold text-left">{{ $t('dati.sei_estero') }}<sup>*</sup></div>
          <div class="w-full items-center flex flex-row justify-between align-middle px-8 pt-4">
            <Radio v-model="estero" :text="t('dati.si')" name="abroad" :value="true" />
            <Radio v-model="estero" :text="t('dati.no')" name="abroad" :value="false" />
          </div>
        </div>
        <div v-if="autostrada === false && estero === false" class="pt-8">
          <Location v-model="localizzazione" :placeholder="$t('dati.localizzazione_veicolo')"
            :text="$t('dati.posizione_del_veicolo')" :required="true" @is-valid="(valid) => (localizzazioneOk = valid)"
            name="localization" />
        </div>
      </div>
      <div class="w-full px-8 pb-8">
        <div class="text-xs italic text-right font-light">
          {{ $t("dati.campi_obbligatori") }}
        </div>
      </div>
      <div class="w-full px-8">
        <Button :text="$t('avanti')"
          :disabled="autostrada == null || estero == null || (autostrada === false && estero === false && !localizzazioneOk)"
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
