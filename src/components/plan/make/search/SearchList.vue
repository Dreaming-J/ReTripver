<script setup>
import SearchForm from "@/components/plan/make/search/SearchForm.vue";
import SearchListItem from "@/components/plan/make/search/SearchListItem.vue";
</script>

<template>
  <div>
    <div class="form-container">
      <SearchForm />
    </div>
    <div>
      <div>
        <SearchListItem />
      </div>
      <div>
        <SearchListItem />
      </div>
      <div>
        <SearchListItem />
      </div>
    </div>
  </div>
</template>

<style scoped>
</style>
