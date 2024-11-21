<script setup>
import SelectedListItem from "@/components/plan/make/select/SelectedListItem.vue";
import OptimizeDialog from "@/components/plan/make/select/OptimizeDialog.vue";
import ToggleBtn from "./ToggleBtn.vue";
import { Button, Dialog } from "primevue";
import { ref, watch } from "vue";
import { useMakePlansStore } from "@/stores/makePlans";
import { VueDraggableNext } from "vue-draggable-next";

const emit = defineEmits(["changeRouteType"]);

const visible = ref(false);
const routeType = ref(false); // true -> 자동차, false -> 도보

const store = useMakePlansStore();
const endDrag = (event) => {
  if (event.moved) {
    // moved 데이터를 활용하여 순서 업데이트
    const { newIndex, oldIndex } = event.moved;
    console.log(`Moved from ${oldIndex} to ${newIndex}`);

    // courseOrder 재정렬
    store.selectList = store.selectList.map((item, index) => ({
      ...item,
      courseOrder: index + 1,
    }));
  }
};

const changeRouteTypeFunc = (routeType) => {
  emit("changeRouteType", routeType);
};

const deleteSearchItem = (no) => {
  store.removeSeletedItem(no);
};

watch(
  () => routeType.value,
  (newType) => changeRouteTypeFunc(newType)
);
</script>

<template>
  <div class="container">
    <div class="seleted-header">
      <div class="header-item fa-lg">
        <font-awesome-icon :icon="['fas', 'location-dot']" />
      </div>

      <div class="header-item fa-lg">여행 장소 선택</div>

      <div class="header-item">
        <Button
          label="코스 최적화"
          @click="visible = true"
          severity="warn"
          variant="outlined"
        />

        <div class="card flex justify-center">
          <Dialog v-model:visible="visible" modal header=" ">
            <OptimizeDialog />
          </Dialog>
        </div>
      </div>
    </div>

    <div class="select-list">
      <div v-if="store.selectList.length === 0" class="text-center m-6">
        여행지를 추가해주세요.
      </div>
      <VueDraggableNext
        class="w-full"
        v-model="store.selectList"
        @change="endDrag"
      >
        <div
          class="selected-list-item"
          v-for="select in store.selectList"
          :key="select.no"
        >
          <SelectedListItem
            :select="select"
            @delete-search-item="deleteSearchItem"
          />
        </div>
      </VueDraggableNext>
    </div>
  </div>

  <div class="select-btn">
    <div class="toggle-btn">
      <!-- <Button label="선택" @click="changeRouteTypeFunc" /> -->
      <ToggleBtn v-model="routeType" />
    </div>
    <div>
      <Button label="여행 만들기" severity="warn" variant="outlined" />
    </div>
  </div>
</template>

<style scoped>
.seleted-header {
  display: flex;
  align-items: center;
  height: 50px;
  padding: 5px;
}

.header-item {
  padding: 0px 10px;
  margin-right: 5px;
}

.header-item:last-child {
  margin-right: 0px;
  margin-left: auto;
}

.select-list {
  padding: 5px;
}

.selected-list-item {
  /* border: 1px solid lightgray; */
  /* padding: 10px; */
  margin-bottom: 10px;
  height: 110px;
  /* border-radius: 10px; */
}

.select-btn {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 15px;
}

.toggle-btn {
  width: 80px;
  height: 40px;
}

/* 호버 효과 */
.selected-list-item {
  transition: all 0.3s cubic-bezier(0.4, 0, 0.2, 1);
}

.selected-list-item:hover {
  transform: translateY(-2px);
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.05);
}
</style>
