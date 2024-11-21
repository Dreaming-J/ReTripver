<script setup>
import InputText from "primevue/inputtext";
import IconField from "primevue/iconfield";
import InputIcon from "primevue/inputicon";

import { ref } from "vue";
const keyword = ref(null);
</script>

<template>
  <div>
    <div class="card flex">
      <IconField class="w-full">
        <InputText class="w-full" v-model="keyword" variant="filled" />
        <InputIcon class="pi pi-search" />
      </IconField>
    </div>
  </div>
</template>

<style scoped></style>
