<script setup>
import PlanListItem from '@/components/plan/item/PlanListItem.vue';

const props = defineProps({
    sidoCode : {
        type:String
    }
})

console.log(props.sidoCode);
</script>

<template>
  <div>
    <div class="flex flex-wrap">
      <div class="col-4 p-3">
        <PlanListItem class="plan-item"/>
      </div>
      <div class="col-4 p-3">
        <PlanListItem class="plan-item"/>
      </div>
      <div class="col-4 p-3">
        <PlanListItem class="plan-item" />
      </div>
      <div class="col-4 p-3">
        <PlanListItem class="plan-item" />
      </div>
      <div class="col-4 p-3">
        <PlanListItem class="plan-item" />
      </div>
    </div>
  </div>
</template>

<style scoped>
.plan-item {
  transition: all 0.3s cubic-bezier(0.4, 0, 0.2, 1);
}

.plan-item:hover {
  transform: translateY(-3px);
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.05);
  /* border: 1px solid #ddd; */
}
</style>
