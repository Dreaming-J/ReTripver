<script setup>
import MyPageMenu from '@/components/user/myPage/MyPageMenu.vue';
import { RouterView } from 'vue-router';
</script>

<template>
  <div class="container">
    <div class="menu-container">
      <MyPageMenu />
    </div>
    <div class="view-container">
      <RouterView />
    </div>
  </div>
</template>

<style scoped>
.container {
  width: 1000px;
  border: 1px solid red;
  margin: 0 auto;
  display: flex;
}

.menu-container {
  width: 200px;
  border: 1px solid blue;
}

.view-container {
  width: 800px;
  border: 1px solid black;
}
</style>
