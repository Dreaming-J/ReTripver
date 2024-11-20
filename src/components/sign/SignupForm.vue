<script setup>
import { InputText, Button, Message, InputOtp } from "primevue";
import { ref } from "vue";

const signupForm = ref({
  id: "",
  password: "",
  passwordCheck: "",
  name: "",
  email: "",
  emailVerify:""
});

const verifying = ref(false);

const verifyEmail = () => {
  verifying.value = true;
}

const idValidMessage = ref("");
const validateId = (id) => {
  if (!id) return "아이디를 작성해주세요.";
  if (id.length < 4 || id.length > 20) return "4~20자 사이여야 합니다.";
  if (!/^[a-zA-Z0-9_.-]+$/.test(id))
    return "영문, 숫자, 특수문자(_.-)만 사용 가능합니다.";

  return "";
};
const idValidBlur = () => {
  idValidMessage.value = validateId(signupForm.value.id);
};

const passwordValidMessage = ref("");
const validatePassword = (password) => {
  if (!password) return "비밀번호를 작성해주세요.";

  const hasLetter = /[a-zA-Z]/.test(password);
  const hasNumber = /[0-9]/.test(password);
  const validChars = /^[a-zA-Z0-9~!@#*?]+$/.test(password);

  if (!hasLetter || !hasNumber || !validChars) {
    return "영문, 숫자, 특수문자(~!@#*?)를 포함해야 합니다.";
  }

  return "";
};
const passwordValidBlur = () => {
  passwordValidMessage.value = validatePassword(signupForm.value.password);
};

const passwordCheckValidMessage = ref("");
const validatePasswordCheck = (passwordCheck) => {
  if (signupForm.value.password !== passwordCheck)
    return "비밀번호가 일치하지 않습니다.";

  return "";
};
const passwordCheckValidBlur = () => {
  passwordCheckValidMessage.value = validatePasswordCheck(
    signupForm.value.passwordCheck
  );
};

const nameValidMessage = ref("");
const validateName = (name) => {
  if (!name) return "이름을 작성해주세요.";

  if (!/^[a-zA-Z가-힣\s]+$/.test(name)) {
    return "한글, 영문, 공백만 사용 가능합니다.";
  }

  return "";
};
const nameValidBlur = () => {
  nameValidMessage.value = validateName(signupForm.value.name);
};

const emailValidMessage = ref("");
const validateEmail = (email) => {
  if (!email) return "이메일을 작성해주세요.";
  if (!email.includes("@")) return "올바른 이메일 형식이 아닙니다.";

  return "";
};
const emailValidBlur = () => {
  emailValidMessage.value = validateEmail(signupForm.value.email);
};

const handleSubmit = async () => {
  idValidBlur();
  passwordValidBlur();
  passwordCheckValidBlur();
  nameValidBlur();
  emailValidBlur();

  if (idValidMessage.value !== "") return;
  if (passwordValidMessage.value !== "") return;
  if (passwordCheckValidMessage.value !== "") return;
  if (nameValidMessage.value !== "") return;
  if (emailValidMessage.value !== "") return;

  console.log("회원가입 하러 감!!");
};
</script>

<template>
  <div>
    <form class="container" @submit.prevent="handleSubmit">
      <div class="input-group">
        <div class="text-xs input-label">ID</div>
        <div class="id-group">
          <div class="col-9 p-0 pr-1">
            <InputText
              class="w-full"
              type="text"
              v-model="signupForm.id"
              @blur="idValidBlur"
            />
          </div>
          <div class="col-3 p-0 pl-1">
            <Button
              class="w-full"
              label="확인"
              severity="warn"
              variant="outlined"
            />
          </div>
        </div>
        <div class="text-valid">
          <Message
            v-if="idValidMessage !== ''"
            class="text-sm"
            severity="error"
            variant="simple"
          >
            {{ idValidMessage }}
          </Message>
        </div>
      </div>

      <div class="input-group">
        <div class="text-xs input-label">Password</div>
        <div>
          <div>
            <InputText
              class="w-full"
              type="text"
              v-model="signupForm.password"
              @blur="passwordValidBlur"
            />
          </div>
          <div class="text-valid">
            <Message
              v-if="passwordValidMessage !== ''"
              class="text-sm"
              severity="error"
              variant="simple"
            >
              {{ passwordValidMessage }}
            </Message>
          </div>
        </div>
      </div>

      <div class="input-group">
        <div class="text-xs input-label">Check Password</div>
        <div>
          <div>
            <InputText
              class="w-full"
              type="text"
              v-model="signupForm.passwordCheck"
              @blur="passwordCheckValidBlur"
            />
          </div>
          <div class="text-valid">
            <Message
              v-if="passwordCheckValidMessage !== ''"
              class="text-sm"
              severity="error"
              variant="simple"
            >
              {{ passwordCheckValidMessage }}
            </Message>
          </div>
        </div>
      </div>

      <div class="input-group">
        <div class="text-xs input-label">Name</div>
        <div>
          <div>
            <InputText
              class="w-full"
              type="text"
              v-model="signupForm.name"
              @blur="nameValidBlur"
            />
          </div>
          <div class="text-valid">
            <Message
              v-if="nameValidMessage !== ''"
              class="text-sm"
              severity="error"
              variant="simple"
            >
              {{ nameValidMessage }}
            </Message>
          </div>
        </div>
      </div>

      <div class="input-group">
        <div class="text-xs input-label">Email</div>
        <div class="email-group">
          <div class="col-9 p-0 pr-1">
            <InputText
              class="w-full"
              type="text"
              v-model="signupForm.email"
              @blur="emailValidBlur"
            />
          </div>
          <div class="col-3 p-0 pl-1">
            <Button
              class="w-full"
              label="인증"
              severity="warn"
              variant="outlined"
              @click="verifyEmail"
            />
          </div>
        </div>

        <div v-if="verifying" class="email-group input-code">
          <div class="col-9 p-0 pr-1">
            <InputOtp v-model="signupForm.emailVerify" :length="6" style="gap: 5" integerOnly />
          </div>
          <div class="col-3 p-0 pl-1">
            <Button
              class="w-full"
              label="확인"
              severity="warn"
              variant="outlined"
            />
          </div>
        </div>

        <div class="text-valid">
          <Message
            v-if="emailValidMessage !== ''"
            class="text-sm"
            severity="error"
            variant="simple"
          >
            {{ emailValidMessage }}
          </Message>
        </div>
      </div>

      <div class="btn-signup">
        <Button type="submit" class="w-full" label="Sign Up" severity="warn" />
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
.text-valid {
  padding-left: 10px;
  padding-top: 10px;
}

:deep(.p-message .p-message-text) {
  font-size: 0.75rem;
}

.id-group {
  display: flex;
  width: 100%;
}

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

.input-code {
  padding-top : 20px;
}

:deep(.p-inputotp) {
  justify-content: center;
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
