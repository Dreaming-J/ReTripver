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

      <div class="input-group">
        <div class="text-xs input-label">Check Password</div>
        <div>
          <InputText class="w-full" type="text" />
        </div>
      </div>

      <div class="input-group">
        <div class="text-xs input-label">Name</div>
        <div>
          <InputText class="w-full" type="text" />
        </div>
      </div>

      <div class="input-group">
        <div class="text-xs input-label">Email</div>
        <div class="email-group">
          <div class="col-9 p-0 pr-1">
            <InputText class="w-full" type="text" />
          </div>
          <div class="col-3 p-0 pl-1">
            <Button
              class="w-full"
              label="인증"
              severity="warn"
              variant="outlined"
            />
          </div>
        </div>
      </div>

      <div class="btn-signup">
        <Button class="w-full" label="Sign Up" severity="warn" />
      </div>

      <div class="login-group">
        <div class="text-xs">Already have an account?</div>
        <div>
          <RouterLink :to="{ name: 'login' }" class="text-xs link-text">
            Login
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
  padding: 10px 0;
}

.input-label {
  padding-bottom: 10px;
}

.email-group {
  display: flex;
  width: 100%;
}

.btn-signup {
  padding-top: 20px;
}

.login-group {
  display: flex;
  justify-content: space-between;
  padding: 40px 0;
}
</style>
