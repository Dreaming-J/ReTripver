<script setup>
import MissionUpload from "@/components/plan/mission/MissionUpload.vue";
import { Timeline, Card } from "primevue";
import { ref, onMounted } from "vue";
import { useMakePlanStore } from "@/stores/makePlan-store";
import { storeToRefs } from "pinia";

const makePlanStore = useMakePlanStore()
const { newPlan } = storeToRefs(makePlanStore)

const plan = ref([
  {
    name: "경복궁",
    questImg: new URL("@/assets/img/sido/1.jpg", import.meta.url).href,
    uploadImg: "",
  },
  {
    name: "광화문",
    questImg: new URL("@/assets/img/sido/1.jpg", import.meta.url).href,
    uploadImg: "",
  },
  {
    name: "종로",
    questImg: new URL("@/assets/img/sido/1.jpg", import.meta.url).href,
    uploadImg: "",
  },
]);
</script>

<template>
  <div class="container">
    <Timeline :value="plan">
      <template #opposite="slotProps">
        <small class="text-surface-500 dark:text-surface-400">{{
          slotProps.item.name
        }}</small>
      </template>
      <template #content="slotProps">
        <Card class="mt-4 mb-8 card-container">
          <template #content>
            <MissionUpload :course="slotProps.item" />
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
