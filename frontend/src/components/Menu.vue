<script setup>
import { nextTick } from "vue";
import { useDataStore } from "../store/useDataStore";
import SvgIcon from "./SvgIcon.vue";
import { useModalStore } from "../store/useModalStore";
const dataStore = useDataStore();
const modalStore = useModalStore();
const vaiACategoria = (i) => {
  dataStore.showMenu = false;
  nextTick(() => {
    dataStore.goToRef = i;
  });
};
const setFilter = (filter) => {
  dataStore.setShowFilter(filter);
  dataStore.showMenu = false;
};
</script>

<template>
  <div
    class="w-full text-center items-stretch flex flex-col p-8 pt-20 min-h-[95vh]"
  >
    <div class="w-full items-center flex-1">
      <div class="flex font-extrabold justify-end titoloRosso text-xl">
        MENU
      </div>
      <div class="flex">Vai a:</div>
      <div
        v-for="(category, catI, i) in dataStore.items"
        :key="catI"
        class="ml-4"
      >
        <div
          class="flex font-extrabold"
          :class="i > 0 ? 'my-2' : ''"
          @click="vaiACategoria(i)"
        >
          {{ catI }}
        </div>
      </div>
      <div class="ml-4">
        <div
          class="flex font-extrabold my-2"
          @click="vaiACategoria('redazione')"
        >
          {{ $t("REDAZIONE") }}
        </div>
      </div>
      <hr class="my-2" />
      <div class="flex">Mostra:</div>
      <div class="ml-4">
        <div class="flex flex-row" @click="setFilter('showAll')">
          <div class="w-6">
            <input
              type="checkbox"
              :checked="dataStore.showFilter === 'showAll'"
            />
          </div>
          <div class="flex-1">Tutti gli elementi</div>
        </div>
        <div class="flex flex-row" @click="setFilter('showOnlyLook')">
          <div class="w-6">
            <input
              type="checkbox"
              :checked="dataStore.showFilter === 'showOnlyLook'"
            />
          </div>
          <div class="flex-1">Solo gli elementi che voglio visionare</div>
        </div>
        <div class="flex flex-row" @click="setFilter('showOnlyPlay')">
          <div class="w-6">
            <input
              type="checkbox"
              :checked="dataStore.showFilter === 'showOnlyPlay'"
            />
          </div>
          <div class="flex-1">Solo gli elementi che voglio giocare</div>
        </div>
        <div class="flex flex-row" @click="setFilter('showOnlyBuy')">
          <div class="w-6">
            <input
              type="checkbox"
              :checked="dataStore.showFilter === 'showOnlyBuy'"
            />
          </div>
          <div class="flex-1">Solo gli elementi che voglio comprare</div>
        </div>
        <div class="flex flex-row" @click="setFilter('showOnlySomething')">
          <div class="w-6">
            <input
              type="checkbox"
              :checked="dataStore.showFilter === 'showOnlySomething'"
            />
          </div>
          <div class="flex-1">
            Solo gli elementi con una qualsiasi preferenza
          </div>
        </div>
        <div class="flex flex-row" @click="setFilter('showOnlyNothing')">
          <div class="w-6">
            <input
              type="checkbox"
              :checked="dataStore.showFilter === 'showOnlyNothing'"
            />
          </div>
          <div class="flex-1">Solo gli elementi con nessuna preferenza</div>
        </div>
      </div>
      <hr class="my-2" />
      <div class="flex" @click="modalStore.showInstructionsModal">
        <SvgIcon name="Info" class="h-4 w-4 my-auto mr-2" /><span
          >Mostra istruzioni</span
        >
      </div>
      <hr class="my-2" />
      <div class="flex flex-col">
        <div class="flex flex-row justify-between">
          <a href="https://www.giochisulnostrotavolo.it" target="_blank"
            ><SvgIcon name="Website" class="h-12 w-12 my-auto"
          /></a>
          <a
            href="https://www.facebook.com/GiochiSulNostroTavolo/"
            target="_blank"
            ><SvgIcon name="Facebook" class="h-12 w-12 my-auto"
          /></a>
          <a href="https://twitter.com/giochisntavolo" target="_blank"
            ><SvgIcon name="Twitter-X" class="h-12 w-12 my-auto"
          /></a>
        </div>
        <div class="flex flex-row justify-between">
          <a
            href="https://www.youtube.com/channel/UCIZlT-GQY9BgSmJvqG04GSQ"
            target="_blank"
            ><SvgIcon name="Youtube" class="h-12 w-12 my-auto"
          /></a>
          <a href="https://t.me/giochisulnostrotavolo" target="_blank"
            ><SvgIcon name="Telegram" class="h-12 w-12 my-auto"
          /></a>
          <a
            href="https://www.instagram.com/giochisulnostrotavolo/"
            target="_blank"
            ><SvgIcon name="Instagram" class="h-12 w-12 my-auto"
          /></a>
        </div>
        <div class="flex flex-row justify-between">
          <a
            href="https://www.giochisulnostrotavolo.it/category/approfondimenti/podcast/"
            target="_blank"
            ><SvgIcon
              name="Headphones"
              class="h-12 w-12 my-auto fill-[#5ca040]"
          /></a>
          <a
            href="https://www.tiktok.com/@giochisulnostrotavolo?is_from_webapp=1&amp;sender_device=pc"
            target="_blank"
            ><SvgIcon name="TikTok" class="h-12 w-12 my-auto"
          /></a>
          <a href="https://www.giochisulnostrotavolo.it/feed/" target="_blank"
            ><SvgIcon name="Rss" class="h-12 w-12 my-auto fill-[#f26522]"
          /></a>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped></style>
