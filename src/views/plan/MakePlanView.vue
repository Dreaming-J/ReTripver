<script setup>
import { ref } from "vue";

import SelectedList from "@/components/plan/make/SelectedList.vue";
import SearchList from "@/components/plan/make/SearchList.vue";
import MapContent from "@/components/plan/make/MapContent.vue";

import Button from "primevue/button";

const isSearchVisible = ref(true);
</script>

<template>
  <div class="container">
    <div class="seletced-list-container">
      <div class="seletced-list">
        <SelectedList />
      </div>

      <div :class="['search-list', { 'search-list-hide': !isSearchVisible }]">
        <div>
          <SearchList />
        </div>

        <div class="btn-search-toggle">
          <Button
            class="btn-toggle"
            :icon="
              isSearchVisible ? 'pi pi-chevron-left' : 'pi pi-chevron-right'
            "
            severity="secondary"
            @click="isSearchVisible = !isSearchVisible"
          />
        </div>
      </div>
    </div>

    <div class="map-content">
      <MapContent />
    </div>
  </div>
</template>

<style scoped>
.container {
  display: flex;
}

.seletced-list-container {
  display: flex;
  width: 500px;
  height: 1200px;
  position: relative;
}

.seletced-list {
  width: 500px;
  height: 100%;
  position: relative;
  z-index: 2;
  background-color: white;
}

.search-list {
  width: 500px;
  border-radius: 15px;
  background-color: white;
  border: 1px solid lightgray;
  height: 100%;
  left: 510px;
  position: absolute;
  transition: 0.4s;
}

.search-list-hide {
  transform: translate(-510px, 0);
}

.btn-search-toggle {
  position: absolute;
  top: 40%;
  left: 100%;
}

.btn-toggle {
  height: 50px;
  width: 25px;
  border-radius: 0px;
  border-top-right-radius: 10px;
  border-bottom-right-radius: 10px;
}

.map-content {
  width: 100%;
  height: 1200px;
  background-color: lightblue;
}
</style>
