<script setup>
import { Button, Card } from "primevue";
import ListItem from "@/components/plan/make/item/ListItem.vue";
defineProps({
  select: {
    type: Object,
  },
});

const emit = defineEmits(["deleteSearchItem"]);

const deleteLocation = (no) => {
  emit("deleteSearchItem", no);
};
</script>

<template>
  <div class="container shadow-2">
    <div class="list-container col-11 p-0 h-full">
      <ListItem :info="select" />
    </div>
    <div class="col-1 p-0">
      <Button
        icon="pi pi-minus-circle"
        severity="danger"
        variant="text"
        rounded
        @click="deleteLocation(select.no)"
      />
    </div>
  </div>
</template>

<style scoped>
.container {
  width: 100%;
  height: 100%;
  padding: 10px;
  display: flex;
  align-items: center;
  border-radius: 10px;
}
</style>
