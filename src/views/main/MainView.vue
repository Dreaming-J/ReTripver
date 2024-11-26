<script setup>
  import { ref, watch, onMounted } from 'vue'
  import { useUserStore } from '@/stores/user-store';
  import { storeToRefs } from 'pinia';
  import { useRouter } from 'vue-router';

  const userStore = useUserStore()
  const { isLogin } = storeToRefs(userStore)
  const { existCurrentQuest } = userStore
  const router = useRouter()

  const curPlanId = ref(0)

  // watch(isLogin, async (oldD, newD) => {
  //   curPlanId.value = await existCurrentQuest()

  //   if (curPlanId) {
  //     console.log("1")
  //     router.push({name : "plan"})
  //   }
  //   else {
  //     console.log("2")
  //     router.push({name : "plan"})
  //   }
  // })

  onMounted(async () => {
    curPlanId.value = await existCurrentQuest()

    if (curPlanId.value === 0) {
      router.push({name : "plan"})
    }
    else {
      router.push({
        name: 'mission-upload',
        params: { newPlanId: curPlanId.value }
      })
    }
  })
</script>

<template>
  <div>
  </div>
</template>

<style scoped></style>
