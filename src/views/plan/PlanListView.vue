<script setup>
import { useRoute } from "vue-router";

const route = useRoute();
const sidoCode = route.params.sidoCode;
</script>

<template>
  <div>
    <h1>{{ sidoCode }}</h1>
  </div>
</template>

<style scoped></style>
