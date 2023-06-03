import { createApp } from "vue";
import "./style.scss";
import App from "./App.vue";
import i18n from "./i18n/i18n";
import router from "./router";
import pinia from "./store/store";
import * as Sentry from "@sentry/vue";

console.log("Versione: 1.0.1");

const app = createApp(App);
const isProd = location.hostname.indexOf("p.acigo.it") > -1;
const isLocalhost = location.hostname.indexOf("localhost") > -1;

if (!isLocalhost) {
  Sentry.init({
    app,
    dsn: isProd
      ? "https://7ca884fe1bbd4d7e88e6531078236057@o4504951030284288.ingest.sentry.io/4505047153311744"
      : "https://a0fef5d3abf4443c9a45edf56ce0bae6@o4504951030284288.ingest.sentry.io/4505047116021760",
    integrations: [
      new Sentry.BrowserTracing({
        routingInstrumentation: Sentry.vueRouterInstrumentation(router),
        tracePropagationTargets: [isProd ? "p.acigo.it" : "t.acigo.it", /^\//],
      }),
    ],
    // Set tracesSampleRate to 1.0 to capture 100%
    // of transactions for performance monitoring.
    // We recommend adjusting this value in production
    tracesSampleRate: 1.0,
  });
}

app.use(i18n);
app.use(router);
app.use(pinia);
app.mount("#app");
