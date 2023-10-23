import { createRouter, createWebHistory } from "vue-router";
import Root from "./components/Root.vue";

const routes = [{ path: "/", component: Root }];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
