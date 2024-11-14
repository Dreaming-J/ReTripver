<script setup>
import Header from "@/components/common/Header.vue";
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

<style scoped></style>
