<script setup>
import SearchForm from "@/components/plan/make/search/form/SearchForm.vue";
import AttractionList from "@/components/attraction/AttractionList.vue"
</script>

<template>
  <div class="view-container">
    <div class="form-container mt-5">
        <SearchForm /> 
    </div>
    <div class="list-container">
        <div>
            <AttractionList />
        </div>
    </div>
  </div>
</template>

<style scoped>
.view-container {
    width: 1300px;
    margin: 0 auto;
    /* border: 1px solid black; */
}

.form-container {
    width: 50%;
    height: 100px;
    margin: 0 auto;
    display: flex;
    align-items: center;
    justify-content: center;
}

.list-container {
    width: 100%;
}

</style>
