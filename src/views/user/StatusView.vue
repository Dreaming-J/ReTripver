<script setup>
import StatusInfo from "@/components/user/status/StatusInfo.vue";
import StatusMap from "@/components/user/status/StatusMap.vue";
import StatusPlan from "@/components/user/status/StatusPlan.vue";

import { ref } from "vue";

const isPlansVisible = ref(true);

const selectSido = () => {
  isPlansVisible.value = !isPlansVisible.value;
}

</script>

<template>
  <div class="status-container">
    <div class="info-container">
      {{ isPlansVisible  }}
      <StatusInfo />
    </div>
    <div class="map-container" >
      <button @click="selectSido">클릭</button>
      <StatusMap  />
    </div>
    <div class="plans-container">
      <div
            class="plan-panel shadow-3"
            :class="{'plan-panel-hide': !isPlansVisible}">
        
          <StatusPlan />
        
      </div>
    </div>
  </div>
</template>

<style scoped>
.status-container {
  display: flex;
  flex-wrap: nowrap;
  width: 100%;
  height: 100%;
  position: relative;
}

.info-container {
  border: 1px solid pink;
  width: 400px;
  min-width: 400px;
}

.map-container {
  border: 1px solid red;
  width: 100%;
}

.plans-container {
  position: relative;
  width: 500px;
  min-width: 400px;
  height: 100%;
  /* border: 1px solid purple; */
}

.plan-panel {
  width: 100%;
  height: 100%;
  border-radius: 15px;
  position: absolute;
  right: 0;
  top: 0;
  background-color: white;
  transition: 0.5s ease;
}

.plan-panel-hide {
  transform: translate(400px, 0);
  
   /* background-color: pink; */
}
</style>
