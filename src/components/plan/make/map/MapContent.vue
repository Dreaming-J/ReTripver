<script setup>
import TMapComponont from "@/components/plan/make/map/TMapComponont.vue";

defineProps({
  selectList: {
    type: Object,
  },
});
</script>

<template>
  <div class="map">
    <TMapComponont :select-list="selectList" />
  </div>
</template>

<style scoped>
.map {
  width: 100%;
  height: 100%;
}
</style>
