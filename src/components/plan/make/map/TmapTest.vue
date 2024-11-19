<script setup>
import { initTmap } from "@/util/init-tmap.js";
import { onMounted } from "vue";

onMounted(async () => {
  try {
    const map = await initTmap();
    console.log("Map initialized:", map);
  } catch (error) {
    console.error("Failed to initialize map:", error);
  }
});
</script>

<template>
  <div>
    <div id="map_div"></div>
  </div>
</template>

<style scoped>
#map_div {
  width: 890px;
  height: 400px;
}
</style>
