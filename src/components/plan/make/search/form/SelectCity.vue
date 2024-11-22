<script setup>
import SearchListItem from "@/components/plan/make/search/SearchListItem.vue";
import Select from "primevue/select";
import CascadeSelect from "primevue/cascadeselect";

import { storeToRefs } from "pinia";
import { useAttractionStore } from "@/stores/attraction-store";
import { useMakePlanStore } from "@/stores/makePlan-store";
import { ref, onMounted, watch } from "vue";

const attractionStore = useAttractionStore()
const { getSidoList } = attractionStore

const makePlanStore = useMakePlanStore()
const { searchOption } = storeToRefs(makePlanStore)

const selectCode = ref({})
const sidoList = ref([])

onMounted(async () => {
  sidoList.value = await getSidoList()
})

watch(
  selectCode, (newSelectCode) => {
    searchOption.value.sidoCode = newSelectCode.sidoCode
    searchOption.value.gugunCode = newSelectCode.gugunCode
  },
  {immediate: true}
)
</script>

<template>
  <div>
    <CascadeSelect
      v-model="selectCode"
      :options="sidoList"
      optionLabel="gugunName"
      optionGroupLabel="sidoName"
      :optionGroupChildren="['guguns']"
      class="w-full"
      placeholder="지역"
    >
      <template #option="slotProps">
        <div class="flex items-center">
          <i v-if="slotProps.option.gugunName" class="pi pi-map-marker mr-2"></i>
          <span>{{ slotProps.option.gugunName || slotProps.option.sidoName }}</span>
        </div>
      </template>
      <template #dropdownicon>
        <i class="pi pi-map" />
      </template>
    </CascadeSelect>
  </div>
</template>

<style scoped></style>
