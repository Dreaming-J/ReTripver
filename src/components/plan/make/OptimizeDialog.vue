<script setup>
import { ref } from "vue";

import Checkbox from 'primevue/checkbox';
import Button from "primevue/button";

const optimizeOptions = ref();
</script>

<template>
  <div class="checkbox-container">
        <div class="checkbox-item">
                <Checkbox v-model="optimizeOptions" inputId="optimizeCourse" name="optimizeOptions" value="optimizeCourse" />
                <label for="optimizeCourse"> 코스 최적화 </label>
        </div>
        <div class="checkbox-item">
            <Checkbox v-model="optimizeOptions" inputId="startFix" name="optimizeOptions" value="startFix" />
            <label for="startFix"> 출발지 고정 </label>
        </div>
        <div class="checkbox-item">
            <Checkbox v-model="optimizeOptions" inputId="endFix" name="optimizeOptions" value="endFix" />
            <label for="endFix"> 도착지 고정 </label>
        </div>
        <div class="btn-items">
            <Button class="btn-item" label="취소" severity="secondary" variant="outlined" />
            <Button class="btn-item" label="확인" severity="warn" />
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
    width:200px;
}
</style>
