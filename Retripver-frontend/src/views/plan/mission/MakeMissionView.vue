<script setup>
import { ref, onMounted, computed } from "vue";
import PlanTimeLine from "@/components/plan/mission/PlanMissionTimeLine.vue";
import { useRouter, useRoute } from "vue-router";
import { storeToRefs } from "pinia";
import { useMakePlanStore } from "@/stores/makePlan-store";

const makePlanStore = useMakePlanStore()
const { newPlan } = storeToRefs(makePlanStore)

const showTimeline = ref(false);
const startAnimation = ref(false);
const router = useRouter();
const route = useRoute();

const images = ref([]);

// 각 이미지의 딜레이를 동적으로 계산
const getDelay = (index) => {
  return index * 0.3;
};

// 전체 애니메이션 시간 계산
const totalAnimationTime = computed(() => {
  return images.value.length * 0.3 + 1.5;
});

onMounted(() => {
  setTimeout(() => {
    startAnimation.value = true;
    setTimeout(() => {
      router.push({ name: "mission-upload", params: {newPlanId: newPlan.value.id} });
    }, totalAnimationTime.value * 1000);
  }, 500);

  for (let idx = 0; idx < newPlan.value.courses.length; idx++) {
    images.value.push({
      id: idx, src: newPlan.value.courses[idx].missionImg
    })
  }
});
</script>

<template>
  <div class="main-container">
    <div class="text-center">Mission 이미지 생성 중...</div>
    <div v-if="!showTimeline" class="quest-container">
      <div
        v-for="(image, index) in images"
        :key="image.id"
        class="img-card"
        :style="`--delay: ${getDelay(index)}s`"
        :class="{ 'start-slide': startAnimation }"
      >
        <img :src="image.src" width="100%" height="100%" />
      </div>
    </div>

    <div
      v-else
      class="timeline-container"
      :class="{ 'show-timeline': showTimeline }"
    >
      <PlanTimeLine />
    </div>
  </div>
</template>

<style scoped>
.main-container {
  min-height: 100vh;
  position: relative;
  overflow: hidden;
}

.quest-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  min-height: 100vh;
}

.img-card {
  width: 300px;
  height: 300px;
  position: absolute;
  left: 50%;
  top: 40%;
  transform: translate(-50%, 100vh);
  opacity: 0;
  --delay: 0s;
}

.img-card img {
  border-radius: 10px;
  width: 100%;
  height: 100%;
  object-fit: cover;
  box-shadow: 0 10px 30px rgba(0, 0, 0, 0.2);
}

.start-slide {
  animation: slideUp 1.5s forwards;
  animation-delay: var(--delay);
}

@keyframes slideUp {
  0% {
    transform: translate(-50%, 100vh);
    opacity: 0;
  }
  100% {
    transform: translate(-50%, -50%);
    opacity: 1;
  }
}

.timeline-container {
  width: 100%;
  max-width: 1200px;
  margin: 0 auto;
  padding: 20px;
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
