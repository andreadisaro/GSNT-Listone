<script setup>
import { useDataStore } from "../store/useDataStore";
import SvgIcon from "./SvgIcon.vue";
const dataStore = useDataStore();
defineProps({
  item: Object,
  journalist: String,
  print: Boolean,
});
</script>

<template>
  <div class="flex flex-row space-x-2">
    <div
      class="w-6 sfondoVerdeSemiTrasparente"
      v-for="(day, i) in dataStore.days"
      :key="'journalistDay' + i"
    >
      <SvgIcon
        :class="`${'w-5 h-5 ' + (print ? '' : 'm-auto')}`"
        :style="`${print ? 'margin-left: 0.0625rem' : ''}`"
        :name="item[day] ? 'CheckboxFull' : 'CheckboxEmpty'"
      />
    </div>
    <div class="flex flex-1 text-start">{{ journalist }}</div>
  </div>
</template>

<style lang="postcss" scoped></style>
