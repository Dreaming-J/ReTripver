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
    <div class="img-container">
      <form>
        <label for="uploadImg">
          <img :src="course.userImg" width="100%" height="100%" />
        </label>
        <input type="file" id="uploadImg" style="display: none" />
      </form>
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

form img {
  filter: brightness(0.7);
}

form img:hover {
  filter: brightness(0.4);
}
</style>
