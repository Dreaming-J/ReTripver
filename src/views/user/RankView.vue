<script setup>
import RankListItem from "@/components/user/rank/RankListItem.vue";
import { ref, onMounted } from "vue";
import { usePlanStore } from "@/stores/plan-store";

const planStore = usePlanStore();
const { getUserRankList } = planStore;

const userRankList = ref([]);
onMounted(async () => {
  userRankList.value = await getUserRankList();
});
</script>

<template>
  <div class="view-container">
    <div class="text-title text-2xl">User Ranking</div>
    <div>
      <RankListItem
        v-for="(rank, index) in userRankList"
        :key="rank.id"
        :rank="rank"
        :index="index"
      />
    </div>
  </div>
</template>

<style scoped>
.view-container {
  width: 1000px;
  margin: 0 auto;
  /* border: 1px solid black; */
}

.items-container {
  width: 100%;
  height: 100px;
}

.text-title {
  /* border: 1px solid red; */
  text-align: center;
  padding-top: 60px;
  padding-bottom: 60px;
}
</style>
