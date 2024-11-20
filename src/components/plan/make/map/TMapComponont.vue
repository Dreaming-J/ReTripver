<template>
  <div id="map_wrap" class="map_wrap">
    <div id="map_div"></div>
  </div>
</template>

<script setup>
import { onMounted, ref, watch } from "vue";
import { initTmap, addMarker, removeMarker } from "@/util/tmapLoader";

const props = defineProps({
  selectList: {
    type: Object,
  },
});

// const selectList = ref([
//   {no:1, lat:37.56520450, lng:126.98602028},
//   {no:2, lat:37.56520450, lng:126.98702028},
// ]);

const map = ref(null);
const markers = ref([]);

onMounted(() => {
  map.value = initTmap();

  if (props.selectList && props.selectList.length > 0) {
    initializeMarkers(props.selectList);
  }
});

// 마커 초기화 함수
const initializeMarkers = (locations) => {
  // 기존 마커 삭제
  clearAllMarkers();

  if (!locations || locations.length === 0) return;

  // LatLngBounds 객체 생성
  const bounds = new Tmapv3.LatLngBounds(
    new Tmapv3.LatLng(locations[0].lat, locations[0].lng)
  );

  // 새 위치로 마커 추가 및 bounds 확장
  locations.forEach((location) => {
    const marker = addMarker(map.value, location.lat, location.lng);
    markers.value.push(marker);

    // bounds 확장
    bounds.extend(new Tmapv3.LatLng(location.lat, location.lng));
  });

  // 여백 설정
  const margin = {
    top: 50,
    right: 50,
    bottom: 50,
    left: 50,
  };

  // bounds에 여백을 추가하고 지도 뷰 설정
  map.value.fitBounds(bounds, margin);
};

// 모든 마커 삭제 함수
const clearAllMarkers = () => {
  markers.value.forEach((marker) => {
    marker.setMap(null);
  });
  markers.value = [];
};

watch(
  () => props.selectList,
  (newList) => {
    initializeMarkers(newList);
  },
  { deep: true }
);
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
