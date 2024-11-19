<template>
  <div id="map_wrap" class="map_wrap">
    <div id="map_div"></div>
  </div>
</template>

<script setup>
import { onMounted, ref } from "vue";
import { initTmap } from "@/util/tmapLoader";

defineProps({
  selectList: {
    type: Object,
  },
});

onMounted(() => {
  initTmap();
});

var marker = new Tmapv3.Marker({
  position: new Tmapv3.LatLng(selectList.value[0].lat, selectList.value[0].lng),
  map: map,
});
</script>

<style scoped>
.map_wrap {
  width: 100%;
  height: 100%;
}

#map_div {
  width: 100%;
  height: 100%;
}
</style>
