<script setup></script>

<template>
  <div
    class="w-full text-center items-stretch flex flex-col py-8 pt-20 min-h-[95vh]"
  >
    <div
      class="w-full items-center px-2 flex flex-row justify-between align-middle"
    >
      <img
        src="../assets/arrow_back_ios.svg"
        alt="Back"
        class="back h-6 w-6 my-auto"
        @click="$router.back()"
      />
      <div class="text-[25px] font-bold italic w-full flex-1">
        {{ $t("dati.telaio_placeholder") }}
      </div>
      <div class="h-6 w-6 my-auto" />
    </div>
    <div class="w-full flex-1 px-8 pt-8">
      <div class="text-[17px]">
        {{ $t("telaio.testo1") }}<br /><br />
        {{ $t("telaio.testo2") }}
      </div>
    </div>
    <div class="w-full flex-1 px-8">
      <img src="/src/assets/telaio.png" alt="Telaio" />
    </div>
    <div class="w-full flex-1 px-8">
      <div class="text-[17px]">
        {{ $t("telaio.testo3") }}
      </div>
    </div>
  </div>
</template>

<style scoped>
.shim-green {
  position: relative;
  overflow: hidden;
  background-color: rgba(1, 98, 77, 0.73);
}
.shim-green::after {
  position: absolute;
  top: 0;
  right: 0;
  bottom: 0;
  left: 0;
  transform: translateX(-100%);
  background-color: #ebebeb;
  content: "";
}
</style>
