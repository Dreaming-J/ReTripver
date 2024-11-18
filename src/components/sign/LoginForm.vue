<script setup>
import { InputText, Button, Message } from "primevue"
import { ref, computed } from "vue"
import { storeToRefs } from "pinia"
import { useRouter } from "vue-router"
import { useUserStore } from "@/stores/user"

const router = useRouter()
const userStore = useUserStore()
const { isLogin, isLoginError } = storeToRefs(userStore)
const { userLogin } = userStore

const loginForm = ref({
  id: "",
  password: "",
})

const validMessage = computed(() => {
  if (!loginForm.value.id) return "아이디를 작성해주세요."
  if (!loginForm.value.password) return "비밀번호를 작성해주세요"
  return ""
})

const validCheck = computed(() => {
  return !loginForm.value.id || !loginForm.value.password;
})

const login = async () => {
  await userLogin(loginForm.value)
  console.log("isLogin: " + isLogin.value)
  if (isLogin.value) {
    router.replace("/")
  }
}
</script>

<template>
  <div>
    <Form class="container">
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
          <Message
            class="text-valid"
            v-if="validCheck"
            severity="error"
            variant="simple"
          >
            {{ validMessage }}
          </Message>
        </div>
        <div>
          <Button class="w-full" label="Login" severity="warn" @click="login" />
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
    </Form>
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

.message {
  padding-bottom: 10px;
  padding-left: 10px;
}

.signup-group {
  display: flex;
  justify-content: space-between;
  padding: 40px 0;
}
</style>
