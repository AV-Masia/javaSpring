<!-- <template>
  <div id="nav">
    <router-link to="/">Home</router-link>
    <router-link to="/home-2">Home-2</router-link>
  </div>
  <router-view/>
</template>

<script>

</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style> -->


<template>
  <div id="nav">
      <router-link to="/">Home</router-link> |
      <router-link to="/about">About</router-link>
  </div>
  <router-view />
</template>

<script>
// import { ref } from "vue";
import {computed} from 'vue';
import {useStore} from "vuex";
// import Vue3RouterTree from "vue3-router-tree";
export default {
  name: "App",
  // provide: { store },
  data() {
    return {
    };
  },
  setup() {
    const store = useStore();

    let products = computed(function () {
      return store.state.products
    });

    let cart = computed(function () {
      return store.state.cart
    });

    return {
      products,
      cart
    }
  },
  components: {
    // hello,
    // Vue3RouterTree,
  },
  mounted() {
    console.log(this.$router.getRoutes());
    console.log(this.products[0]);
  },
};
</script>

<style scoped>
img {
  width: 200px;
}
h1 {
  font-family: Arial, Helvetica, sans-serif;
}
</style>