<script setup>
import SelectedListItem from "@/components/plan/make/select/SelectedListItem.vue";
import OptimizeDialog from "@/components/plan/make/select/OptimizeDialog.vue";
import { Button, Dialog } from "primevue";
import { ref } from "vue";
import { useMakePlansStore } from "@/stores/makePlans";

const store = useMakePlansStore();

const emit = defineEmits(["changeRouteType"]);

const visible = ref(false);
const routeType = ref(false); // true -> 자동차, false -> 도보

const changeRouteTypeFunc = () => {
  routeType.value = !routeType.value;
  emit("changeRouteType", routeType.value);
};
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
      <div
        class="selected-list-item"
        v-for="select in store.selectList"
        :key="select.no"
      >
        <SelectedListItem :select="select" />
      </div>
    </div>
  </div>

  <div class="select-btn">
    <div class="toggle-btn">
      <Button label="선택" @click="changeRouteTypeFunc" />
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
  border: 1px solid lightgray;
  margin: 10px;
  height: 80px;
  border-radius: 5px;
}

.select-btn {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 15px;
}
</style>
