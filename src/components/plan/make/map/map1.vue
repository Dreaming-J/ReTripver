<script setup>
import { onMounted } from "vue";

const loadTmapAPI = () => {
  return new Promise((resolve, reject) => {
    // 랜덤 도메인 선택 로직
    const nu = Math.floor(Math.random() * (3 - 1 + 1) + 1);
    let domain = "toptmaptile1";
    if (nu === 1) {
      domain = "toptmaptile1";
    } else if (nu === 2) {
      domain = "toptmaptile2";
    } else {
      domain = "toptmaptile3";
    }

    const baseUrl = `https://${domain}.tmap.co.kr/scriptSDKV3/`;

    // CSS 로드
    const cssLink = document.createElement("link");
    cssLink.rel = "stylesheet";
    cssLink.href = `${baseUrl}vsm.css`;
    document.head.appendChild(cssLink);

    // 메인 스크립트 로드
    const script = document.createElement("script");
    // script.src = `${baseUrl}tmapjs3.min.js?version=20231206`;
    script.src =
      "https://apis.openapi.sk.com/tmap/vectorjs?version=1&appKey=Nzgwwy1J3Zsqz0wG9woF7pL1T7Vqflc79F9psGGh";
    script.onload = () => {
      resolve();
    };
    script.onerror = (error) => {
      reject(error);
    };
    document.head.appendChild(script);
  });
};

// TMap 초기화 함수
const initTmap = () => {
  const map = new Tmapv3.Map("map_div", {
    center: new Tmapv3.LatLng(37.566481622437934, 126.98502302169841),
    width: "890px",
    height: "400px",
    zoom: 18,
  });
};

onMounted(async () => {
  try {
    await loadTmapAPI();
    // API 로드 완료 후 지도 초기화
    initTmap();
  } catch (error) {
    console.error("Failed to load TMap API:", error);
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
  margin: 0 auto;
}
</style>
