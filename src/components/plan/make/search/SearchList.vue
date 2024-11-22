<script setup>
import SearchForm from "@/components/plan/make/search/form/SearchForm.vue";
import SearchListItem from "@/components/plan/make/search/SearchListItem.vue";
import { useMakePlansStore } from "@/stores/makePlans";

const store = useMakePlansStore();

const selectSearchItem = (item) => {
  store.addSelectItem(item);
};
</script>

<template>
  <div>
    <div class="form-container">
      <SearchForm />
    </div>
    <div class="p-3 select-list">
      <div
        class="selected-list-item"
        v-for="search in store.searchList"
        :key="search.no"
      >
        <SearchListItem
          :search="search"
          @select-search-item="selectSearchItem"
        />
      </div>
    </div>
  </div>
</template>

<style scoped>
.select-list {
  padding: 5px;
}

.selected-list-item {
  border-bottom: 1px solid rgb(236, 236, 236);
  /* padding: 10px; */
  /* margin-bottom: 10px; */
  height: 110px;
  /* border-radius: 10px; */
  transition: all 0.2s cubic-bezier(0.4, 0, 0.2, 1);
}

.selected-list-item:first-child {
  border-top: 1px solid (236, 236, 236);
}

/* .selected-list-item:hover {
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  transform: translateY(-2px);
  transform: scale(1.01);
} */
</style>
