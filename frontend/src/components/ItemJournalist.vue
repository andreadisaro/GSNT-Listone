<script setup>
import { useDataStore } from "../store/useDataStore";
const dataStore = useDataStore();
defineProps({
  item: Object,
  journalist: String
});
</script>

<template>
  <div class="flex flex-row">
    <div class="w-6" v-for="(day, i) in dataStore.days" :key="'journalistDay' + i">
      <input type="checkbox" :disabled="true" v-model="item[day]" />
    </div>
    <div class="flex-1">{{ journalist }}</div>
  </div>
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