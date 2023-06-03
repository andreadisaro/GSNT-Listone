<script setup>
import Button from "./Button.vue";
import LanguageSelector from "./LanguageSelector.vue";
import { useDataStore } from "../store/useDataStore";
import { useRouter } from "vue-router";
const dataStore = useDataStore();
const router = useRouter();
const onclick = () => {
  var _paq = (window._paq = window._paq || []);
  _paq.push(['trackEvent', 'Click', 'PrimoAvanti', 'Ok']);
  router.push('/rv1');
}
</script>

<template>
  <div class="w-full text-center items-stretch flex flex-col p-8 pt-20 min-h-[95vh]">
    <div class="w-full items-center flex-1">
      <img src="../assets/assistant.png" alt="Assistant" class="imgAssistant mt-10" />
    </div>
    <div class="text-[25px] font-semibold w-full flex-1">
      {{ $t("welcome.title") }}
    </div>
    <div class="text-sm font-light italic w-full flex-1" v-if="dataStore.getPrivacyLink"
      v-html="$t('welcome.privacy_policy', { link: dataStore.getPrivacyLink })" />
    <div class="w-full flex-1">
      <div class="text-xs italic text-left font-light">
        {{ $t("welcome.seleziona") }}
      </div>
      <div class="pt-8">
        <LanguageSelector />
      </div>
    </div>
    <div class="w-full">
      <Button :text="$t('avanti')" :disabled="(!dataStore.getToken || !dataStore.numChiamato) && !dataStore.demo"
        :onclick="onclick" />
    </div>
  </div>
</template>

<style scoped>
.imgAssistant {
  height: 191px;
  width: 123px;
  margin-left: auto;
  margin-right: auto;
}
</style>
