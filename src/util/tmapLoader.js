import { ref } from "vue";

const { VITE_TMAP_SERVICE_KEY } = import.meta.env;
const { Tmapv3 } = window;

// 상태 변수들을 ref로 정의
const resultMarkerArr = ref([]);
const resultdrawArr = ref([]);
const drawInfoArr = ref([]);

export const initTmap = () => {
  const map = new Tmapv3.Map("map_div", {
    center: new Tmapv3.LatLng(37.5652045, 126.98702028),
    zoom: 14,
    zoomControl: true,
    scrollwheel: true,
  });

  return map;
};

export const addMarker = (map, lat, lng) => {
  const marker = new Tmapv3.Marker({
    position: new Tmapv3.LatLng(lat, lng),
    map: map,
  });

  return marker;
};

export const addPolyline = (map, locations) => {
  locations = locations.map(location => new Tmapv3.LatLng(location.latitude, location.longitude));
  console.log(locations);

  var polyline = new Tmapv3.Polyline({
    path: locations,
    strokeColor: "#808080",
    strokeWeight: 6,
    direction: true,
     map: map
  });

  return polyline;
}

function addPolyline2(){
  var polyline = new Tmapv3.Polyline({
    path: [new Tmapv3.LatLng(37.56480450,126.98512028),	// 선의 꼭짓점 좌표
      new Tmapv3.LatLng(37.56565450,126.98512028),	// 선의 꼭짓점 좌표
      new Tmapv3.LatLng(37.56480450,126.98582028),	// 선의 꼭짓점 좌표
      new Tmapv3.LatLng(37.56565450,126.98652028),	// 선의 꼭짓점 좌표
      new Tmapv3.LatLng(37.56480450,126.98652028)],
    strokeColor: "#dd00dd",
    strokeWeight: 6,
     map: map
  });
}

export const removeMarker = (marker) => {
  marker.setMap(null);
};

export const onSearchOnlyTime = async (start, end, routeType) => {
  // console.log("시간 가져오기", start.title, "->", end.title);

  const headers = {
    "Content-Type": "application/json",
    appKey: VITE_TMAP_SERVICE_KEY,
  };

  const requestData = {
    startName: start.title,
    startX: String(start.longitude),
    startY: String(start.latitude),
    endName: end.title,
    endX: String(end.longitude),
    endY: String(end.latitude),
    reqCoordType: "WGS84GEO",
    resCoordType: "EPSG3857",
  };

  const url = ref("");
  if (routeType) {
    url.value = "https://apis.openapi.sk.com/tmap/routes?version=1&format=json"; // 자동차
  } else {
    url.value =
      "https://apis.openapi.sk.com/tmap/routes/pedestrian?version=1&format=json"; // 도보
  }

  return await fetch(url.value, {
    method: "POST",
    headers: headers,
    body: JSON.stringify(requestData),
  })
    .then((response) => response.json())
    .then((data) => {
      if (!data || !data.features) {
        throw new Error("Invalid response data");
      }

      const resultData = data.features;

      let tTime = 0;

      // 경로 정보 저장
      if (resultData[0] && resultData[0].properties) {
        tTime = resultData[0].properties.totalTime / 60;

        // console.log(
        //   `[시간 정보] ${start.title} -> ${end.title} : ${tTime}분`
        // );
      }

      return tTime
    })
    .catch((error) => {
      console.error("시간 계산 중 오류 발생:", error);
    });
};

export const onSearchRoute = async (map, start, end, routeType) => {
  // console.log("경로 그리기:", start.title, "->", end.title);

  const headers = {
    "Content-Type": "application/json",
    appKey: VITE_TMAP_SERVICE_KEY,
  };

  const requestData = {
    startName: start.title,
    startX: String(start.longitude),
    startY: String(start.latitude),
    endName: end.title,
    endX: String(end.longitude),
    endY: String(end.latitude),
    reqCoordType: "WGS84GEO",
    resCoordType: "EPSG3857",
  };

  const url = ref("");
  if (routeType) {
    url.value = "https://apis.openapi.sk.com/tmap/routes?version=1&format=json";
  } else {
    url.value =
      "https://apis.openapi.sk.com/tmap/routes/pedestrian?version=1&format=json";
  }

  return await fetch(url.value, {
    method: "POST",
    headers: headers,
    body: JSON.stringify(requestData),
  })
    .then((response) => response.json())
    .then((data) => {
      if (!data || !data.features) {
        throw new Error("Invalid response data");
      }

      const resultData = data.features;

      // 경로 정보 저장
      let tTime;
      if (resultData[0] && resultData[0].properties) {
        const tDistance = resultData[0].properties.totalDistance / 1000;
        tTime = resultData[0].properties.totalTime / 60;

        // console.log(
        //   `[경로 정보] ${start.title} -> ${end.title} : ${tDistance}km, ${tTime}분`
        // );
      }

      // 이 경로의 모든 좌표를 저장할 배열
      const routeCoordinates = [];

      // 새로운 경로 그리기
      resultData.forEach((feature) => {
        const { geometry } = feature;

        if (geometry.type === "LineString") {
          // 경로 라인 그리기
          const coordinates = geometry.coordinates.map((coord) => {
            const point = new Tmapv3.Point(coord[0], coord[1]);
            const convertPoint =
              new Tmapv3.Projection.convertEPSG3857ToWGS84GEO(point);
            return new Tmapv3.LatLng(convertPoint._lat, convertPoint._lng);
          });
          routeCoordinates.push(...coordinates);
        }
      });

      // 이 경로에 대한 새로운 polyline 생성
      if (routeCoordinates.length > 0) {
        const polyline = new Tmapv3.Polyline({
          path: routeCoordinates,
          strokeColor: "#DD0000",
          strokeWeight: 6,
          direction: true,
          map: map,
        });

        if (!resultdrawArr.value) resultdrawArr.value = [];
        resultdrawArr.value.push(polyline);
      }
      // console.log("!!!!!", tTime);
      return tTime;
    })
    .catch((error) => {
      console.error("경로 검색 중 오류 발생:", error);
    });
};

// 경로 라인 그리기 함수 수정
export const drawLine = (map) => {
  if (!map || !drawInfoArr.value) return;

  const polyline = new Tmapv3.Polyline({
    path: drawInfoArr.value,
    strokeColor: "#DD0000",
    strokeWeight: 6,
    map: map,
  });

  if (!resultdrawArr.value) resultdrawArr.value = [];
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
