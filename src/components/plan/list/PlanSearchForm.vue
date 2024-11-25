<script setup>
import InputText from "primevue/inputtext";
import IconField from "primevue/iconfield";
import InputIcon from "primevue/inputicon";
import Select from 'primevue/select';

import { useRoute } from 'vue-router'
import { ref, onMounted } from "vue";
import { useAttractionStore } from "@/stores/attraction-store";

const route = useRoute()
const attractionStore = useAttractionStore()
const { getSidoList } = attractionStore

const sidoCode = route.params.sidoCode;

const selectedCity = ref({});
const keyword = ref("");
const guguns = ref([]);

onMounted(async () => {
  const response = await getSidoList()
  guguns.value = response.find(sido => sido.sidoCode == sidoCode).guguns
})

const emit = defineEmits(["searchKeyword"]);

const submitEvent = () => {
  emit("searchKeyword", selectedCity.value, keyword.value);
}

</script>

<template>
  <form class="flex">
    <div class="container">
        <div class="select-city col-4 p-3 pr-1">
            <Select v-model="selectedCity" :options="guguns" optionLabel="gugunName" placeholder="지역" class="w-full md:w-56" />
        </div>
        <div class="card flex col-8 p-3 pl-1">
          <IconField class="w-full">
            <InputText class="w-full" v-model="keyword" variant="filled" />
            <InputIcon class="pi pi-search" @click="submitEvent"/>
          </IconField>
        </div>
    </div>
  </form>
</template>

<style scoped>
form {
  width: 100%;
}
.container {
    width: 50%;
    
  display: flex;
  margin: 0 auto;
  justify-content: center;
}
</style>
