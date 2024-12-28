<script setup>
import AttractionInfo from '@/components/attraction/AttractionInfo.vue';
import {Card, Button,Dialog } from 'primevue';
import { ref } from 'vue';
 
defineProps({
  attraction: {
    type: Object,
  },
});

const visible = ref(false);

</script>

<template>
  <div class="container">
    <!-- {{ attraction }} -->
    <Card style="width: 100%; height:100%; overflow: hidden" @click="visible=true">
        <template #header>
            <img alt="user header" :src="attraction.firstImage1" height="300px"
            @error="
          $event.target.src =
            'https://via.placeholder.com/85x85/f5f5f5/999999?text=No+Image'
        " />
        </template>
        <template #title><p class="text-lg truncate-title">{{ attraction.title }}</p></template>
        <template #subtitle><p class="text-base truncate-subtitle">{{ attraction.addr1?.trim().split(/\s+/).filter(Boolean).slice(0, 2).join(" ") || "" }}</p></template>
    </Card>

    <Dialog v-model:visible="visible" modal header=" " :style="{ width: '60rem', heigt: '20rem' }">
        <AttractionInfo :attraction="attraction"/>
    </Dialog>
  </div>
</template>

<style scoped>

:deep(.svg-inline--fa) {
  height: 25px;
}

.truncate-title {
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}

/* 부제목 텍스트 줄임 */
.truncate-subtitle {
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}
</style>
