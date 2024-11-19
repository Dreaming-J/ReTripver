<script>
export default {
  mounted() {
    let option = {
      center: new Tmapv2.LatLng(37.39843974939604, 127.10972941510465),
      width: "890px",
      height: "400px",
      zoom: 7,
    };
    let map = new Tmapv2.Map(this.$refs.tmap, option);
  },
};
</script>

<template>
  <div ref="tmap"></div>
</template>
