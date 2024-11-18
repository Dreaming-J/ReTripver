<script setup>
import Header from "@/components/common/header/Header.vue";
import Footer from "@/components/common/Footer.vue";

import { RouterView } from "vue-router";
</script>

<template>
  <div>
    <header>
      <Header />
    </header>
    <main>
      <RouterView />
    </main>
    <footer>
      <Footer />
    </footer>
  </div>
</template>

<style scoped>
header {
  width: 100%;
  height: 100px;
}

main {
  margin-left: auto;
  margin-right: auto;
}

footer {
  width: 100%;
  height: 40px;
}
</style>
