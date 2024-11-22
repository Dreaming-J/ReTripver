<script setup>
import SelectSidoItem from "@/components/common/sido/SelectSidoItem.vue";
import { ref } from "vue";

const items = ref([
  {
    idx: 1,
  },
  {
    idx: 2,
  },
  {
    idx: 3,
  },
  {
    idx: 4,
  },
  {
    idx: 5,
  },
  {
    idx: 6,
  },
]);
</script>

<template>
  <div class="flex flex-wrap">
    <div class="sido-item" v-for="item in items" :key="item.idx">
      <SelectSidoItem />
    </div>
  </div>
</template>

<style scoped>
.sido-item {
  border: 1px solid peru;
}
</style>
