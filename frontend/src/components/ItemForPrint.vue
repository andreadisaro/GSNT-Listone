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
      <template v-if="item.bookType">
        <template v-if="item.isBuyable">
          <SvgIcon name="CheckboxFull" class="h-5 w-5" v-if="item.buy" />
          <SvgIcon name="CheckboxEmpty" class="h-5 w-5" v-else />
        </template>
        <SvgIcon name="Forbidden" class="h-4 w-4 mt-[1px]" v-else />
      </template>
      <template v-else-if="item.isPlayable">
        <SvgIcon name="CheckboxFull" class="h-5 w-5" v-if="item.play" />
        <SvgIcon name="CheckboxEmpty" class="h-5 w-5" v-else />
      </template>
      <SvgIcon name="Forbidden" class="h-4 w-4 mt-[1px]" v-else />
    </div>
    <div
      class="flex w-6 sfondoVerdeSemiTrasparente justify-center"
      :class="{ 'py-0.5': !item.bookType }"
    >
      <span v-if="item.notBuyableType" class="font-bold -mt-2.5">{{ item.notBuyableType }}</span>
      <span v-else-if="item.bookType" class="font-bold -mt-2">{{
        item.bookType
      }}</span>
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
