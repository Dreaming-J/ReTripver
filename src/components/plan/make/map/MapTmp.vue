<template>
  <div id="map_wrap" class="map_wrap">
    <div id="map_div"></div>
  </div>
</template>

<script setup>
import { onMounted } from "vue";

let map;
let marker_s, marker_e;
let resultMarkerArr = [];
let drawInfoArr = [];
let resultInfoArr = [];
const { Tmapv3 } = window;

onMounted(() => {
  initTmap();
});

function initTmap() {
  resultMarkerArr = [];

  // 1. 지도 띄우기
  map = new Tmapv3.Map("map_div", {
    center: new Tmapv3.LatLng(37.405278291509404, 127.12074279785197),
    zoom: 14,
    zoomControl: true,
    scrollwheel: true,
  });

  // 2. 시작, 도착 심볼 찍기
  // 시작
  marker_s = new Tmapv3.Marker({
    position: new Tmapv3.LatLng(37.402688, 127.103259),
    icon: "/upload/tmap/marker/pin_r_m_s.png",
    iconSize: new Tmapv3.Size(24, 38),
    map: map,
  });
  resultMarkerArr.push(marker_s);

  // 도착
  marker_e = new Tmapv3.Marker({
    position: new Tmapv3.LatLng(37.414382, 127.142571),
    icon: "/upload/tmap/marker/pin_r_m_e.png",
    iconSize: new Tmapv3.Size(24, 38),
    map: map,
  });
  resultMarkerArr.push(marker_e);

  // 3. 경유지 심볼 찍기
  const waypoints = [
    {
      lat: 37.399569,
      lng: 127.10379,
      icon: "/upload/tmap/marker/pin_b_m_1.png",
    },
    {
      lat: 37.402748,
      lng: 127.108913,
      icon: "/upload/tmap/marker/pin_b_m_2.png",
    },
    {
      lat: 37.397153,
      lng: 127.113403,
      icon: "/upload/tmap/marker/pin_b_m_3.png",
    },
    {
      lat: 37.410135,
      lng: 127.12121,
      icon: "/upload/tmap/marker/pin_b_m_4.png",
    },
    {
      lat: 37.3994,
      lng: 127.123296,
      icon: "/upload/tmap/marker/pin_b_m_5.png",
    },
    {
      lat: 37.406327,
      lng: 127.130933,
      icon: "/upload/tmap/marker/pin_b_m_6.png",
    },
    {
      lat: 37.413227,
      lng: 127.127337,
      icon: "/upload/tmap/marker/pin_b_m_7.png",
    },
  ];

  for (let i = 0; i < waypoints.length; i++) {
    const marker = new Tmapv3.Marker({
      position: new Tmapv3.LatLng(waypoints[i].lat, waypoints[i].lng),
      icon: waypoints[i].icon,
      iconSize: new Tmapv3.Size(24, 38),
      map: map,
    });
    resultMarkerArr.push(marker);
  }
}

