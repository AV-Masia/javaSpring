// import { createApp } from 'vue'
// import App from './App'
// // import components from './components/Ui';
// import router from './router';


// // const app = createApp(App)


import { createApp } from "vue";

import router from "./router/router";
import store from "./store/store";

import App from "./App.vue";
import App3 from "./App3.vue";

createApp(App).use(router).use(store).mount("#app");
createApp(App3).mount("#app3");