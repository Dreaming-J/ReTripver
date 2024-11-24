<script setup>
import SelectSidoItem from "@/components/common/sido/SelectSidoItem.vue";
import ListItem from "@/components/plan/make/item/ListItem.vue";
import { ref } from "vue";
import { RouterLink } from "vue-router";
const sidos = ref([
  {
    sidoCode:1,
    name: "서울",
    img :new URL('@/assets/img/sido/1.jpg', import.meta.url).href,
    desc :"서울 설명"
  },
  {
    sidoCode:2,
    name: "인천",
    img :new URL('@/assets/img/sido/1.jpg', import.meta.url).href,
    desc :"인천 설명"
  },
  {
    sidoCode:3,
    name: "대전",
    img :new URL('@/assets/img/sido/1.jpg', import.meta.url).href,
    desc :"대전 설명"
  },
  {
    sidoCode:4,
    name: "대구",
    img :new URL('@/assets/img/sido/1.jpg', import.meta.url).href,
    desc :"대구 설명"
  },
  {
    sidoCode:5,
    name: "광주",
    img :new URL('@/assets/img/sido/1.jpg', import.meta.url).href,
    desc :"광주 설명"
  },
  {
    sidoCode:6,
    name: "부산",
    img :new URL('@/assets/img/sido/1.jpg', import.meta.url).href,
    desc :"부산 설명"
  },
  {
    sidoCode:7,
    name: "울산",
    img :new URL('@/assets/img/sido/1.jpg', import.meta.url).href,
    desc :"울산 설명"
  },
  {
    sidoCode:8,
    name: "세종",
    img :new URL('@/assets/img/sido/1.jpg', import.meta.url).href,
    desc :"세종 설명"
  },
  {
    sidoCode:31,
    name: "경기",
    img :new URL('@/assets/img/sido/1.jpg', import.meta.url).href,
    desc :"경기 설명"
  },
  {
    sidoCode:32,
    name: "강원",
    img :new URL('@/assets/img/sido/1.jpg', import.meta.url).href,
    desc :"강원 설명"
  },
  {
    sidoCode:33,
    name: "충북",
    img :new URL('@/assets/img/sido/1.jpg', import.meta.url).href,
    desc :"충북 설명"
  },
  {
    sidoCode:34,
    name: "충남",
    img :new URL('@/assets/img/sido/1.jpg', import.meta.url).href,
    desc :"충남 설명"
  },
  {
    sidoCode:35,
    name: "경북",
    img :new URL('@/assets/img/sido/1.jpg', import.meta.url).href,
    desc :"경북 설명"
  },
  {
    sidoCode:36,
    name: "경남",
    img :new URL('@/assets/img/sido/1.jpg', import.meta.url).href,
    desc :"경남 설명"
  },
  {
    sidoCode:37,
    name: "전북",
    img :new URL('@/assets/img/sido/1.jpg', import.meta.url).href,
    desc :"전북 설명"
  },
  {
    sidoCode:38,
    name: "전남",
    img :new URL('@/assets/img/sido/1.jpg', import.meta.url).href,
    desc :"전남 설명"
  },
  {
    sidoCode:39,
    name: "제주",
    img :new URL('@/assets/img/sido/1.jpg', import.meta.url).href,
    desc :"제주 설명"
  },
]);
</script>

<template>
  <div class="flex flex-wrap">
    <div class="sido-item col-3 p-5" v-for="sido in sidos" :key="sido.sidoCode">
      <RouterLink :to="{name:'plan-list-sido', params: { sidoCode: sido.sidoCode }}">
       <SelectSidoItem :sido="sido"/>
      </RouterLink>
    </div>
  </div>
</template>

<style scoped>
.sido-item {
  /* border: 1px solid peru; */
  height: 380px;
}
</style>
