import { createWebHistory, createRouter } from "vue-router";
import Home from "../test/components/Home.vue";
import Login from "../test/components/Login.vue";

const routes = [
    { path: "/home", name: "Home", component: Home },
    { path: "/login", name: "Login", component: Login }
];
const router = createRouter({
    history: createWebHistory(),
    routes
});
export default router;