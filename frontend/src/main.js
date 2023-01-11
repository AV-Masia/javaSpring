import { createApp } from "vue";
import { BootstrapVue3 } from 'bootstrap-vue-3'

import router from "./router/router";
import store from "./store/store";

import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue-3/dist/bootstrap-vue-3.css'



import App from "./App.vue";
// import App3 from "./App3.vue";

createApp(App)
    .use(router)
    .use(store)
    .use(BootstrapVue3)
    .mount("#app");
// createApp(App3).mount("#app3");