<script setup>
defineProps({
  sido: {
    type: Object,
  }
});
</script>

<template>
  <div class="container shadow-3">
    <div class="img-container">
      <img :src="sido.img" width="100%" height="100%" />
    </div>
    <div class="hover-overlay">
      <div class="description">
        <div class="sido-name-hover">{{ sido.name}}</div>
        <p class="sido-description">{{ sido.desc }}</p>
      </div>
    </div>
    <div class="gradient-overlay">
      <div class="sido-name text-lg pt-3">{{ sido.name }}</div>
    </div>
  </div>
</template>

<style scoped>
.container {
  width: 100%;
  height: 100%;
  border-radius: 10px;
  position: relative;
  overflow: hidden;
  transition: transform 0.3s ease;
}

.container:hover {
  transform: scale(1.03);
}

.img-container {
  height: 100%;
  position: relative;
}

.img-container img {
  border-radius: 10px;
  object-fit: cover;
  object-position: center;
  transition: filter 0.3s ease;
}

.container:hover .img-container img {
  filter: brightness(0.7);
}

.hover-overlay {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  display: flex;
  align-items: flex-end;
  padding: 20px;
  opacity: 0;
  transition: opacity 0.3s ease;
  z-index: 2;
}

.container:hover .hover-overlay {
  opacity: 1;
}

.description {
  color: white;
  text-align: left;
  max-width: 80%;
  transform: translateY(20px);
  transition: transform 0.4s ease;
  padding-bottom: 20px;
}

.sido-name-hover {
  font-size: 1.25rem;
  font-weight: 500;
  margin-bottom: 0.5rem;
}

.sido-description {
  font-size: 0.95rem;
  line-height: 1.5;
  font-weight: 300;

  white-space: normal; /* 기본 줄바꿈 설정 */
  word-break: keep-all; /* 단어 단위로 끊어지지 않도록 설정 */
  line-height: 1.3; /* 줄 간격 조절 */
}

.container:hover .description {
  transform: translateY(-2px);
}

.gradient-overlay {
  position: absolute;
  bottom: 0;
  left: 0;
  width: 100%;
  height: 50%;
  background: linear-gradient(
    to bottom,
    rgba(0, 0, 0, 0) 0%,
    rgba(0, 0, 0, 0.3) 30%,
    rgba(0, 0, 0, 0.9) 100%
  );
  display: flex;
  align-items: flex-end;
  padding-bottom: 20px;
  z-index: 1;
}

.sido-name {
  color: white;
  padding: 0 20px;
  padding-bottom: 10px;
  font-weight: 500;
  text-shadow: 0 1px 2px rgba(0, 0, 0, 0.2);
  transition: opacity 0.3s ease;
}

.container:hover .sido-name {
  opacity: 0;
}
</style>