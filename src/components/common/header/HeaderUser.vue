<script setup>
  import ProfileBtnList from "@/components/common/header/userItems/ProfileBtnList.vue";
  import SignBtnList from "@/components/common/header/userItems/SignBtnList.vue";
  import { useUserStore } from "@/stores/user-store";

  const userStore = useUserStore();
  const isLogin = userStore.isLogin;
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
