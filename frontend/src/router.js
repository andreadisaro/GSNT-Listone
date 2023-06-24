import { createRouter, createWebHistory } from "vue-router";
import Welcome from "./components/Welcome.vue";
import Root from "./components/Root.vue";

const routes = [
  { path: "/", component: Root },
  { path: "/welcome", component: Welcome },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
