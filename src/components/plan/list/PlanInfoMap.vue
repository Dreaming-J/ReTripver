<script setup>
import { onMounted, ref, watch } from "vue";
import { initTmap, addMarker, addPolyline } from "@/util/tmapLoader";

const props = defineProps({
    courses: {
    type: Array,
  },
});

const map = ref(null);
const markers = ref([]);
const locations = ref({})

watch(
  () => props.courses,
  (newValue, oldValue) => {
    console.log(props.courses)

    locations.value = props.courses.map(course => ({
      latitude: course.attraction.latitude,
      longitude: course.attraction.longitude,
    }))
    
    map.value.on("ConfigLoad", () => {
      initializeMarkers(locations.value);
      addPolyline(map.value, locations.value);
    });
  },
  { deep: true }
)

onMounted(() => {
  map.value = initTmap();
});

const initializeMarkers = (locations) => {
  if (!locations || locations.length === 0) return;

  console.log("!!", locations[0].latitude, locations[0].longitude);

  // LatLngBounds 객체 생성
  const bounds = new Tmapv3.LatLngBounds(
    new Tmapv3.LatLng(locations[0].latitude, locations[0].longitude)
  );

  // 새 위치로 마커 추가 및 bounds 확장
  locations.forEach((location) => {
    // console.log(location);

    const marker = addMarker(map.value, location.latitude, location.longitude);
    markers.value.push("++", marker);

    console.log(marker);

    // bounds 확장
    bounds.extend(new Tmapv3.LatLng(location.latitude, location.longitude));
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
