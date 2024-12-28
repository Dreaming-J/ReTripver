<script setup>
import InputText from "primevue/inputtext";
import IconField from "primevue/iconfield";
import InputIcon from "primevue/inputicon";

import { ref, watch } from "vue";
import { storeToRefs } from "pinia";
import { useMakePlanStore } from "@/stores/makePlan-store";

const makePlanStore = useMakePlanStore();
const { searchAttractions } = makePlanStore;
const { searchOption } = storeToRefs(makePlanStore);

const keyword = ref("");

const searchEvent = async () => {
  await searchAttractions();
};

watch(
  keyword,
  (newKeyword) => {
    searchOption.value.keyword = newKeyword;
  },
  { immediate: true }
);
</script>

<template>
  <div>
    <div class="card flex">
      <IconField class="w-full">
        <InputText class="w-full" v-model="keyword" variant="filled" />
        <InputIcon class="pi pi-search" @click="searchEvent" />
      </IconField>
    </div>
  </div>
</template>

<style scoped></style>
