<script setup>
import {Card, Button} from 'primevue';
 
defineProps({
  attraction: {
    type: Object,
  },
});
</script>

<template>
  <div class="container">
    <Card style="width: 100%; height:100%; overflow: hidden">
        <template #header>
            <img alt="user header" :src="attraction.firstImage1"
            @error="
          $event.target.src =
            'https://via.placeholder.com/85x85/f5f5f5/999999?text=No+Image'
        " />
        </template>
        <template #title><p class="text-lg">{{ attraction.title }}</p></template>
        <template #subtitle><p class="text-base">{{ attraction.addr1?.trim().split(/\s+/).filter(Boolean).slice(0, 2).join(" ") || "" }}</p></template>
        <template #footer>
            <div class="flex justify-content-end">
                <Button label="Save" class="col-2" text size="small">
                  <font-awesome-icon class="btn-like" :icon="['far', 'heart']" />
                </Button>
            </div>
        </template>
    </Card>
  </div>
</template>

<style scoped>

:deep(.svg-inline--fa) {
  height: 25px;
}
</style>
