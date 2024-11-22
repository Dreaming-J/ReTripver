<script setup>
const props = defineProps({
  info: {
    type: Object,
  },
});
</script>

<template>
  <div class="container">
    <div class="img-wrapper">
      <img
        :src="info.firstImage1"
        @error="
          $event.target.src =
            'https://via.placeholder.com/85x85/f5f5f5/999999?text=No+Image'
        "
      />
    </div>
    <div class="text-container">
      <div class="text-title" :class="{ 'long-title': info.title.length > 15 }">
        {{ info.title }}
      </div>
      <div class="text-addr">
        <font-awesome-icon :icon="['fas', 'location-dot']" />
        {{ info.addr1?.trim().split(/\s+/).filter(Boolean).slice(0, 2).join(" ") || "" }}
      </div>
    </div>
  </div>
</template>

<style scoped>
.container {
  display: flex;
  height: 100%;
  gap: 20px;
  align-items: center;
}

img {
  object-fit: cover; /* 비율을 유지하며 컨테이너를 채움 (필요시 잘라냄) */
  object-position: center;
}

.img-wrapper {
  flex-shrink: 0;
  width: 100px;
  height: 90px;
  overflow: hidden;
  border-radius: 10px;
}

.img-wrapper img {
  width: 100%;
  height: 100%;
  object-fit: cover;
  border-radius: 10px;
}

.text-container {
  flex: 1;
  min-width: 0; /* 텍스트 오버플로우 방지 */
  display: flex;
  flex-direction: column;
  gap: 8px;
}

.text-title {
  font-family: GmarketSansMedium;
  font-size: 1.125rem; /* 기본 크기 */
  line-height: 1.3;
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
  word-break: keep-all;
}

.text-title.long-title {
  font-size: 1rem; /* 긴 제목일 때 작은 크기 */
}

.text-addr {
  color: gray;
  font-size: 0.875rem;
  display: flex;
  align-items: center;
  gap: 4px;
}

.text-addr .fa-location-dot {
  flex-shrink: 0;
}
</style>
