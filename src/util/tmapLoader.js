const { Tmapv3 } = window;

export const initTmap = () => {
  const map = new Tmapv3.Map("map_div", {
    center: new Tmapv3.LatLng(37.56520450, 126.98702028),
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
  })

  return marker;
}

export const removeMarker = (marker) => {
  marker.setMap(null);
};