<script setup>
import StatusInfo from "@/components/user/status/StatusInfo.vue";
import StatusMap from "@/components/user/status/StatusMap.vue";

import { ref } from "vue";

const isPanelOpen = ref(false);
const selectedSido = ref(null);

const open = () => {
  isPanelOpen.vale = true;
};

const close = () => {
  isPanelOpen.value = false;

  setTimeout(() => {
    selectedSido.vale = null;
  }, 300);
};
</script>

<template>
  <div class="status-container">
    <div class="info-container">
      <StatusInfo />
    </div>
    <div class="map-container">
      <StatusMap @click="open" />
    </div>
    <div class="plans-container">
      <div :is-open="isPanelOpen" @close="closePanel">
        <div>여행 정보</div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.status-container {
  display: flex;
  width: 100%;
  height: 100%;
}

.info-container {
  border: 1px solid pink;
  width: 900px;
}

.map-container {
  border: 1px solid red;
  width: 100%;
}

.plans-container {
  border: 1px solid black;
  width: 900px;
}
</style>
