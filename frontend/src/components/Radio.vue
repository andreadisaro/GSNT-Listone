<script setup>
import { ref, computed } from "vue";
import Yes from "../assets/radio-on.svg";
import No from "../assets/radio-off.svg";
const props = defineProps({
    text: String,
    name: String,
    modelValue: Boolean,
    value: Boolean
});
defineEmits(["update:modelValue"]);
const isChecked = computed(() => props.modelValue === props.value);
const input = ref(null);
</script>

<template>
    <label class="wrapper flex flex-row">
        <input class="checkbox" type="radio" :checked="isChecked" :value="value" ref="input"
            @change="$emit('update:modelValue', $event.target.value == 'true')" :name="name" :data-matomo-name="name" />
        <img :src="isChecked ? Yes : No" alt="RadioYes" class="h-6 w-6 my-auto mr-4" @click="input.focus()" />
        <div @click="input.focus()">{{ text }}</div>
    </label>
</template>
  
<style lang="postcss" scoped>
/* Customize the label (the wrapper) */
.wrapper {
    position: relative;
    margin-bottom: 6px;
    cursor: pointer;
    font-size: 22px;
    -webkit-user-select: none;
    -moz-user-select: none;
    -ms-user-select: none;
    user-select: none;
    font-size: 16px;
}

/* Hide the browser's default radio button */
.wrapper input {
    position: absolute;
    opacity: 0;
    cursor: pointer;
    height: 0;
    width: 0;
}

/* Create a custom radio button */
.checkmark {
    position: absolute;
    top: 0;
    left: 0;
    height: 21px;
    width: 21px;
    border-radius: 50%;
    background-color: #eee;
    border: 1px solid #ccc;
}

/* On mouse-over, add a grey background color */
.wrapper:hover input~.checkmark {
    background-color: #ccc;
}

/* When the radio button is checked, add a blue background */
.wrapper input:checked~.checkmark {
    background-color: #1CD4A7;
}

/* Create the indicator (the dot/circle - hidden when not checked) */
.checkmark:after {
    content: "";
    position: absolute;
    display: none;
}

/* Show the indicator (dot/circle) when checked */
.wrapper input:checked~.checkmark:after {
    display: block;
}

/* Style the indicator (dot/circle) */
.wrapper .checkmark:after {
    top: 5px;
    left: 6px;
    width: 8px;
    height: 8px;
    border-radius: 50%;
    background: white;
}
</style>