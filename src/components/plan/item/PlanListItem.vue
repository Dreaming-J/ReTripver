<script setup>
defineProps({
  plan: {
    type: Object,
    required: true
  }
});
</script>

<template>
  <div class="list-item shadow-2">
    <div class="item-header">
      {{ plan.title }}
    </div>
    <div class="item-images" :class="`images-count-${plan.courses.length}`">
      <div
        v-for="(course, index) in plan.courses"
        :key="index"
        class="image-wrapper"
        :class="`image-${index + 1}`"
        :style="{ backgroundImage: `url(${course.userImg})` }"
      ></div>
    </div>
  </div>
</template>

<style scoped>
.list-item {
  display: flex;
  flex-direction: column;
  /* border: 1px solid #eee; */
  border-radius: 10px;
  padding: 16px;
  margin-bottom: 16px;
  background-color: #fff;
  /* box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); */
  height: 230px;
}

.item-header {
  margin-bottom: 16px;
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
