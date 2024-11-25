<script setup>
  import PlanListItem from '@/components/plan/item/PlanListItem.vue';
  import { ref, onMounted } from 'vue'
  import { storeToRefs } from "pinia";
  import { usePlanStore } from "@/stores/plan-store";

  const planStore = usePlanStore();
  const { planListInSido } = storeToRefs(planStore)
  const { getPlanListInSido } = planStore

  const props = defineProps({
      sidoCode : {
          type:String
      }
  })

  const planList = ref([])
  onMounted(async () => {
    await getPlanListInSido(props.sidoCode)
    planList.value = planListInSido.value
  })
</script>

<template>
  <div>
    <div class="flex flex-wrap">
      <div class="col-4 p-3" v-for="plan in planList" :key="plan.id">
        <PlanListItem class="plan-item" :plan="plan"/>
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
