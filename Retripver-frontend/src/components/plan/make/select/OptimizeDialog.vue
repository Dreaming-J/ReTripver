<script setup>
import { ref } from "vue";
import { useMakePlanStore } from "@/stores/makePlan-store";

import Checkbox from "primevue/checkbox";
import Button from "primevue/button";

const makePlanStore = useMakePlanStore()
const { optimizeList } = makePlanStore

const optimizeOptions = ref([]);

const emit = defineEmits(["closeOptimizeDialog"])

const cancleEvent = () => {
  emit("closeOptimizeDialog")
}

const okEvent = async () => {
  await optimizeList(optimizeOptions.value)
  emit("closeOptimizeDialog")
}
</script>

<template>
  <div class="checkbox-container">
    <div class="checkbox-item">
      <Checkbox
        v-model="optimizeOptions"
        inputId="fixFirst"
        name="optimizeOptions"
        value="fixFirst"
      />
      <label for="fixFirst"> 출발지 고정 </label>
    </div>

    <div class="checkbox-item">
      <Checkbox
        v-model="optimizeOptions"
        inputId="fixLast"
        name="optimizeOptions"
        value="fixLast"
      />
      <label for="fixLast"> 도착지 고정 </label>
    </div>

    <div class="btn-items">
      <Button
        class="btn-item"
        label="취소"
        severity="secondary"
        variant="outlined"
        @click="cancleEvent"
      />
      <Button
        class="btn-item" 
        label="확인"
        severity="warn"
        @click="okEvent"
      />
    </div>
  </div>
</template>

<style scoped>
.checkbox-container {
  width: 430px;
  display: flex;
  flex-direction: column;
  align-items: center;
}

.checkbox-item {
  margin: 15px;
  font-size: 20px;
  display: flex;
  align-items: center;
}

label {
  margin-left: 20px;
}

.btn-items {
  display: flex;
}

.btn-item {
  margin: 5px;
  margin-top: 30px;
  width: 200px;
}
</style>
