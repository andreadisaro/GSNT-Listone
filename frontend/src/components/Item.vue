<script setup>
import SvgIcon from "./SvgIcon.vue";
defineProps({
  item: Object,
});
</script>

<template>
  <div class="flex flex-row space-x-2">
    <div class="w-6 sfondoVerdeSemiTrasparente">
      <input
        class="w-4 h-4"
        type="checkbox"
        v-if="item.isAvailable"
        v-model="item.look"
      />
      <SvgIcon name="Forbidden" class="h-4 w-4 m-auto" v-else />
    </div>
    <div class="w-6 sfondoVerdeSemiTrasparente">
      <input
        class="w-4 h-4"
        type="checkbox"
        v-if="item.isPlayable"
        v-model="item.play"
      />
      <SvgIcon name="Forbidden" class="h-4 w-4 m-auto" v-else />
    </div>
    <div class="w-6 sfondoVerdeSemiTrasparente">
      <span v-if="item.bookType" class="font-bold">{{ item.bookType }}</span>
      <input
        class="w-4 h-4"
        type="checkbox"
        v-model="item.buy"
        v-else-if="item.isBuyable"
      />
      <SvgIcon name="Forbidden" class="h-4 w-4 m-auto" v-else />
    </div>
    <div class="flex-1">
      {{ item.name }}
    </div>
  </div>
</template>

<style lang="postcss" scoped></style>
