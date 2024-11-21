<script setup>
import PlanListItem from '@/components/plan/item/PlanListItem.vue';

import {ScrollPanel, Button} from 'primevue';

</script>

<template>
  <div class="container p-1">
    <div class="btn-close">
      <Button severity="secondary" variant="text" rounded aria-label="Cancel">
        <font-awesome-icon :icon="['fas', 'x']" />
      </Button>
    </div>
    <div class="title-container p-2 pt-0 text-lg flex justify-content-end">
      여행 기록들
    </div>
    <div class="travel-list">
      <ScrollPanel class="scroll-panel p-2" style="width: 100%; height: 100%;">
            <PlanListItem class="plan-list-item"/>
            <PlanListItem />
            <PlanListItem />
            <PlanListItem />
            <PlanListItem />
            <PlanListItem />
            <PlanListItem />

            <PlanListItem />
            <PlanListItem />
            <PlanListItem />

            <PlanListItem />
        </ScrollPanel>



      

    </div>



  </div>
</template>

<style scoped>
.container {
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: column;
}

.title-container {
  height: 50px;
  flex-shrink: 0;
}

.travel-list {
 height: 100%;
  position: relative;
}

.scroll-panel {
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
}

.plan-list-item {
  transition: all 0.3s cubic-bezier(0.4, 0, 0.2, 1);
}

.plan-list-item:hover {
  transform: translateY(-2.5px);
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.08);
  border: 1px solid #ddd;
}
</style>
