import { createI18n } from "vue-i18n";
import en from "./en";
import it from "./it";

const messages = {
  en,
  it,
};

const i18n = createI18n({
  legacy: false,
  locale: import.meta.env.VUE_APP_I18N_LOCALE || "it", // set locale
  fallbackLocale: import.meta.env.VUE_APP_I18N_FALLBACK_LOCALE || "en", // set fallback locale
  messages, // set locale messages
  // If you need to specify other options, you can set other options
  // ...
});

export default i18n;
