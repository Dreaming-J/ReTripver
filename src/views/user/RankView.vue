<script setup>
  import RankListItem from '@/components/user/RankListItem.vue'
  import { storeToRefs } from "pinia"
  import { useUserStore } from "@/stores/user-store"

  const userStore = useUserStore()
  const { rankList } = storeToRefs(userStore)
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