<script setup>
import { ref } from "vue";
const props = defineProps({
  options: Array,
  text: String,
  name: String,
  modelValue: Object,
});
defineEmits(["update:modelValue"]);
const open = ref(false);
</script>

<template>
  <div class="text-lg font-bold text-left">{{ text }}<sup>*</sup></div>
  <select :value="modelValue.value" :name="name" :data-matomo-name="name"
    @change="$emit('update:modelValue', props.options.find(option => option.value === $event.target.value))"
    class="custom-select" :class="{ open }" @click="$event.preventDefault(); open = !open"
    @mousedown="$event.preventDefault()" @mouseup="$event.preventDefault()">
    <option v-for="( option, i ) of options" :key="name + 'hidden' + i" :value="option.value" class="selectHide"
      :class="{ selectedItem: modelValue.value === option.value }">{{
        option.label }}</option>
  </select>
  <div class="items max-h-36 mx-8" :class="{ selectHide: !open }" @blur="open = false">
    <div class="scrollbar overflow-y-auto max-h-36">
      <div v-for="(option, i) of options" :key="name + i" @click="() => {
        $emit('update:modelValue', option);
        open = false;
      }">
        <div :class="{ selectedItem: modelValue.value === option.value }">
          {{ option.label }}
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.custom-select {
  position: relative;
  width: 100%;
  text-align: left;
  outline: none;
  height: 47px;
  line-height: 47px;
  font-size: 16px;
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
  -moz-appearance: none;
  /* Firefox */
  -webkit-appearance: none;
  /* Safari and Chrome */
  appearance: none;
}

.custom-select.open {
  background-image: url('data:image/svg+xml;utf8,<svg width="24" height="24" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg"><path d="M16.59 15.7051L12 11.1251L7.41 15.7051L6 14.2951L12 8.29508L18 14.2951L16.59 15.7051Z" fill="black"/></svg>');
}

.custom-select select {
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

.items {
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

.items>div>div {
  color: #000;
  cursor: pointer;
  user-select: none;
  border-bottom: 1px solid rgba(234, 234, 234, 1);
  margin: 0 1em;
  height: 40px;
  line-height: 35px;
}

.items>div>div>div {
  padding-left: 1em;
  margin: 2px;
}

.items>div>div>div.selectedItem {
  background: rgba(212, 227, 255, 0.64);
  border-radius: 5px;
  font-weight: 600;
}

.selectHide {
  display: none;
}
</style>
