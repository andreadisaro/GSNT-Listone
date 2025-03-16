import { createRouter, createWebHistory } from "vue-router";
import Root from "./components/Root.vue";
import Vote from "./components/Vote.vue";
import Standings from "./components/Standings.vue";

const routes = [
  { path: "/", component: Root },
  { path: "/vote", component: Vote },
  { path: "/vote/:id", component: Vote },
  { path: "/standings", component: Standings },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
