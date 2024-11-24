<script setup>
import Card from "primevue/card";
</script>

<template>
  <div class="container shadow-3">
    <div class="img-container">
      <img src="@/assets/img/1.jpg" width="100%" height="100%" />
    </div>
    <div class="hover-overlay">
      <p class="description">
        여기에 설명을 적어주세요. 마우스를 올리면 나타나는 설명문입니다.
      </p>
    </div>
    <div class="gradient-overlay">
      <div class="sido-name text-lg pt-3">시도 이름</div>
    </div>
  </div>
</template>

<style scoped>
.container {
  width: 100%;
  height: 100%;
  /* border: 1px solid palevioletred; */
  border-radius: 10px;
  position: relative;
  overflow: hidden;
  transition: transform 0.3s ease;
}

.container:hover {
  transform: scale(1.1);
}

.img-container {
  height: 100%;
  position: relative;
}

.img-container img {
  border-radius: 10px;
  object-fit: cover; /* 비율을 유지하며 컨테이너를 채움 (필요시 잘라냄) */
  object-position: center;
  transition: filter 0.3s ease;
}

/* 호버 시 이미지 어둡게 */
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
  align-items: center;
  justify-content: center;
  padding: 20px;
  opacity: 0;
  transition: opacity 0.3s ease;
  z-index: 2;
}

/* 호버 시 설명 표시 */
.container:hover .hover-overlay {
  opacity: 1;
}

.description {
  color: white;
  font-size: 0.95rem;
  line-height: 1.5;
  text-align: center;
  max-width: 80%;
  font-weight: 300;
  transform: translateY(20px);
  transition: transform 0.4s ease;
}

.container:hover .description {
  transform: translateY(0);
}

.gradient-overlay {
  position: absolute;
  bottom: 0;
  left: 0;
  width: 100%;
  height: 50%; /* 그라데이션의 높이 조절 */
  background: linear-gradient(
    to bottom,
    rgba(0, 0, 0, 0) 0%,
    rgba(0, 0, 0, 0.3) 30%,
    rgba(0, 0, 0, 0.9) 100%
  );
  display: flex;
  align-items: flex-end; /* 텍스트를 하단에 배치 */
  padding-bottom: 20px; /* 하단 여백 */
  z-index: 1;
}

.sido-name {
  /* height: 30%; */
  /* width: 100%; */
  /* border: 1px solid blue; */
  /* position: absolute; */
  /* bottom: 0px; */
  /* left: 0px; */
  /* background: white; */

  color: white;
  padding: 0 20px; /* 좌우 여백 */
  padding-bottom: 10px;
  font-weight: 500; /* 텍스트를 조금 더 굵게 */
  text-shadow: 0 1px 2px rgba(0, 0, 0, 0.2); /* 텍스트 가독성을 위한 그림자 */
  transition: transform 0.3s ease;
}

/* 호버 시 제목 약간 위로 이동 */
.container:hover {
  transform: translateY(-5px);
}

.container:hover .sido-name {
  opacity: 0;
}
</style>
