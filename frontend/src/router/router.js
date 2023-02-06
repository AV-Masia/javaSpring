import { createWebHistory, createRouter } from "vue-router";
import Index from "../pages/Index.vue";
import Login from "../pages/Login.vue";
import Service from "../pages/Service.vue";
import Success from "../pages/Success.vue";
import User from "../pages/User.vue";

const routes = [
    { path: "/" },
    { path: "/index.html", name: "Index", component: Index },
    { path: "/login.html", name: "Login", component: Login },
    { path: "/user.html", name: "User", component: User },
    { path: "/service.html", name: "Service", component: Service },
    { path: "/success.html", name: "Success", component: Success }
];
const router = createRouter({
    history: createWebHistory(),
    routes
});
export default router;