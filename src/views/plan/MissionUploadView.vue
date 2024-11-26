<script setup>
import PlanTimeLine from "@/components/plan/mission/PlanMissionTimeLine.vue";
import { ref, onMounted } from "vue";


  onMounted(() => {
    // 요소가 존재하는지 확인 후 스크롤 이동
    const element = document.querySelector(".mission-container");

    if (element) {
      element.scrollIntoView({ behavior: "smooth" });
    }
  });
</script>

<template>
  <div class="view-container">
    <div class="map mt-5">지도로 경유지 보여주기</div>
    <div class="mission-container mt-8">
      <PlanTimeLine />
    </div>
  </div>
</template>

<style scoped>
.view-container {
  width: 1300px;
  margin: 0 auto;
  /* border: 1px solid black; */
}

.map {
  width: 100%;
  height: 300px;
  border: 1px solid blue;
}

.mission-container {
  width: 100%;
  /* border: 1px solid red; */
}

/* 타임라인 등장 애니메이션 */
.timeline-container {
  opacity: 0;
}

.show-timeline {
  animation: timelineAppear 1s ease forwards;
}

@keyframes timelineAppear {
  from {
    opacity: 0;
    transform: translateY(20px);
  }
  to {
    opacity: 1;
    transform: translateY(0);
  }
}
</style>
