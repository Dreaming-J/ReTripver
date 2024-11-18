<script setup>
import SignupForm from "@/components/sign/SignupForm.vue";
</script>

<template>
  <div class="container">
    <div class="text-xl">Sign Up to ReTripver</div>
    <div class="form-container">
      <SignupForm />
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
  margin-top: 20px;
  padding-bottom: 30px;
}
</style>
