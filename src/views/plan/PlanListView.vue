<script setup>
import SelectSido from "@/components/common/sido/SelectSido.vue";
</script>

<template>
  <div class="view-container">
    <div class="text-title text-lg">어떤 지역의 여행을 구경하시나요?</div>
    <div class="select-sido-container">
      <SelectSido />
    </div>
  </div>
</template>

<style scoped>
.view-container {
  width: 1200px;
  margin: 0 auto;
  border: 1px solid black;
}

.text-title {
  border: 1px solid red;
  text-align: center;
  padding-top: 20px;
}

.select-sido-container {
  border: 1px solid blue;
}
</style>
