<script setup>
const props = defineProps({
  text: String,
  onclick: Function | Promise,
  disabled: Boolean,
});
const buttonOnclick = () => {
  if (props.disabled) {
    var _paq = (window._paq = window._paq || []);
    _paq.push(['trackEvent', 'Errori', 'BottoneDisabilitato', props.text]);
  }
  else {
    props.onclick();
  }
}
</script>

<template>
  <div class="button m-auto" @click="buttonOnclick">
    <div class="button-text text-[22px]" :class="{ disabled }">
      {{ text }}
    </div>
    <div class="button-line h-1" :class="{ disabled }" />
  </div>
</template>

<style scoped>
.button {
  width: 155px;
  height: 40px;
  filter: drop-shadow(0px 4px 4px rgba(0, 0, 0, 0.25));
  color: #016953;
  line-height: 27px;
}

.button-text.disabled {
  color: #707271;
}

.button-line {
  background: linear-gradient(180deg, #04866a 0%, #005a47 100%);
  border-radius: 20px;
}

.button-line.disabled {
  background: linear-gradient(180deg, #949796 0%, #555656 100%);
}
</style>
