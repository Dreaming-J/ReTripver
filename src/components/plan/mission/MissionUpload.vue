<script setup>
  import { ref } from 'vue'
  import { usePlanStore } from '@/stores/plan-store';

  const planStore = usePlanStore()
  const { uploadImage, compareImage } = planStore

  const props = defineProps({
    course: {
      type: Object,
    },
  });

  const defaultImg = 'https://retripver-s3-bucket.s3.ap-northeast-2.amazonaws.com/not-uploaded.png'
  
  const file = ref(null)

  const executeCompare = ref(false)
  const compareResult = ref({
    similar: false,
    similarity: 0.01
  })

  const setFile = async (event) => {
    file.value = event.target.files[0]

    const formData = new FormData()
    formData.append('multipartFile', file.value)

    const missionUploadInfo = {
      planId: props.course.planId,
      courseId: props.course.id
    }
    const jsonBlob = new Blob([JSON.stringify(missionUploadInfo)], {
      type: 'application/json'
    })
    formData.append('missionUploadRequest', jsonBlob)

    props.course.userImg = await uploadImage(formData)

    compareResult.value = await compareImage(props.course.missionImg, props.course.userImg)
    executeCompare.value = true
  }
</script>

<template>
  <div class="container">
    <div class="img-container">
      <img :src="course.missionImg" width="100%" height="100%" />
    </div>
    <div class="img-container">
      <label :for="`uploadImg-${course.id}`">
        <img class="img-upload" :src="course.userImg || defaultImg" width="100%" height="100%" />
      </label>
      <input type="file" :id="`uploadImg-${course.id}`" @change="setFile" style="display: none" />
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
      <!-- 통과: {{ compareResult.similar }}<br> -->
      
      
      
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

.img-upload:hover {
  filter: brightness(0.4);
}
</style>
