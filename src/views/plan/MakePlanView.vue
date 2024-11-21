<script setup>
import SelectedList from "@/components/plan/make/select/SelectedList.vue";
import SearchList from "@/components/plan/make/search/SearchList.vue";
import MapContent from "@/components/plan/make/map/MapContent.vue";

import Button from "primevue/button";

import { ref } from "vue";

const isSearchVisible = ref(true);
const routeType = ref(false);

const changeRouteType = (changedRouteType) => {
  routeType.value = changedRouteType;
};
</script>

<template>
  <div class="container">
    <div class="content-wrapper">
      <div class="seletced-list-container">
        <div class="seletced-list">
          <SelectedList @change-route-type="changeRouteType" />
        </div>

        <div
          :class="[
            'search-list shadow-2',
            { 'search-list-hide': !isSearchVisible },
          ]"
        >
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
        <MapContent :route-type="routeType" />
      </div>
    </div>
  </div>
</template>

<style scoped>
.container {
  display: flex;
  height: calc(100vh - 100px);
  width: 100%;
}

.content-wrapper {
  position: relative;
  display: flex;
  height: 100%;
  width: 100%;
}

.seletced-list-container {
  display: flex;
  width: 500px;
  height: 100%;
  position: relative;
  flex-shrink: 0;
}

.seletced-list {
  width: 500px;
  height: 100%;
  position: relative;
  z-index: 3;
  background-color: white;
}

.search-list {
  width: 500px;
  border-radius: 15px;
  background-color: white;
  /* border: 1px solid lightgray; */
  height: 100%;
  left: 510px;
  position: absolute; 
  transition: 0.3s ease;
  z-index: 2;
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
  /* width: calc(100vw - 510px); */
  flex: 1;
  height: 100%;
  position: relative;
  z-index: 1;
  min-width: 0;
}
</style>
