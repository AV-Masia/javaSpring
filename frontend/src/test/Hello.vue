<template>
    <div>
      <ul>
        <li v-for="(item, i) in fnacResults" :key="i">{{ item }}</li>
      </ul>
    </div>
  </template>
  
  <script lang="ts">
  export default {
    inject: ["store"],
    computed: {
      fnacResults() {
        return this.store.state.fnacResults;
      },
    },
    mounted() {
      console.log(this.store.state);
    },
  };
  </script>
  
  <style>
  </style>