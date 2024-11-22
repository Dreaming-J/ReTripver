<script setup>
  import { ref } from 'vue'

  const props = defineProps({
    rank: {
      type: Object,
      required: true
    }
  })
</script>

<template>
  <div>
    <div>
      <p>등수: {{ rank.rank }}</p>
      <p>사용자: {{ rank.userId }}</p>
      <p>제목: {{ rank.title }}</p>
      <p>지역: {{ rank.sidoName }}</p>
      <p>좋아요: {{ rank.likeCount }}</p>
      <p>방문지 {{ rank.courses.length }}개</p>
      <template v-for="course in rank.courses" v-key="course.id">
        <p>&nbsp&nbsp순번: {{ course.courseOrder }}</p>
        <p>&nbsp&nbsp이름: {{ course.attraction.title }}</p>
        <p>&nbsp&nbsp주소: {{ course.attraction.addr1 }}</p>
        <p>&nbsp&nbsp사진: {{ course.attraction.firstImage1 }}</p>
        <p>&nbsp&nbsp위도, 경도: {{ course.attraction.latitude }}, {{ course.attraction.longitude }}</p>
        <p>&nbsp&nbsp미션 사진: <img :src="course.missionImg" width="40px"></p>
        <p>&nbsp&nbsp유저 사진: <img :src="course.userImg" width="40px"></p>
        <br>
      </template>
    </div>
    <br>
    <hr>
    <br>
  </div>
</template>

<style scoped></style>
