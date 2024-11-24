<script setup>
import PlanTimeLine from '@/components/plan/mission/PlanTimeLine.vue';
</script>

<template>
  <div class="view-container">
    <div class="map mt-5">
        지도로 경유지 보여주기
    </div>
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

</style>
