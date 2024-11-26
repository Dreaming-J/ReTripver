<script setup>
import AttractionListItem from "@/components/attraction/AttractionListItem.vue";

import { ref, watch, onMounted } from "vue";
import { storeToRefs } from "pinia";
import { useMakePlanStore } from "@/stores/makePlan-store";

import Dialog from 'primevue/dialog';

const store = useMakePlanStore();
const { searchAttractions } = store

onMounted(async () => {
  await searchAttractions()
})
</script>

<template>
  <div>
    <div class="flex flex-wrap">
      <!-- {{ store.searchList }} -->
      <div
        class="col-3 p-4 search-item"
        v-for="search in store.searchList"
        :key="search.no"
      >
        <!-- {{ search.title }} -->
        <AttractionListItem class="attraction-item" :attraction="search"/>
      </div>
    </div> 
  </div>
</template>

<style scoped>
.attraction-item {
  transition: all 0.3s cubic-bezier(0.4, 0, 0.2, 1);
}

.attraction-item:hover {
  transform: translateY(-3px);
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.05);
  /* border: 1px solid #ddd; */
}
</style>
