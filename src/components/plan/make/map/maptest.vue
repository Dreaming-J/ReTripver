<template>
  <div>
    <div class="mb-4">
      <p id="result" v-if="routeInfo" class="mb-2">
        {{ routeInfo.distance }}, {{ routeInfo.time }}
      </p>
      <select
        id="selectLevel"
        v-model="selectedOption"
        class="mr-2 p-2 border rounded"
      >
        <option value="0">교통최적+추천</option>
        <option value="1">교통최적+무료우선</option>
        <option value="2">교통최적+최소시간</option>
        <option value="3">교통최적+초보</option>
      </select>
      <button
        @click="onSearchRoute"
        class="px-4 py-2 bg-blue-500 text-white rounded"
      >
        적용하기
      </button>
    </div>

    <div id="map_wrap" class="map_wrap">
      <div id="map_div"></div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from "vue";

const map = ref(null);
const resultMarkerArr = ref([]);
const resultdrawArr = ref([]);
const drawInfoArr = ref([]);
const routeInfo = ref(null);
const selectedOption = ref("0");

// 지도 초기화
const initTmap = () => {
  const { Tmapv3 } = window;

  // 1. 지도 생성
  map.value = new Tmapv3.Map("map_div", {
    center: new Tmapv3.LatLng(37.5652045, 126.98702028),
    width: "100%",
    height: "400px",
    zoom: 14,
    zoomControl: true,
    scrollwheel: true,
  });

  // 2. 시작, 도착 마커 생성
  const marker_s = new Tmapv3.Marker({
    position: new Tmapv3.LatLng(37.564991, 126.983937),
    icon: "/upload/tmap/marker/pin_r_m_s.png",
    iconSize: new Tmapv3.Size(24, 38),
    map: map.value,
  });
  resultMarkerArr.value.push(marker_s);

  const marker_e = new Tmapv3.Marker({
    position: new Tmapv3.LatLng(37.566158, 126.98894),
    icon: "/upload/tmap/marker/pin_r_m_e.png",
    iconSize: new Tmapv3.Size(24, 38),
    map: map.value,
  });
  resultMarkerArr.value.push(marker_e);

  // 초기 경로 검색
  onSearchRoute();
};

// 경로 검색
const onSearchRoute = async () => {
  const { Tmapv3 } = window;

  try {
    const headers = {
      "Content-Type": "application/json",
      appKey: "Nzgwwy1J3Zsqz0wG9woF7pL1T7Vqflc79F9psGGh", // 실제 발급받은 AppKey로 대체하세요
    };

    const requestData = {
      startName: "출발지",
      startX: "126.983937",
      startY: "37.564991",
      endName: "도착지",
      endX: "126.98894",
      endY: "37.566158",
      reqCoordType: "WGS84GEO",
      resCoordType: "EPSG3857",
      searchOption: selectedOption.value,
    };

    const response = await fetch(
      "https://apis.openapi.sk.com/tmap/routes/pedestrian?version=1&format=json&callback=result",
      {
        method: "POST",
        headers: headers,
        body: JSON.stringify(requestData),
      }
    );

    const data = await response.json();
    const resultData = data.features;

    // 경로 정보 저장
    routeInfo.value = {
      distance: `총 거리: ${(
        resultData[0].properties.totalDistance / 1000
      ).toFixed(1)}km`,
      time: `총 시간: ${(resultData[0].properties.totalTime / 60).toFixed(
        0
      )}분`,
    };

    // 콘솔에 정보 출력
    console.log(
      `[경로 정보] ${routeInfo.value.distance}, ${routeInfo.value.time}`
    );

    // 기존 경로 초기화
    clearRoute();

    // 새로운 경로 그리기
    for (const feature of resultData) {
      const { geometry, properties } = feature;

      if (geometry.type === "LineString") {
        // 경로 라인 그리기
        const coordinates = geometry.coordinates.map((coord) => {
          const point = new Tmapv3.Point(coord[0], coord[1]);
          const convertPoint = new Tmapv3.Projection.convertEPSG3857ToWGS84GEO(
            point
          );
          return new Tmapv3.LatLng(convertPoint._lat, convertPoint._lng);
        });

        drawInfoArr.value = coordinates;
        drawLine();
      } else {
        // 포인트 마커 추가
        const point = new Tmapv3.Point(
          geometry.coordinates[0],
          geometry.coordinates[1]
        );
        const convertPoint = new Tmapv3.Projection.convertEPSG3857ToWGS84GEO(
          point
        );

        let markerImg = "http://topopen.tmap.co.kr/imgs/point.png";
        let size = new Tmapv3.Size(8, 8);

        if (properties.pointType === "S") {
          markerImg = "/upload/tmap/marker/pin_r_m_s.png";
          size = new Tmapv3.Size(24, 38);
        } else if (properties.pointType === "E") {
          markerImg = "/upload/tmap/marker/pin_r_m_e.png";
          size = new Tmapv3.Size(24, 38);
        }

        const marker = new Tmapv3.Marker({
          position: new Tmapv3.LatLng(convertPoint._lat, convertPoint._lng),
          icon: markerImg,
          iconSize: size,
          map: map.value,
        });

        resultMarkerArr.value.push(marker);
      }
    }
  } catch (error) {
    console.error("경로 검색 중 오류 발생:", error);
  }
};

// 경로 라인 그리기
const drawLine = () => {
  const { Tmapv3 } = window;

  const polyline = new Tmapv3.Polyline({
    path: drawInfoArr.value,
    strokeColor: "#DD0000",
    strokeWeight: 6,
    map: map.value,
  });

  resultdrawArr.value.push(polyline);
};

// 기존 경로 초기화
const clearRoute = () => {
  resultdrawArr.value.forEach((line) => line.setMap(null));
  resultMarkerArr.value.forEach((marker) => marker.setMap(null));
  resultdrawArr.value = [];
  resultMarkerArr.value = [];
  drawInfoArr.value = [];
};

// 컴포넌트 마운트 시 초기화
onMounted(() => {
  if (window.Tmapv3) {
    initTmap();
  } else {
    const script = document.createElement("script");
    script.src =
      "https://apis.openapi.sk.com/tmap/jsv3?version=1.2&appKey=Nzgwwy1J3Zsqz0wG9woF7pL1T7Vqflc79F9psGGh";
    script.onload = () => initTmap();
    document.head.appendChild(script);
  }
});
</script>

<style scoped>
.map_wrap {
  width: 100%;
  position: relative;
}

#map_div {
  width: 100%;
  height: 400px;
}
</style>
