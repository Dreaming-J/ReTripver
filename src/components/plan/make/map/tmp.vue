<script setup>
import { onMounted } from "vue";
import { loadTmapAPI, initTmap } from "@/util/tmapLoader";

onMounted(async () => {
  try {
    await loadTmapAPI();
    // API 로드 완료 후 지도 초기화
    const map = initTmap();

    //EPSG:3857 좌표계를 생성합니다.
    var epsg3857 = new Tmapv3.Point(14136131, 4518172);
    // WGS84 좌표계로 변환합니다
    var wgs84 = Tmapv3.Projection.convertEPSG3857ToWGS84GEO(epsg3857);

    // 마커를 생성합니다.
    var marker1 = new Tmapv3.Marker({
      position: wgs84,
      icon: "/upload/tmap/marker/pin_b_m_a.png",
      map: map,
    });

    // 지도를 이동시킵니다.
    map.setCenter(wgs84);
  } catch (error) {
    console.error("Failed to load TMap API:", error);
  }
});
</script>

<template>
  <div class="map-container">
    <div id="map_div"></div>
  </div>
</template>

<style scoped>
.map-container {
  width: 100%;
  height: 100%;
}

#map_div {
  width: 100%;
  height: 100%;
}
</style>
