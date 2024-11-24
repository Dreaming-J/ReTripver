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
    desc :"역사와 현대가 어우러진 도시, 서울! 전통과 미래를 한눈에 느껴보세요."
  },
  {
    sidoCode:2,
    name: "인천",
    img :new URL('@/assets/img/sido/1.jpg', import.meta.url).href,
    desc :"과거와 현재가 공존하는 항구 도시, 인천! 바다와 도시의 매력을 동시에 즐겨보세요."
  },
  {
    sidoCode:3,
    name: "대전",
    img :new URL('@/assets/img/sido/1.jpg', import.meta.url).href,
    desc :"과학과 자연이 만나는 도시, 대전! 여유로운 여행을 떠나보세요."
  },
  {
    sidoCode:4,
    name: "대구",
    img :new URL('@/assets/img/sido/1.jpg', import.meta.url).href,
    desc :"따뜻한 사람들과 뜨거운 열정의 도시, 대구! 전통과 현대가 어우러진 매력을 만나보세요."
  },
  {
    sidoCode:5,
    name: "광주",
    img :new URL('@/assets/img/sido/1.jpg', import.meta.url).href,
    desc :"빛의 도시, 광주! 예술과 미식이 어우러진 특별한 여행을 경험하세요."
  },
  {
    sidoCode:6,
    name: "부산",
    img :new URL('@/assets/img/sido/1.jpg', import.meta.url).href,
    desc :"푸른 바다와 맛있는 먹거리의 천국, 부산! 즐거운 추억이 기다립니다."
  },
  {
    sidoCode:7,
    name: "울산",
    img :new URL('@/assets/img/sido/1.jpg', import.meta.url).href,
    desc :"산업과 자연의 조화, 울산! 푸른 바다와 웅장한 자연이 기다립니다."
  },
  {
    sidoCode:8,
    name: "세종",
    img :new URL('@/assets/img/sido/1.jpg', import.meta.url).href,
    desc :"미래를 품은 도시, 세종! 자연 속에서 누리는 여유를 만나보세요."
  },
  {
    sidoCode:31,
    name: "경기",
    img :new URL('@/assets/img/sido/1.jpg', import.meta.url).href,
    desc :"역사와 현대가 공존하는 경기! 서울 근교에서 만나는 다채로운 매력을 느껴보세요."
  },
  {
    sidoCode:32,
    name: "강원",
    img :new URL('@/assets/img/sido/1.jpg', import.meta.url).href,
    desc :"맑은 공기와 푸른 산이 반기는 강원도! 자연의 품에서 힐링하세요."
  },
  {
    sidoCode:33,
    name: "충북",
    img :new URL('@/assets/img/sido/1.jpg', import.meta.url).href,
    desc :"산과 호수가 어우러진 충북! 자연 속에서 평온한 시간을 즐겨보세요."
  },
  {
    sidoCode:34,
    name: "충남",
    img :new URL('@/assets/img/sido/1.jpg', import.meta.url).href,
    desc :"넉넉한 풍경과 따뜻한 정이 있는 충남! 힐링이 가득한 여행을 떠나보세요."
  },
  {
    sidoCode:35,
    name: "경북",
    img :new URL('@/assets/img/sido/1.jpg', import.meta.url).href,
    desc :"역사와 전통이 살아 숨 쉬는 경북! 아름다운 자연과 문화유산을 만나보세요."
  },
  {
    sidoCode:36,
    name: "경남",
    img :new URL('@/assets/img/sido/1.jpg', import.meta.url).href,
    desc :"다채로운 자연과 풍요로운 미식의 경남! 바다와 산이 어우러진 특별한 여행지."
  },
  {
    sidoCode:37,
    name: "전북",
    img :new URL('@/assets/img/sido/1.jpg', import.meta.url).href,
    desc :"전통의 멋과 맛이 가득한 전북! 따뜻한 정과 풍경을 느껴보세요."
  },
  {
    sidoCode:38,
    name: "전남",
    img :new URL('@/assets/img/sido/1.jpg', import.meta.url).href,
    desc :"바다와 섬이 만들어낸 낭만, 전라남도! 정겨운 풍경이 가득합니다."
  },
  {
    sidoCode:39,
    name: "제주",
    img :new URL('@/assets/img/sido/1.jpg', import.meta.url).href,
    desc :"천혜의 자연을 품은 섬, 제주! 일상에서 벗어나 자연 속으로 떠나보세요."
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
