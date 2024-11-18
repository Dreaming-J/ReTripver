<script setup>
import { RouterView } from "vue-router";

import Logo from "@/assets/img/logo.png";
</script>

<template>
  <div>
    <div class="logo-img">
      <RouterLink :to="{ name: 'main' }">
        <img :src="Logo" width="200px" />
      </RouterLink>
    </div>
    <div class="sign-container">
      <RouterView />
    </div>
  </div>
</template>

<style scoped>
.logo-img {
  height: 100px;
  padding: 20px;
  display: flex;
  align-items: center;
}
.sign-container {
  border: 1px solid lightgray;
  border-radius: 10px;
  width: 600px;
  margin: 0 auto;
  margin-top: 50px;
}
</style>
