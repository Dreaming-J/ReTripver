<script setup>
  import PlanListItem from '@/components/plan/item/PlanListItem.vue';
  import { ref, onMounted, watch } from 'vue'
  import { storeToRefs } from "pinia";
  import { usePlanStore } from "@/stores/plan-store";
  import { useRouter } from 'vue-router';

  const router = useRouter();

  const planStore = usePlanStore();
  const { planListInSido } = storeToRefs(planStore)
  const { getPlanListInSido } = planStore

  const props = defineProps({
      sidoCode: {
        type: String
      },
      gugun: {
        type: Object,
        required: false
      },
      keyword: {
        type: String,
        required: false
      }
  })

  const planList = ref([])

  onMounted(async () => {
    await getPlanListInSido(props.sidoCode)
    planList.value = planListInSido.value
  })

  watch([() => props.gugun, () => props.keyword], () => {
    planList.value = planListInSido.value.filter(plan => {
      const courses = plan.courses;

      let isTarget = false
      let hasKeyword = false

      for (let idx = 0; idx < courses.length; idx++) {
        if (courses[idx].attraction.siGunGuCode === props.gugun.gugunCode)
          isTarget = true

        if (courses[idx].attraction.title.includes(props.keyword))
          hasKeyword = true 
      }

      if (Object.keys(props.gugun).length === 0)
        isTarget = true

      if (props.keyword === '')
        hasKeyword = true

      return isTarget && hasKeyword
    })
  })

const planListItemClick = (plan) => {
  // console.log(plan);


  router.push({name: "plan-info", params: {
      planId : plan.id
    }
  });


}
</script>

<template>
  <div>
    <div class="flex flex-wrap">
      <div class="col-4 p-3" v-for="plan in planList" :key="plan.id">
        <PlanListItem class="plan-item" :plan="plan" @click="planListItemClick(plan)"/>
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
