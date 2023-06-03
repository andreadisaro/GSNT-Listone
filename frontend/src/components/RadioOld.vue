<script setup>
import { ref } from "vue";
import Yes from "../assets/radio-on.svg";
import No from "../assets/radio-off.svg";
const props = defineProps({
  text: String,
  name: String,
  modelValue: Boolean,
});
defineEmits(["update:modelValue"]);

const yes = ref(props.modelValue === true);
const no = ref(props.modelValue === false);
const input = ref(null);
</script>

<template>
  <input :value="modelValue" class="inputHidden" :name="name" ref="input" :data-matomo-name="name" type="text" />
  <div class="text-lg font-bold text-left">{{ text }}<sup>*</sup></div>
  <div class="w-full items-center flex flex-row justify-between align-middle px-8 pt-4">
    <div class="flex flex-row space-x-4">
      <img :src="yes ? Yes : No" alt="RadioYes" class="h-6 w-6 my-auto" @click="
        yes = true;
      no = false;
      $emit('update:modelValue', true);
      input.focus()
        " />
      <div>{{ $t("dati.si") }}</div>
    </div>
    <div class="flex flex-row space-x-4">
      <img :src="no ? Yes : No" alt="RadioNo" class="h-6 w-6 my-auto" @click="
        yes = false;
      no = true;
      $emit('update:modelValue', false);
      input.focus()
        " />
      <div>{{ $t("dati.no") }}</div>
    </div>
  </div>
</template>

<style scoped>
.input-container {
  position: relative;
  width: 100%;
  text-align: left;
  outline: none;
  height: 47px;
  line-height: 47px;
  font-size: 22px;
  height: auto;
}

.input {
  background-color: transparent;
  border-radius: 0px;
  border-bottom: 1px solid #6b6a6a;
  color: #000;
  padding-left: 1em;
  cursor: pointer;
  user-select: none;
  font-size: 16px;
  width: 100%;
}

.input.error {
  border-bottom: 2px solid red;
  color: red;
}

.input:focus {
  outline: none;
}

.input::placeholder {
  font-style: italic;
  font-weight: 200;
}

.inputHidden {
  display: none;
}
</style>
