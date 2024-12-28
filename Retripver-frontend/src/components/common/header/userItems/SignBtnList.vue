<script setup>
import Button from "primevue/button";
</script>

<template>
  <div class="btn-list-sign">
    <div class="btn-sign">
      <RouterLink :to="{ name: 'signup' }" class="fa-sm">
        <Button
          class="btn-signup"
          label="Sign up"
          severity="secondary"
          variant="text"
          iconPos="left"
        />
      </RouterLink>
    </div>
    <div class="btn-sign">
      <RouterLink :to="{ name: 'login' }" class="fa-sm">
        <Button class="btn-login" label="Login" severity="warn" rounded />
      </RouterLink>
    </div>
  </div>
</template>

<style scoped>
.btn-list-sign {
  display: flex;
  align-items: center;
}

.btn-signup {
  padding-left: 10px;
}

.btn-login {
  width: 120px;
  height: 45px;
}
</style>
