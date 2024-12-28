import { ref } from "vue";

const { Tmapv2 } = window;

// 상태 변수들을 ref로 정의
const routeInfo = ref(null);
const resultMarkerArr = ref([]);
const resultdrawArr = ref([]);
const drawInfoArr = ref([]);

export const initTmap = () => {
  console.log(Tmapv2);

  const map = new Tmapv2.Map("map_div", {
    center: new Tmapv2.LatLng(37.5652045, 126.98702028),
    zoom: 14,
    zoomControl: true,
    scrollwheel: true,
    initMapType: "NORMAL",
  });

  // 2. 시작, 도착 심볼찍기
  // 시작
  const marker_s = new Tmapv2.Marker({
    position: new Tmapv2.LatLng(37.564991, 126.983937),
    map: map,
  });

  // // 도착
  const marker_e = new Tmapv2.Marker({
    position: new Tmapv2.LatLng(37.566158, 126.98894),
    map: map,
  });

  return map;
};

export const addMarker = (map, lat, lng) => {
  const marker = new Tmapv2.Marker({
    position: new Tmapv2.LatLng(lat, lng),
    map: map,
  });

  return marker;
};

export const removeMarker = (marker) => {
  marker.setMap(null);
};

export const onSearchRoute = (map) => {
  const headers = {
    "Content-Type": "application/json",
    appKey: "Nzgwwy1J3Zsqz0wG9woF7pL1T7Vqflc79F9psGGh",
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
  };

  fetch(
    "https://apis.openapi.sk.com/tmap/routes/pedestrian?version=1&format=json",
    {
      method: "POST",
      headers: headers,
      body: JSON.stringify(requestData),
    }
  )
    .then((response) => response.json())
    .then((data) => {
      if (!data || !data.features) {
        throw new Error("Invalid response data");
      }

      const resultData = data.features;

      // 경로 정보 저장
      if (resultData[0] && resultData[0].properties) {
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
      }

      // 기존 경로 초기화
      clearRoute();

      const allCoordinates = [];

      // 새로운 경로 그리기
      resultData.forEach((feature) => {
        const { geometry, properties } = feature;

        if (geometry.type === "LineString") {
          // 경로 라인 그리기
          const coordinates = geometry.coordinates.map((coord) => {
            const point = new Tmapv2.Point(coord[0], coord[1]);
            const convertPoint =
              new Tmapv2.Projection.convertEPSG3857ToWGS84GEO(point);
            return new Tmapv2.LatLng(convertPoint._lat, convertPoint._lng);
          });

          // drawInfoArr.value = coordinates;
          // drawInfoArr.value.push(coordinates);
          allCoordinates.push(...coordinates);
        }
      });
      drawInfoArr.value = allCoordinates;
      drawLine(map);
    })
    .catch((error) => {
      console.error("경로 검색 중 오류 발생:", error);
    });
};

// 경로 라인 그리기 함수 수정
export const drawLine = (map) => {
  if (!map || !drawInfoArr.value) return;

  const polyline = new Tmapv2.Polyline({
    path: drawInfoArr.value,
    strokeColor: "#DD0000",
    strokeWeight: 6,
    map: map,
  });

  // if (!resultdrawArr.value) resultdrawArr.value = [];
  resultdrawArr.value.push(polyline);
};

// 기존 경로 초기화 함수 수정
export const clearRoute = () => {
  if (resultdrawArr.value) {
    resultdrawArr.value.forEach((line) => {
      if (line) line.setMap(null);
    });
  }
  if (resultMarkerArr.value) {
    resultMarkerArr.value.forEach((marker) => {
      if (marker) marker.setMap(null);
    });
  }
  resultdrawArr.value = [];
  resultMarkerArr.value = [];
  drawInfoArr.value = [];
};
