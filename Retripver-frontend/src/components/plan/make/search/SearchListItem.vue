<script setup>
import { Button, Card } from "primevue";
import ListItem from "@/components/plan/make/item/ListItem.vue";

defineProps({
  search: {
    type: Object,
  },
});

const emit = defineEmits(["selectSearchItem"]);

const selectLocation = (location) => {
  emit("selectSearchItem", location);
};
</script>

<template>
  <div class="item-wrapper">
    <div class="list-container col-11 p-2 h-full">
      <ListItem :info="search" />
    </div>
    <div class="col-1 p-0">
      <Button
        class="btn-plus"
        :class="{ 'btn-plus-selected': search.isSelected }"
        severity="secondary"
        @click="selectLocation(search)"
        :disabled="search.isSelected"
      >
        <font-awesome-icon :icon="['fas', 'plus']" />
      </Button>
    </div>
  </div>
</template>

<style scoped>
.item-wrapper {
  width: 100%;
  height: 100%;
  padding: 10px;
  display: flex;
  align-items: center;
  /* border-top: 1px solid lightgray;
  border-bottom: 1px solid lightgray; */
}

.btn-plus {
  border: none;
}

.btn-plus-selected {
  border: none;
  background: navajowhite;
  color: white;
}

.btn-plus:hover {
  border: none;
  background: navajowhite;
  color: white;
}
</style>
