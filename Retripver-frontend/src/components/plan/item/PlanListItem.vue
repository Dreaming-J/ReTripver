<script setup>
import {Button} from 'primevue';
import { ref, onMounted } from 'vue';
import { useUserStore } from '@/stores/user-store';
import { usePlanStore } from '@/stores/plan-store';
import { useMakePlanStore } from '@/stores/makePlan-store';

const userStore = useUserStore()
const { isLogin } = userStore

const planStore = usePlanStore()
const { createReview } = planStore

const makePlanStore = useMakePlanStore();
const { copyPlan } = makePlanStore;

const props = defineProps({
  plan: {
    type: Object,
    required: true
  }
});

const review = ref("AI 리뷰 생성 중")

onMounted(async () => {
  for (let idx = 0; idx < props.plan.courses.length; idx++) {
    if (!props.plan.courses[idx].userImg)
      props.plan.courses[idx].userImg = "https://retripver-s3-bucket.s3.ap-northeast-2.amazonaws.com/not-uploaded.png"
  }

  let count = 0

  const interval = setInterval(() => {
    review.value = "AI 리뷰 생성 중" + ".".repeat(1 + count++ % 3)
  }, 100);

  review.value = await createReview(props.plan)

 clearInterval(interval);
})

const openMissionView = () => {
  console.log("클릭햇을 때, 라우터를 이용하여 이동하거나 하시면 됩니다.")
}

const copyPlanEvent = (planId) => {
  copyPlan(planId);
}
</script>

<template>
  <div class="list-item shadow-2" @click="openMissionView">
    <div class="item-images" :class="`images-count-${plan.courses.length}`">
      <div
        v-for="(course, index) in plan.courses"
        :key="index"
        class="image-wrapper"
        :class="`image-${index + 1}`"
        :style="{ backgroundImage: `url(${course.userImg})` }"
      ></div>
    </div>
    <div class="item-header mt-3">
      {{ plan.title }}
    </div>
    <div class="item-desc text-sm">
      {{ review }}
    </div>
    <div class="item-footer flex justify-content-end">
      <Button class="text-sm" severity="secondary" variant="text"
              @click.stop="copyPlanEvent(plan.id)" :disabled="!isLogin">
        <!-- <font-awesome-icon class="text-lg" :icon="['far', 'copy']" /> -->
        <font-awesome-icon :icon="['fas', 'person-walking']" />
        여행 따라가기
      </Button>
    </div>
  </div>
</template>

<style scoped>
.list-item {
  display: flex;
  flex-direction: column;
  /* border: 1px solid red; */
  border-radius: 10px;
  padding: 16px;
  margin-bottom: 16px;
  background-color: #fff;
  /* box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); */
  height: 270px;
}

.item-header {
  margin-bottom: 10px;
}

.item-header h3 {
  margin: 0;
  font-size: 1.2rem;
  font-weight: bold;
}

.item-header p {
  margin: 4px 0 0;
  color: #666;
  font-size: 0.9rem;
}

.item-images {
  display: grid;
  gap: 8px;
  height: 300px;
}

.image-wrapper {
  background-size: cover;
  background-position: center;
  border-radius: 8px;
  box-shadow: 0 1px 2px rgba(0, 0, 0, 0.1);
}

.item-desc  {
  color: gray;
  /* border: 1px solid red; */
  height: 60px;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis; /* 넘치는 부분에 "..." 추가 */
}

/* Single image */
.images-count-1 {
  grid-template-columns: 1fr;
}

/* Two images */
.images-count-2 {
  grid-template-columns: 1fr 1fr;
}

/* Three images */
.images-count-3 {
  grid-template-columns: 1fr 1fr;
  grid-template-rows: 1fr 1fr;
}

.images-count-3 .image-1 {
  grid-row: 1 / 3;
}

/* Four images */
.images-count-4 {
  grid-template-columns: repeat(2, 1fr);
  grid-template-rows: repeat(2, 1fr);
}

/* Five images */
.images-count-5 {
  grid-template-columns: repeat(4, 1fr);
  grid-template-rows: repeat(2, 1fr);
}

.images-count-5 .image-1 {
  grid-column: 1 / 3;
  grid-row: 1 / 3;
}
</style>
