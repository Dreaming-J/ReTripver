<script setup>
import HeaderNav from "@/components/common/header/HeaderNav.vue";
import HeaderUser from "@/components/common/header/HeaderUser.vue";
</script>

<template>
  <div class="header-continer">
    <div>
      <HeaderNav />
    </div>
    <div class="user-itmes">
      <HeaderUser />
    </div>
  </div>
</template>

<style scoped>
.header-continer {
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 20px;
}
</style>
