<script setup>
import SvgIcon from "./SvgIcon.vue";
defineProps({
  item: Object,
});
</script>

<template>
  <div class="flex flex-row space-x-2">
    <div class="flex w-6 sfondoVerdeSemiTrasparente justify-center py-0.5">
      <template v-if="item.isAvailable">
        <SvgIcon name="CheckboxFull" class="h-5 w-5" v-if="item.look" />
        <SvgIcon name="CheckboxEmpty" class="h-5 w-5" v-else />
      </template>
      <SvgIcon name="Forbidden" class="h-4 w-4 mt-[1px]" v-else />
    </div>
    <div class="flex w-6 sfondoVerdeSemiTrasparente justify-center py-0.5">
      <template v-if="item.isPlayable">
        <SvgIcon name="CheckboxFull" class="h-5 w-5" v-if="item.play" />
        <SvgIcon name="CheckboxEmpty" class="h-5 w-5" v-else />
      </template>
      <SvgIcon name="Forbidden" class="h-4 w-4 mt-[1px]" v-else />
    </div>
    <div class="flex w-6 sfondoVerdeSemiTrasparente justify-center py-0.5">
      <span v-if="item.bookType" class="font-bold">{{ item.bookType }}</span>
      <template v-else-if="item.isBuyable">
        <SvgIcon name="CheckboxFull" class="h-5 w-5" v-if="item.buy" />
        <SvgIcon name="CheckboxEmpty" class="h-5 w-5" v-else />
      </template>
      <SvgIcon name="Forbidden" class="h-4 w-4 mt-[1px]" v-else />
    </div>
    <div class="flex flex-1 text-start -mt-2">
      {{ item.name }}
    </div>
  </div>
</template>

<style lang="postcss" scoped></style>
