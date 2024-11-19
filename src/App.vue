<script setup>
import Header from "@/components/common/header/Header.vue";
import Footer from "@/components/common/Footer.vue";

// import map from "@/components/plan/make/map/map.vue";

import { useRoute } from "vue-router";
import MapTmp from "@/components/plan/make/map/MapTmp.vue";

const route = useRoute();
</script>

<template>
  <div>
    <header v-if="route.meta.layout !== 'empty'">
      <Header />
    </header>
    <main>
      <RouterView />
      <!-- <MapTmp /> -->
    </main>
    <footer v-if="route.meta.layout !== 'empty'">
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
