<script setup>
  import { ref, onMounted } from 'vue'
  import { usePlanStore } from '@/stores/plan-store';

  const planStore = usePlanStore()
  const { compareImage } = planStore

  const props = defineProps({
    course: {
      type: Object,
    },
  });

  const executeCompare = ref(false)
  const compareResult = ref({
    similar: false,
    similarity: 0.01
  })

  onMounted(async () => {
    if (!props.course.userImg) {
      return
    }

    compareResult.value = await compareImage(props.course.missionImg, props.course.userImg)
    executeCompare.value = true
  })
</script>

<template>
  <div class="container">
    <div class="img-container">
      <img :src="course.missionImg" width="100%" height="100%" />
    </div>
    <div class="img-container upload-content">
      <img :src="course.userImg" width="100%" height="100%" />
    </div>
    <div class="flex flex-column justify-content-center" :style="{ visibility: executeCompare ? 'visible' : 'hidden' }">
    <div v-if="compareResult.similar" class="flex justify-content-center">
      <font-awesome-icon class="text-5xl" :style="{color: '#28bf71'}" :icon="['fas', 'circle-check']" />
    </div>
    <div v-else class="flex justify-content-center">
      <font-awesome-icon class="text-5xl" :style="{color: '#e64132'}" :icon="['far', 'circle-xmark']" />
    </div>
    <div class="pt-3 text-sm">
      {{ (compareResult.similarity * 100).toFixed(2) }}%
    </div>
  </div>
  </div>
</template>

<style scoped>
.container {
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: space-around;
  /* align-items: center; */
}

.img-container {
  width: 250px;
  height: 250px;
  /* border: 1px solid peru; */
}

.img-container img {
  border-radius: 10px;
}

/* .upload-content  img {
  filter: brightness(0.7);
}

.upload-content img:hover {
  filter: brightness(0.4);
} */
</style>
