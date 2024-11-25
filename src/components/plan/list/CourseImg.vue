<script setup>
const props = defineProps({
  course: {
    type: Object,
  },
});
</script>

<template>
  <div class="container">
    <div class="img-container">
      <img :src="course.missionImg" width="100%" height="100%" />
    </div>
    <div class="img-container upload-content">
      <img :src="course.userImg" width="100%" height="100%" />
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
