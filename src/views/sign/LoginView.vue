<script setup>
import LoginForm from "@/components/sign/LoginForm.vue";
</script>

<template>
  <div class="container">
    <div class="text-xl">Login to ReTripver</div>
    <div class="form-container">
      <LoginForm />
    </div>
  </div>
</template>

<style scoped>
.container {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding-top: 30px;
}

.form-container {
  width: 400px;
  margin-top: 50px;
  padding-bottom: 30px;
}
</style>
