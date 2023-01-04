<template>
    <button :class="type">
      <slot> </slot>
    </button>
  </template>
  <script lang="ts">
  export default {
    name: "VButton",
    props: ["type"],
  };
  </script>