<script setup>
import TMapComponont from "@/components/plan/make/map/TMapComponont.vue";

const props = defineProps({
  selectList: {
    type: Object,
  },
});

</script>

<template>
  <div class="map">
    <TMapComponont :select-list="props.selectList" />
  </div>
</template>

<style scoped>
.map {
  width: 100%;
  height: 100%;
}
</style>