function onSearchRoute() {
  const searchOption = document.getElementById("selectLevel").value;

  const headers = {
    "Content-Type": "application/json",
    appKey: "zrw3Ybf3xhaeiVAoqNFRs6QV3bDPpWWfa9fao7fR", // 실제 발급받은 AppKey로 대체하세요
  };

  const requestData = {
    startName: "출발지",
    startX: "127.103259",
    startY: "37.402688",
    startTime: "201708081103",
    endName: "도착지",
    endX: "127.142571",
    endY: "37.414382",
    viaPoints: [
      {
        viaPointId: "test01",
        viaPointName: "name01",
        viaX: "127.103790",
        viaY: "37.399569",
      },
      {
        viaPointId: "test02",
        viaPointName: "name02",
        viaX: "127.108913",
        viaY: "37.402748",
      },
      {
        viaPointId: "test03",
        viaPointName: "name03",
        viaX: "127.113403",
        viaY: "37.397153",
      },
      {
        viaPointId: "test04",
        viaPointName: "name04",
        viaX: "127.121210",
        viaY: "37.410135",
      },
      {
        viaPointId: "test05",
        viaPointName: "name05",
        viaX: "127.123296",
        viaY: "37.399400",
      },
      {
        viaPointId: "test06",
        viaPointName: "name06",
        viaX: "127.130933",
        viaY: "37.406327",
      },
      {
        viaPointId: "test07",
        viaPointName: "name07",
        viaX: "127.127337",
        viaY: "37.413227",
      },
    ],
    reqCoordType: "WGS84GEO",
    resCoordType: "EPSG3857",
    searchOption: searchOption,
  };

  fetch(
    "https://apis.openapi.sk.com/tmap/routes/routeSequential30?version=1&format=json",
    {
      method: "POST",
      headers: headers,
      body: JSON.stringify(requestData),
    }
  )
    .then((response) => response.json())
    .then((data) => {
      const resultData = data.properties;
      const resultFeatures = data.features;

      // 결과 출력
      const tDistance =
        "총 거리 : " + (resultData.totalDistance / 1000).toFixed(1) + "km,  ";
      const tTime =
        "총 시간 : " + (resultData.totalTime / 60).toFixed(0) + "분,  ";
      const tFare = "총 요금 : " + resultData.totalFare + "원";

      document.getElementById("result").innerText = tDistance + tTime + tFare;

      // 기존 라인 초기화
      if (resultInfoArr.length > 0) {
        for (let i in resultInfoArr) {
          resultInfoArr[i].setMap(null);
        }
        resultInfoArr = [];
      }

      for (let i in resultFeatures) {
        const geometry = resultFeatures[i].geometry;
        const properties = resultFeatures[i].properties;
        let polyline_;

        drawInfoArr = [];

        if (geometry.type == "LineString") {
          for (let j in geometry.coordinates) {
            // 경로들의 결과값(구간)들을 포인트 객체로 변환
            const x = geometry.coordinates[j][0];
            const y = geometry.coordinates[j][1];
            const point = new Tmapv3.Point(x, y);
            // 좌표 변환
            const latlng = Tmapv3.Projection.convertEPSG3857ToWGS84GEO(point);
            const convertChange = new Tmapv3.LatLng(latlng._lat, latlng._lng);

            drawInfoArr.push(convertChange);
          }

          polyline_ = new Tmapv3.Polyline({
            path: drawInfoArr,
            strokeColor: "#FF0000",
            strokeWeight: 6,
            map: map,
          });
          resultInfoArr.push(polyline_);
        } else {
          let markerImg = "";
          let size;

          if (properties.pointType == "S") {
            // 출발지 마커
            markerImg = "src/resources/baseball_icon_126956.png";
            size = new Tmapv3.Size(24, 38);
          } else if (properties.pointType == "E") {
            // 도착지 마커
            markerImg = "src/resources/baseball_icon_126956.png";
            size = new Tmapv3.Size(24, 38);
          } else {
            // 각 포인트 마커
            markerImg = "src/resources/baseball_icon_126956.png";
            size = new Tmapv3.Size(8, 8);
          }

          // 경로들의 결과값들을 포인트 객체로 변환
          const x = geometry.coordinates[0];
          const y = geometry.coordinates[1];
          const point = new Tmapv3.Point(x, y);
          // 좌표 변환
          const latlng = Tmapv3.Projection.convertEPSG3857ToWGS84GEO(point);
          const position = new Tmapv3.LatLng(latlng._lat, latlng._lng);

          const marker_p = new Tmapv3.Marker({
            position: position,
            icon: "src/resources/baseball_icon_126956.png",
            iconSize: size,
            map: map,
          });

          resultMarkerArr.push(marker_p);
        }
      }
    })
    .catch((error) => {
      console.error("Error:", error);
    });
}
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
