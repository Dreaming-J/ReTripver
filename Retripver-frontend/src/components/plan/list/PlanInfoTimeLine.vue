<script setup>
import CourseImg from "@/components/plan/list/CourseImg.vue";
import { Timeline, Card } from "primevue";
import { ref } from "vue";

const props = defineProps({
    courses: {
        type: Object
    }
}) 

</script>

<template>
  <div class="container">
    <Timeline :value="courses">
      <template #opposite="slotProps">
        <small class="text-surface-500 dark:text-surface-400">{{
          slotProps.item.attraction.title
        }}</small>
      </template>
      <template #content="slotProps">
        <Card class="mt-4 mb-8 card-container">
          <template #content>
            <CourseImg :course="slotProps.item" />
          </template>
        </Card>
      </template>
    </Timeline>
  </div>
</template>

<style scoped>
.container {
  width: 100%;
  height: 100%;
}

:deep(.p-card-body) {
  /* width: 700px; */
  height: 300px;
}

:deep(.p-timeline-event-opposite) {
  flex: none;
  width: 400px;
}
</style>
