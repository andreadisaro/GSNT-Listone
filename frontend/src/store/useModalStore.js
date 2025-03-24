import { ref } from "vue";
import { defineStore } from "pinia";
import { useI18n } from "vue-i18n";

export const useModalStore = defineStore("modal", () => {
  const { t } = useI18n();
  const showInfo = ref(false);
  const infoTitle = ref("");
  const infoMessage = ref("");
  const showPDF = ref(false);
  /*
  const showError = ref(false);
  const showSuccess = ref(false);
  const errorTitle = ref("");
  const errorMessage = ref("");
  const successTitle = ref("");
  const successMessage = ref("");
  function showErrorModal(errorTitleP, errorMessageP) {
    errorTitle.value = errorTitleP || t("Errore");
    errorMessage.value = errorMessageP || t("Si è verificato un errore");
    showError.value = true;
  }
  function hideErrorModal() {
    showError.value = false;
  }
  function showSuccessModal(successTitleP, successMessageP) {
    successTitle.value = successTitleP || t("Operazione completata");
    successMessage.value =
      successMessageP || t("Operazione completata con successo");
    showSuccess.value = true;
  }
  function hideSuccessModal() {
    showSuccess.value = false;
  }*/
  function showInfoModal(infoTitleP, infoMessageP) {
    infoTitle.value = infoTitleP || t("Informazione");
    infoMessage.value = infoMessageP || t("Informazione");
    showInfo.value = true;
  }
  const showInstructions = ref(
    localStorage.getItem("showInstructions") == null
  );
  function hideInfoModal() {
    showInfo.value = false;
    showInstructions.value = false;
    localStorage.setItem("showInstructions", "false");
  }
  function showInstructionsModal() {
    showInfoModal(
      "Benvenuto",
      "Per utilizzare il sito evita la navigazione in incognito e non cancellare la cache del browser, altrimenti perderai i dati inseriti. Si consiglia di utilizzare Google Chrome"
    );
  }
  function showPDFModal() {
    showPDF.value = true;
  }
  function hideConfirmPDFModal() {
    showPDF.value = false;
  }

  return {
    /*showError,
    errorTitle,
    errorMessage,
    showErrorModal,
    hideErrorModal,
    showSuccess,
    successTitle,
    successMessage,
    showSuccessModal,
    hideSuccessModal,*/
    showInfo,
    infoTitle,
    infoMessage,
    showPDF,
    showPDFModal,
    hideConfirmPDFModal,
    showInfoModal,
    hideInfoModal,
    showInstructionsModal,
    showInstructions,
  };
});
