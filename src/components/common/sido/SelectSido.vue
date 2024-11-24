<script setup>
import SelectSidoItem from "@/components/common/sido/SelectSidoItem.vue";
import { ref } from "vue";

const sidos = ref([
  {
    sidoCode:1,
    name: "서울",
    img :new URL('@/assets/img/sido/1.jpg', import.meta.url).href,
    desc :"서울 설명"
  },
  {
    sidoCode:1,
    name: "서울",
    img :new URL('@/assets/img/sido/1.jpg', import.meta.url).href,
    desc :"서울 설명"
  },
  {
    sidoCode:1,
    name: "서울",
    img :new URL('@/assets/img/sido/1.jpg', import.meta.url).href,
    desc :"서울 설명"
  },
  {
    sidoCode:1,
    name: "서울",
    img :new URL('@/assets/img/sido/1.jpg', import.meta.url).href,
    desc :"서울 설명"
  },
  {
    sidoCode:1,
    name: "서울",
    img :new URL('@/assets/img/sido/1.jpg', import.meta.url).href,
    desc :"서울 설명"
  },
  {
    sidoCode:1,
    name: "서울",
    img :new URL('@/assets/img/sido/1.jpg', import.meta.url).href,
    desc :"서울 설명"
  },
  {
    sidoCode:1,
    name: "서울",
    img :new URL('@/assets/img/sido/1.jpg', import.meta.url).href,
    desc :"서울 설명"
  },
  {
    sidoCode:1,
    name: "서울",
    img :new URL('@/assets/img/sido/1.jpg', import.meta.url).href,
    desc :"서울 설명"
  },
  {
    sidoCode:1,
    name: "서울",
    img :new URL('@/assets/img/sido/1.jpg', import.meta.url).href,
    desc :"서울 설명"
  },
  {
    sidoCode:1,
    name: "서울",
    img :new URL('@/assets/img/sido/1.jpg', import.meta.url).href,
    desc :"서울 설명"
  },
  {
    sidoCode:1,
    name: "서울",
    img :new URL('@/assets/img/sido/1.jpg', import.meta.url).href,
    desc :"서울 설명"
  },
  {
    sidoCode:1,
    name: "서울",
    img :new URL('@/assets/img/sido/1.jpg', import.meta.url).href,
    desc :"서울 설명"
  },
  {
    sidoCode:1,
    name: "서울",
    img :new URL('@/assets/img/sido/1.jpg', import.meta.url).href,
    desc :"서울 설명"
  },
]);
</script>

<template>
  <div class="flex flex-wrap">
    <div class="sido-item col-3 p-5" v-for="sido in sidos" :key="sido.sidoCode">
      <SelectSidoItem :sido="sido"/>
    </div>
  </div>
</template>

<style scoped>
.sido-item {
  /* border: 1px solid peru; */
  height: 380px;
}
</style>
