<script setup>
import StatusInfo from '@/components/user/status/StatusInfo.vue';
import StatusMap from '@/components/user/status/StatusMap.vue';
</script>

<template>
  <div class="status-container">
    <div>
      <StatusInfo />
    </div>
    <div>
      <StatusMap />
    </div>
  </div>
</template>

<style scoped>
.status-container {
  display: flex;
}
</style>
