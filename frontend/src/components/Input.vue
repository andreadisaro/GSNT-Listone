<script setup>
import { ref, onMounted, watch } from "vue";
const props = defineProps({
  text: String,
  placeholder: String,
  name: String,
  modelValue: String,
  required: Boolean,
  readonly: Boolean,
  validationRegex: String,
});
const emit = defineEmits(["update:modelValue", "isValid"]);
const error = ref(false);
const validation = () => {
  if (props.required && (props.modelValue === "" || props.modelValue === null)) {
    error.value = true;
  } else {
    error.value = false;
    if (props.validationRegex && props.modelValue) {
      if (props.modelValue.match(new RegExp(props.validationRegex, 'i'))) {
        error.value = false;
      } else {
        error.value = true;
        console.error(
          "Invalid input " + props.modelValue + " for " + props.text
        );
      }
    }
  }
  emit("isValid", !error.value);
};
onMounted(() => {
  if (props.modelValue) validation();
});
watch(() => props.modelValue, () => {
  if (props.modelValue) validation();
})
</script>

<template>
  <div class="input-container">
    <div class="text-lg font-bold">{{ text }}<sup v-if="required">*</sup></div>
    <input :value="modelValue" @input="$emit('update:modelValue', $event.target.value)" :placeholder="placeholder"
      @keyup="validation()" @blur="validation()" class="input" :class="{ error: error }" :readonly="readonly" :name="name"
      :data-matomo-name="name" type="text" />
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
</style>
