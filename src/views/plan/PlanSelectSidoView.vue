<script setup>
import SelectSido from "@/components/common/sido/SelectSido.vue";
</script>

<template>
  <div class="view-container">
    <div class="text-title text-2xl">어떤 지역을 보고 싶나요?</div>
    <div class="select-sido-container">
      <SelectSido />
    </div>
  </div>
</template>

<style scoped>
.view-container {
  width: 1300px;
  margin: 0 auto;
  /* border: 1px solid black; */
}

.text-title {
  /* border: 1px solid red; */
  text-align: center;
  padding-top: 60px;
  padding-bottom: 60px;
}

</style>
