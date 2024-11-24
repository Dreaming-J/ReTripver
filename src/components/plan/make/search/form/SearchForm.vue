<script setup>
import SelectCity from "@/components/plan/make/search/form/SelectCity.vue";
import SearchKeyword from "@/components/plan/make/search/form/SearchKeyword.vue";
</script>

<template>
  <div class="w-full flex">
    <div class="col-4 p-3 pr-1">
      <SelectCity/>
    </div>
    <div class="col-8 p-3 pl-1"> 
      <SearchKeyword />
    </div>
  </div>
</template>

<style scoped></style>
