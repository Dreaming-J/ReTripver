<template>
  <div id="map_wrap" class="map_wrap">
    <!-- <h1>{{ routeType }}</h1>
    <button @click="addP">추가</button>
    <button @click="removeP(4486)">삭제</button> -->
    <div id="map_div"></div>
  </div>
</template>

<script setup>
import { onMounted, ref, watch } from "vue";
import { useMakePlansStore } from "@/stores/makePlans";
import {
  initTmap,
  addMarker,
  removeMarker,
  onSearchRoute,
  clearRoute,
} from "@/util/tmapLoader";

const store = useMakePlansStore();

const props = defineProps({
  routeType: {
    type: Boolean,
  },
});

const addP = () => {
  store.addItem();
};

const removeP = (no) => {
  store.removeItem(no);

  console.log(store.selectList);
};

const map = ref(null);
const markers = ref([]);

onMounted(() => {
  map.value = initTmap();

  map.value.on("ConfigLoad", () => {
    map.value.setMapType("NORMAL");

    if (store.selectList && store.selectList.length > 0) {
      initializeMarkers(store.selectList);

      initializeRoute(store.selectList, props.routeType);
    }
  });
});

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

const initializeRoute = (locations) => {
  clearRoute();

  console.log("새로 그림!!");
  for (let idx = 0; idx < locations.length - 1; idx++) {
    const start = locations[idx];
    const end = locations[idx + 1];

    console.log("경로 그리기 시작!");
    console.log(idx, start);
    console.log(idx + 1, end);

    onSearchRoute(map.value, start, end, props.routeType);
  }
};

watch(
  () => store.selectList,
  (newList) => {
    initializeMarkers(newList);

    initializeRoute(store.selectList, props.routeType);
  },
  { deep: true }
);

watch(
  () => props.routeType,
  (newType) => {
    console.log(newType);
    initializeRoute(store.selectList);
  }
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
