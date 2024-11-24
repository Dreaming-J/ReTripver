<script setup>
import AttractionListItem from '@/components/attraction/AttractionListItem.vue';

import { ref, watch } from 'vue';
import { storeToRefs } from "pinia";
import { useMakePlanStore } from "@/stores/makePlan-store";

const store = useMakePlanStore();

</script>

<template>
  <div>
    <div class="flex flex-wrap" >
        <div class="col-3 p-4"
        v-for="search in store.searchList"
        :key="search.no">
            <AttractionListItem :search="attraction"/>
        </div>
    </div>
  </div>
</template>

<style scoped>

</style>
