const { Tmapv3 } = window;

export const initTmap = () => {
  map = new Tmapv3.Map("map_div", {
    center: new Tmapv3.LatLng(37.405278291509404, 127.12074279785197),
    zoom: 14,
    zoomControl: true,
    scrollwheel: true,
  });
};
