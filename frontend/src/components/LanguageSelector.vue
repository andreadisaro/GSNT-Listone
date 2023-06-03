<script setup>
import { ref } from "vue";
import { useDataStore } from "../store/useDataStore";
import ItFlag from "../assets/flag_it.svg";
import EnFlag from "../assets/flag_en.svg";

const dataStore = useDataStore();
const selected = ref({ label: "Italiano", value: "it", flag: ItFlag });
const open = ref(false);
const options = ref([
  { label: "Italiano", value: "it", flag: ItFlag },
  { label: "English", value: "en", flag: EnFlag },
]);
</script>

<template>
  <div class="custom-select" @blur="open = false" v-if="dataStore.getIsVariantOriginal">
    <div class="selected flex flex-row align-middle" :class="{ open: open }" @click="open = !open">
      <img :src="selected.flag" alt="LanguageFlag" class="flag h-9 w-9 my-auto"
        style="filter: drop-shadow(0px 4px 4px rgba(0, 0, 0, 0.25))" />
      <div class="mx-auto">{{ selected.label }}</div>
      <div class="h-9 w-9 my-auto" />
    </div>
    <div class="items max-h-36" :class="{ selectHide: !open }">
      <div class="scrollbar overflow-y-auto max-h-36">
        <div v-for="(option, i) of options" :key="'language' + i" @click="
          selected = option;
        $i18n.locale = option.value;
        open = false;">
          <div :class="{ selectedItem: selected.value === option.value }" class="flex flex-row items-center">
            <img :src="option.flag" alt="LanguageFlag" class="flag h-9 w-9 my-auto"
              style="filter: drop-shadow(0px 4px 4px rgba(0, 0, 0, 0.25))" />
            <div class="mx-auto">{{ option.label }}</div>
            <div class="h-9 w-9 my-auto" />
          </div>
        </div>
      </div>
    </div>
  </div>
  <div class="custom-select-b" @blur="open = false" v-else>
    <div class="selected flex flex-row align-middle" :class="{ open: open }" @click="open = !open">
      <img :src="selected.flag" alt="LanguageFlag" class="flag h-6 w-6"
        style="filter: drop-shadow(0px 4px 4px rgba(0, 0, 0, 0.25))" />
    </div>
    <div class="items max-h-36" :class="{ selectHide: !open }">
      <div class="scrollbar overflow-y-auto max-h-36">
        <div v-for="(option, i) of options" :key="'language' + i" @click="
          selected = option;
        $i18n.locale = option.value;
        open = false;">
          <div :class="{ selectedItem: selected.value === option.value }" class="flex flex-row items-center">
            <img :src="option.flag" alt="LanguageFlag" class="flag h-9 w-9 my-auto"
              style="filter: drop-shadow(0px 4px 4px rgba(0, 0, 0, 0.25))" />
            <div class="mx-auto">{{ option.label }}</div>
            <div class="h-9 w-9 my-auto" />
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.custom-select-b {
  position: relative;
  width: 100%;
  text-align: left;
  outline: none;
  height: 47px;
  line-height: 47px;
  margin: 0 auto;
  font-size: 22px;
}

.custom-select-b .selected {
  background-color: transparent;
  color: #000;
  cursor: pointer;
  user-select: none;
  background-image: url('data:image/svg+xml;utf8,<svg width="24" height="24" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg"><path d="M7.41098 8.29492L12.001 12.8749L16.591 8.29492L18.001 9.70492L12.001 15.7049L6.00098 9.70492L7.41098 8.29492Z" fill="white"/></svg>');
  background-repeat: no-repeat;
  background-position-x: 100%;
  background-position-y: 50%;
}

.custom-select-b .selected.open {
  background-image: url('data:image/svg+xml;utf8,<svg width="24" height="24" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg"><path d="M16.59 15.7051L12 11.1251L7.41 15.7051L6 14.2951L12 8.29508L18 14.2951L16.59 15.7051Z" fill="white"/></svg>');
}

.custom-select-b .selected:after {
  position: absolute;
  content: "";
  top: 22px;
  right: 1em;
  width: 0;
  height: 0;
  /*border: 5px solid transparent;*/
  border: 0;
  /*disattiva l'indicatore di apertura di default*/
  border-color: #000 transparent transparent transparent;
}

.custom-select-b .items {
  width: 236px;
  color: #000;
  overflow: hidden;
  position: fixed;
  right: 5px;
  margin-top: 20px;
  z-index: 1;
  background: #ffffff;
  border: 0.5px solid #cacaca;
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
}

.custom-select-b .items>div>div {
  color: #000;
  cursor: pointer;
  user-select: none;
  border-bottom: 1px solid rgba(234, 234, 234, 1);
  margin: 0 0.5em;
  height: 40px;
  line-height: 35px;
}

.custom-select-b .items>div>div>div {
  padding-left: 1em;
  margin: 2px;
}

.custom-select-b .items>div>div>div.selectedItem {
  background: rgba(212, 227, 255, 0.64);
  border-radius: 5px;
  font-weight: 600;
}

.custom-select {
  position: relative;
  width: 100%;
  text-align: left;
  outline: none;
  height: 47px;
  line-height: 47px;
  margin: 0 auto;
  font-size: 22px;
}

.custom-select .selected {
  background-color: transparent;
  border-radius: 0px;
  border-bottom: 1px solid #6b6a6a;
  color: #000;
  padding-left: 1em;
  cursor: pointer;
  user-select: none;
  background-image: url('data:image/svg+xml;utf8,<svg width="24" height="24" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg"><path d="M7.41098 8.29492L12.001 12.8749L16.591 8.29492L18.001 9.70492L12.001 15.7049L6.00098 9.70492L7.41098 8.29492Z" fill="black"/></svg>');
  background-repeat: no-repeat;
  background-position-x: 95%;
  background-position-y: 50%;
}

.custom-select .selected.open {
  background-image: url('data:image/svg+xml;utf8,<svg width="24" height="24" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg"><path d="M16.59 15.7051L12 11.1251L7.41 15.7051L6 14.2951L12 8.29508L18 14.2951L16.59 15.7051Z" fill="black"/></svg>');
}

.custom-select .selected:after {
  position: absolute;
  content: "";
  top: 22px;
  right: 1em;
  width: 0;
  height: 0;
  /*border: 5px solid transparent;*/
  border: 0;
  /*disattiva l'indicatore di apertura di default*/
  border-color: #000 transparent transparent transparent;
}

.custom-select .items {
  color: #000;
  overflow: hidden;
  position: absolute;
  left: 0;
  right: 0;
  margin-top: 2px;
  z-index: 1;
  background: #ffffff;
  border: 0.5px solid #cacaca;
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
}

.custom-select .items>div>div {
  color: #000;
  cursor: pointer;
  user-select: none;
  border-bottom: 1px solid rgba(234, 234, 234, 1);
  margin: 0 1em;
  height: 40px;
  line-height: 35px;
}

.custom-select .items>div>div>div {
  padding-left: 1em;
  margin: 2px;
}

.custom-select .items>div>div>div.selectedItem {
  background: rgba(212, 227, 255, 0.64);
  border-radius: 5px;
  font-weight: 600;
}

.selectHide {
  display: none;
}
</style>
