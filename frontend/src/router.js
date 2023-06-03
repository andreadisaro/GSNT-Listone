import { createRouter, createWebHistory } from "vue-router";
import Welcome from "./components/Welcome.vue";
import RiconoscimentoVeicolo1 from "./components/RiconoscimentoVeicolo1.vue";
import RiconoscimentoVeicolo1Telaio from "./components/RiconoscimentoVeicolo1Telaio.vue";
import Telaio from "./components/Telaio.vue";
import RiconoscimentoVeicolo2 from "./components/RiconoscimentoVeicolo2.vue";
import RiconoscimentoVeicolo3 from "./components/RiconoscimentoVeicolo3.vue";
import EndOk from "./components/EndOk.vue";
import EndKo from "./components/EndKo.vue";
import Root from "./components/Root.vue";

const routes = [
  { path: "/", component: Root },
  { path: "/welcome", component: Welcome },
  { path: "/rv1", component: RiconoscimentoVeicolo1 },
  { path: "/rv2", component: RiconoscimentoVeicolo2 },
  { path: "/rv3", component: RiconoscimentoVeicolo3 },
  { path: "/rv1telaio", component: RiconoscimentoVeicolo1Telaio },
  { path: "/telaio", component: Telaio },
  { path: "/endOk", component: EndOk },
  { path: "/endKo", component: EndKo },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

router.beforeEach((to, from) => {
  var _paq = (window._paq = window._paq || []);
  if (_paq) {
    _paq.push(["setCustomUrl", to.path]);
    _paq.push(["setDocumentTitle", to.path]);
    _paq.push(["trackPageView"]);
  }
  return true;
});

export default router;
