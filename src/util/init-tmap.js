// init-tmap.js
export const initTmap2 = () => {
  // Tmapv2가 로드되었는지 확인
  if (!window.Tmapv2) {
    console.error("Tmapv2 is not loaded");
    return;
  }

  // map_div 요소가 존재하는지 확인
  const mapDiv = document.getElementById("map_div");
  if (!mapDiv) {
    console.error("map_div element not found");
    return;
  }

  try {
    const map = new window.Tmapv2.Map(mapDiv, {
      center: new window.Tmapv2.LatLng(37.566481622437934, 126.98502302169841),
      width: "890px",
      height: "400px",
      zoom: 15,
    });

    return map;
  } catch (error) {
    console.error("Error initializing map:", error);
  }
};
