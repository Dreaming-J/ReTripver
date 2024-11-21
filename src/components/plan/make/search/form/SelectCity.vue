<script setup>
import SearchListItem from "@/components/plan/make/search/SearchListItem.vue";
import Select from "primevue/select";

import CascadeSelect from "primevue/cascadeselect";

import { ref } from "vue";

const selectCode = ref();
const citys = ref([
  {
    name: "경기",
    states: [
      {
        cname: "경기",
        sidoCode: 2,
        gugunCode: 0,
      },
      {
        cname: "양주시",
        sidoCode: 2,
        gugunCode: 0,
      },
      {
        cname: "의정부시",
        sidoCode: 2,
        gugunCode: 0,
      },
    ],
  },
  {
    name: "강원",
    states: [
      {
        cname: "강원",
        sidoCode: 2,
        gugunCode: 0,
      },
      {
        cname: "강릉시",
        sidoCode: 2,
        gugunCode: 0,
      },
      {
        cname: "동해시",
        sidoCode: 2,
        gugunCode: 0,
      },
    ],
  },
]);
</script>

<template>
  <div>
    <CascadeSelect
      v-model="selectCode"
      :options="citys"
      optionLabel="cname"
      optionGroupLabel="name"
      :optionGroupChildren="['states']"
      class="w-full"
      placeholder="지역"
    >
      <template #option="slotProps">
        <div class="flex items-center">
          <i v-if="slotProps.option.cname" class="pi pi-map-marker mr-2"></i>
          <span>{{ slotProps.option.cname || slotProps.option.name }}</span>
        </div>
      </template>
      <template #dropdownicon>
        <i class="pi pi-map" />
      </template>
    </CascadeSelect>
  </div>
</template>

<style scoped></style>
