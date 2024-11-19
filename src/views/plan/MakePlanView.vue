<script setup>
import { ref } from "vue";

import SelectedList from "@/components/plan/make/select/SelectedList.vue";
import SearchList from "@/components/plan/make/search/SearchList.vue";
import MapContent from "@/components/plan/make/map/MapContent.vue";

import Button from "primevue/button";

const isSearchVisible = ref(true);

const selectList = ref([
  { no: 3837, title: "경복궁", lat: 37.5788222356, lng: 126.9769930325 },
  { no: 4486, title: "남산서울타워", lat: 37.5511089858, lng: 126.9878983791 },
  {
    no: 3931,
    title: "창덕궁과 후원 [유네스코 세계유산]",
    lat: 37.5809583673,
    lng: 126.9919888278,
  },
]);
</script>

<template>
  <div class="container">
    <div class="seletced-list-container">
      <div class="seletced-list">
        <SelectedList :select-list="selectList" />
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
      <MapContent :select-list="selectList" />
    </div>
  </div>
</template>

<style scoped>
.container {
  display: flex;
  height: calc(100vh - 100px);
}

.seletced-list-container {
  display: flex;
  width: 500px;
  height: 100%;
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
  /* left: 510px;
   */
  left: 0px;
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
  height: 100%;
  padding: 1px; /* border 크기만큼 padding 추가 */
  box-sizing: border-box; /* padding을 width/height에 포함 */
}
</style>
