<script setup>
import { onMounted, ref } from "vue";
import { initTmap, addMarker } from "@/util/tmapLoader";

const props = defineProps({
    courses: {
    type: Array,
  },
});

const map = ref(null);
const markers = ref([]);

console.log(props.courses);

onMounted(() => {
  map.value = initTmap();

  map.value.on("ConfigLoad", () => {
    initializeMarkers(props.courses);
  });
});

const initializeMarkers = (courses) => {
  if (!courses || courses.length === 0) return;

  console.log("!!", courses[0].attraction.latitude, courses[0].attraction.longitude);

  // LatLngBounds 객체 생성
  const bounds = new Tmapv3.LatLngBounds(
    new Tmapv3.LatLng(courses[0].attraction.latitude, courses[0].attraction.longitude)
  );

  console.log(bounds);

  // 새 위치로 마커 추가 및 bounds 확장
  courses.forEach((location) => {
    console.log(location);

    const marker = addMarker(map.value, location.attraction.latitude, location.attraction.longitude);
    markers.value.push("++", marker);

    console.log(marker);

    // bounds 확장
    bounds.extend(new Tmapv3.LatLng(location.attraction.latitude, location.attraction.longitude));
  });

  // 여백 설정
  const margin = {
    top: 100,
    right: 100,
    bottom: 100,
    left: 100,
  };

  // bounds에 여백을 추가하고 지도 뷰 설정
  map.value.fitBounds(bounds, margin);
};
</script>

<template>
  <div id="map_div">
    <!-- {{ courses }} -->
  </div>
</template>

<style scoped>
#map_div {
    width: 100%;
    height: 100%;
    /* border: 1px solid red; */
}
</style>
