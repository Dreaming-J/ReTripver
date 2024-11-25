<script setup>
defineProps({
  attraction: {
    type: Object,
  },
});
</script>

<template>
  <div class="info-continer flex">
    <div class="map-content col-4">
        {{ attraction.latitude }}
        {{ attraction.latitude}}
    </div>
    <div class="info-content col-8">
        <div class="img-content">
            <img :src="attraction.firstImage1" width="100%" height="100%">
        </div>
        <div class="text-content">
            <div class="text-lg text-item">{{ attraction.title }}</div>
            <div class="text-item">
                <sapn><font-awesome-icon class="text-sm" :icon="['fas', 'location-dot']" /></sapn>
                {{ attraction.addr1?.trim().split(/\s+/).filter(Boolean).slice(0, 2).join(" ") || "" }}
            </div>
        </div>
    </div>
  </div>
</template>

<style scoped>
.info-continer {
  width: 100%;
  height: 400px;
}

.map-content {
    /* border: 1px solid blue; */
}

.img-content {
    height: 250px;
}

.img-content img {
    object-fit: cover;
    object-position: center;
}

.text-content {
    height: 130px;
    padding: 20px;
}

.text-item {
    padding: 10px 0;
}
</style>
