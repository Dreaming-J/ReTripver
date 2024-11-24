<script setup>
  import RankListItem from '@/components/user/RankListItem.vue'
  import { ref, onMounted } from 'vue'
  import { usePlanStore } from "@/stores/plan-store"

  const planStore = usePlanStore()
  const { getRankList } = planStore
  
  const rankList = ref([])
  onMounted(async () => {
    rankList.value = await getRankList(1);
  })
</script>

<template>
  <div>
    <h1>랭킹 화면</h1>

    <RankListItem
      v-for="rank in rankList"
      :key="rank.id"
      :rank="rank"
    />
  </div>
</template>

<style scoped></style>