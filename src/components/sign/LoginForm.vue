<script setup>
import { InputText, Button, Message } from "primevue";
import { ref } from "vue";
import { storeToRefs } from "pinia";
import { useRouter } from "vue-router";
import { useUserStore } from "@/stores/user-store";

const router = useRouter();
const userStore = useUserStore();
const { isLogin } = storeToRefs(userStore);
const { userLogin } = userStore;

const loginForm = ref({
  id: "",
  password: "",
});

const validMessage = ref("");

const handleSubmit = async () => {
  if (!loginForm.value.id) {
    validMessage.value = "아이디를 작성해주세요.";
    return;
  }

  if (!loginForm.value.password) {
    validMessage.value = "비밀번호를 작성해주세요.";
    return;
  }

  validMessage.value = "";

  await userLogin(loginForm.value);
  console.log("isLogin: " + isLogin.value);
  if (isLogin.value) {
    router.replace("/");
  }
};
</script>

<template>
  <div>
    <form class="container" @submit.prevent="handleSubmit">
      <div class="input-group">
        <div class="text-xs input-label">ID</div>
        <div>
          <InputText v-model="loginForm.id" class="w-full" type="text" />
        </div>
      </div>

      <div class="input-group">
        <div class="text-xs input-label">Password</div>
        <div>
          <InputText v-model="loginForm.password" class="w-full" type="text" />
        </div>
      </div>

      <div class="link-forgot">
        <RouterLink :to="{ name: 'main' }" class="text-xs link-text">
          Forgot Password?
        </RouterLink>
      </div>

      <div>
        <div class="message">
          <Message class="text-sm" severity="error" variant="simple">
            {{ validMessage }}
          </Message>
        </div>
        <div>
          <Button type="submit" class="w-full" label="Login" severity="warn" />
        </div>
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
  padding-bottom: 30px;
}

.message {
  padding-bottom: 20px;
  padding-left: 10px;
}

:deep(.p-message .p-message-text) {
  font-size: 0.75rem;
}

.signup-group {
  display: flex;
  justify-content: space-between;
  padding: 40px 0;
}
</style>
