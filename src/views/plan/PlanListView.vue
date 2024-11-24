<script setup>
import PlanList from "@/components/plan/list/PlanList.vue";
import PlanSearchForm from "@/components/plan/list/PlanSearchForm.vue";
import { useRoute } from "vue-router";

const route = useRoute();
const sidoCode = route.params.sidoCode;

const searchKeyword = (selectedCity, keyword) => {
  console.log(selectedCity, keyword )
}
</script>

<template>
  <div class="view-container">
    <div class="form-cotainer mt-5">
      <PlanSearchForm @searchKeyword="searchKeyword"/>
    </div>
    <div class="list-container"> 
      <PlanList :sidoCode="sidoCode"/>
    </div>
  </div>
</template>

<style scoped>
.view-container {
  width: 1300px;
  margin: 0 auto;
  /* border: 1px solid black; */
}

.form-cotainer {
  width: 100%;
  height: 100px;
  display: flex;
  align-items: center;
  justify-content: center;
}

.list-container {
  width: 100%; 
}
</style>
