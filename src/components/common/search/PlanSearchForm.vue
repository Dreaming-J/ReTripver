<script setup>
import InputText from "primevue/inputtext";
import IconField from "primevue/iconfield";
import InputIcon from "primevue/inputicon";
import Select from 'primevue/select';

import { ref } from "vue";

const selectedCity = ref();
const keyword = ref();
const cities = ref([
    { name: 'New York', code: 'NY' },
    { name: 'Rome', code: 'RM' },
    { name: 'London', code: 'LDN' },
    { name: 'Istanbul', code: 'IST' },
    { name: 'Paris', code: 'PRS' }
]);

const emit = defineEmits(["searchKeyword"]);

const sumbitEvent = () => {
  emit("searchKeyword", selectedCity.value, keyword.value);
}

</script>

<template>
  <form @submit.prevent="sumbitEvent">
  <div class="container">
      <div class="select-city col-4">
          <Select v-model="selectedCity" :options="cities" optionLabel="name" placeholder="지역" class="w-full md:w-56" />
      </div>
      <div class="card flex col-8">
        <IconField class="w-full">
          <InputText class="w-full" v-model="keyword" variant="filled" />
          <InputIcon class="pi pi-search"/>
        </IconField>
      </div>
   </div>
  </form>
</template>

<style scoped>
form {
  width: 100%;
  height: 100%;
}
.container {
    width: 50%;
  display: flex;
  margin: 0 auto;
  justify-content: center;
}
</style>
