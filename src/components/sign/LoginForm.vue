<script setup>
import { InputText, Button } from "primevue";
</script>

<template>
  <div>
    <form class="container">
      <div class="input-group">
        <div class="text-xs input-label">ID</div>
        <div><InputText class="w-full" type="text" /></div>
      </div>

      <div class="input-group">
        <div class="text-xs input-label">Password</div>
        <div>
          <InputText class="w-full" type="text" />
        </div>
      </div>

      <div class="link-forgot">
        <RouterLink :to="{ name: 'main' }" class="text-xs link-text">
          Forgot Password?
        </RouterLink>
      </div>

      <div>
        <Button class="w-full" label="Login" severity="warn" />
      </div>

      <div class="signup-group">
        <div class="text-xs">Don't have an account?</div>
        <div>
          <RouterLink :to="{ name: 'signup' }" class="text-xs link-text">
            Sign up
          </RouterLink>
        </div>
      </div>
    </form>
  </div>
</template>

<style scoped>
.link-text {
  text-decoration: none;
  color: darkblue;
}

.container {
  width: 100%;
}

.input-group {
  padding: 20px 0;
}

.input-label {
  padding-bottom: 10px;
}

.link-forgot {
  display: flex;
  justify-content: flex-end;
  padding-bottom: 40px;
}

.signup-group {
  display: flex;
  justify-content: space-between;
  padding: 40px 0;
}
</style>
