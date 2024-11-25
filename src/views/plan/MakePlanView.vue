<script setup>
import SelectedList from "@/components/plan/make/select/SelectedList.vue";
import SearchList from "@/components/plan/make/search/SearchList.vue";
import MapContent from "@/components/plan/make/map/MapContent.vue";

import { Button, Dialog } from "primevue";

import { ref } from "vue";
import { useRouter } from "vue-router";

const router = useRouter();

const isSearchVisible = ref(true);
const routeType = ref(false);

const isNotSelected = ref(false);
const isMakePlanvVisible = ref(false);

const changeRouteType = (changedRouteType) => {
  routeType.value = changedRouteType;
};

const makePlan = (selectList) => {
  if (selectList.length <= 0) {
    isNotSelected.value = true;
  } else {
    isMakePlanvVisible.value = true;
  }
};

const goQuestPage = () => {
  router.push({ name: "make-mission" });
};
</script>

<template>
  <div class="container">
    <div class="content-wrapper">
      <div class="seletced-list-container">
        <div class="seletced-list">
          <SelectedList
            @change-route-type="changeRouteType"
            @make-plan="makePlan"
          />
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

    <Dialog
      v-model:visible="isNotSelected"
      modal
      header=" "
      :style="{ width: '25rem' }"
    >
      <span class="text-lg dark:text-surface-400 block mb-6 text-center"
        >여행지를 추가해주세요.</span
      >
      <div class="flex justify-end gap-2">
        <Button
          class="w-full"
          type="button"
          label="확인"
          @click="isNotSelected = false"
        ></Button>
      </div>
    </Dialog>

    <Dialog
      v-model:visible="isMakePlanvVisible"
      modal
      header="여행을 만들겠습니까?"
      :style="{ width: '25rem' }"
    >
      <span class="text-lg dark:text-surface-400 block mt-6 mb-6"
        >확인 후, 미션 사진이 생성됩니다!</span
      >
      <div class="flex items-center text-sm mb-5">
        * 만드신 여행 계획은 수정이 불가능합니다.
      </div>
      <div class="flex justify-end gap-2">
        <Button
          class="col-6"
          type="button"
          label="취소"
          severity="secondary"
          @click="isMakePlanvVisible = false"
        ></Button>
        <Button
          class="col-6"
          type="button"
          label="확인"
          @click="goQuestPage"
        ></Button>
      </div>
    </Dialog>
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
  width: 400px;
  height: 100%;
  position: relative;
  flex-shrink: 0;
}

.seletced-list {
  width: 400px;
  height: 100%;
  position: relative;
  z-index: 3;
  background-color: white;
}

.search-list {
  width: 400px;
  border-radius: 15px;
  background-color: white;
  /* border: 1px solid lightgray; */
  height: 100%;
  left: 410px;
  position: absolute;
  transition: 0.3s ease;
  z-index: 2;
}

.search-list-hide {
  transform: translate(-410px, 0);
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
