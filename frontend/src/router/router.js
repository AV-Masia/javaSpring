import { createWebHistory, createRouter } from "vue-router";
import Home from "../test/components/Home.vue";
import About from "../test/components/About.vue";

const routes = [
    { path: "/", name: "Home", component: Home },
    { path: "/about", name: "About", component: About }
];
const router = createRouter({
    history: createWebHistory(),
    routes
});
export default router;