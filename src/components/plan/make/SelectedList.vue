<script setup>
import SelectedListItem from "@/components/plan/make/SelectedListItem.vue";
import OptimizeDialog from "@/components/plan/make/OptimizeDialog.vue";

import { Button, Dialog } from "primevue";

import { ref } from "vue";

const visible = ref(false);
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
      <div class="selected-list-item">
        <SelectedListItem />
      </div>
      <div class="selected-list-item">
        <SelectedListItem />
      </div>
      <div class="selected-list-item">
        <SelectedListItem />
      </div>
      <div class="selected-list-item">
        <SelectedListItem />
      </div>
    </div>
  </div>

  <div class="select-btn">
    <Button label="여행 만들기" severity="warn" variant="outlined" />
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
  justify-content: flex-end;
  padding: 15px;
}
</style>
