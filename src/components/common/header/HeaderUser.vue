<script setup>
  import ProfileBtnList from "@/components/common/header/userItems/ProfileBtnList.vue";
  import SignBtnList from "@/components/common/header/userItems/SignBtnList.vue";
  import { useUserStore } from "@/stores/user-store";
  import { storeToRefs } from "pinia";
  import { onMounted } from 'vue'

  const userStore = useUserStore();
  const { isLogin } = storeToRefs(userStore);
  const { getUserInfo } = userStore

  onMounted(async () => {
    await getUserInfo()
  })
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
