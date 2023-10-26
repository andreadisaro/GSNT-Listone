<script setup>
import { useModalStore } from "../store/useModalStore";
import { useDataStore } from "../store/useDataStore";
const dataStore = useDataStore();
const modalStore = useModalStore();
</script>

<template>
  <!-- BEGIN: Modal Content -->

  <!--Modal-->
  <div
    class="modal fixed w-full h-full top-0 left-0 flex items-center justify-center"
    v-if="modalStore.showPDF"
  >
    <div
      class="modal-overlay absolute w-full h-full bg-gray-900 opacity-50"
    ></div>

    <div
      class="modal-container bg-white w-11/12 md:max-w-md mx-auto rounded shadow-lg z-50 overflow-y-auto"
    >
      <div
        class="modal-close absolute top-0 right-0 cursor-pointer flex flex-col items-center mt-4 mr-4 text-white text-sm z-50"
        @click="modalStore.hideConfirmPDFModal()"
      >
        <svg
          class="fill-current text-white"
          xmlns="http://www.w3.org/2000/svg"
          width="18"
          height="18"
          viewBox="0 0 18 18"
        >
          <path
            d="M14.53 4.53l-1.06-1.06L9 7.94 4.53 3.47 3.47 4.53 7.94 9l-4.47 4.47 1.06 1.06L9 10.06l4.47 4.47 1.06-1.06L10.06 9z"
          ></path>
        </svg>
        <span class="text-sm">(Esc)</span>
      </div>

      <!-- Add margin if you want to see some of the overlay behind the modal-->
      <div class="modal-content py-4 text-left px-6">
        <!--Title-->
        <div class="flex justify-between items-center pb-3">
          <p class="text-2xl font-bold">Genera PDF</p>
          <div class="modal-close cursor-pointer z-50">
            <svg
              class="fill-current text-black"
              xmlns="http://www.w3.org/2000/svg"
              width="18"
              height="18"
              viewBox="0 0 18 18"
            >
              <path
                d="M14.53 4.53l-1.06-1.06L9 7.94 4.53 3.47 3.47 4.53 7.94 9l-4.47 4.47 1.06 1.06L9 10.06l4.47 4.47 1.06-1.06L10.06 9z"
              ></path>
            </svg>
          </div>
        </div>

        <!--Body-->
        <p>
          Prima di generare il PDF, assicurati di aver impostato il filtro che
          desideri sotto "Mostra" nel menù. Verranno esportati gli stessi
          elementi che visualizzi. Il processo richiederà un po' di tempo.
          <br /><br />Nota: su Android, dopo il caricamento il file sarà
          disponibile nei download, trascinando il menù a tendina del telefono
          dall'alto dovresti trovare una notifica dell'avvenuto download
        </p>

        <!--Footer-->
        <div class="flex justify-end pt-2">
          <button
            type="button"
            @click="modalStore.hideConfirmPDFModal"
            class="btn rounded-lg w-24 mr-1"
            style="border: 2px solid #5ca040"
          >
            Annulla
          </button>
          <button
            class="modal-close px-4 bg-[#5ca040] p-3 rounded-lg text-white"
            @click="
              dataStore.makePdf = true;
              modalStore.hideConfirmPDFModal();
            "
          >
            Procedi
          </button>
        </div>
      </div>
    </div>
  </div>
  <!-- END: Modal Content -->
</template>
