import { createApp } from "vue";
import "./style.scss";
import App from "./App.vue";
import i18n from "./i18n/i18n";
import router from "./router";
import pinia from "./store/store";

console.log("Versione: 1.0.0");

const app = createApp(App);

app.use(i18n);
app.use(router);
app.use(pinia);
app.mount("#app");
