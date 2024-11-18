<script setup>
import ProfileBtnList from "@/components/common/header/ProfileBtnList.vue";
import SignBtnList from "@/components/common/header/SignBtnList.vue";

import { ref } from "vue";

const isLogin = ref(true);
</script>

<template>
  <div>
    <div v-if="isLogin">
      <ProfileBtnList />
    </div>
    <div v-else>
      <SignBtnList />
    </div>
  </div>
</template>

<style scoped></style>
