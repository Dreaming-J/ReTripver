<script setup>
import TMapComponont from "@/components/plan/make/map/TMapComponont.vue";

defineProps({
  routeType: {
    type: Boolean,
  },
});
</script>

<template>
  <div class="map">
    <TMapComponont :route-type="routeType" />
  </div>
</template>

<style scoped>
.map {
  width: 100%;
  height: 100%;
}
</style>
