<script setup>
import { InputText, Password, Button, Message, InputOtp } from "primevue";
import { ref } from "vue";

const modifyForm = ref({
  oldPassword: "",
  newPassword: "",
  passwordCheck: "",
})

const oldPasswordValidMessage = ref("");
const validOldPassword = (password) => {
  if (!password) return "현재 비밀번호를 작성해주세요.";
}
const oldPasswordValidBlur = () => {
  oldPasswordValidMessage.value = validOldPassword(modifyForm.value.oldPassword);
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
  passwordValidMessage.value = validatePassword(modifyForm.value.newPassword);
};

const passwordCheckValidMessage = ref("");
const validatePasswordCheck = (passwordCheck) => {
  if (modifyForm.value.newPassword !== passwordCheck)
    return "비밀번호가 일치하지 않습니다.";

  return "";
};
const passwordCheckValidBlur = () => {
  passwordCheckValidMessage.value = validatePasswordCheck(
    modifyForm.value.passwordCheck
  );
};

</script>

<template>
  <from class="form-container">
    <div class="input-group">
        <div class="text-xs input-label">Old Password</div>
        <div>
          <div>
            <Password 
              class="w-full"
              v-model="modifyForm.oldPassword"
              @blur="oldPasswordValidBlur"
              :feedback="false"
              toggleMask 
            />
          </div>
          <div class="text-valid">
            <Message
              v-if="oldPasswordValidMessage !== ''"
              class="text-sm"
              severity="error"
              variant="simple"
            >
              {{ oldPasswordValidMessage }}
            </Message>
          </div>
        </div>
      </div>

    <div class="input-group">
        <div class="text-xs input-label">New Password</div>
        <div>
          <div>
            <Password 
              class="w-full"
              v-model="modifyForm.newPassword"
              @blur="passwordValidBlur"
              :feedback="false"
              toggleMask 
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
            <Password
              class="w-full"
              v-model="modifyForm.passwordCheck"
              @blur="passwordCheckValidBlur"
              :feedback="false"
              toggleMask 
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

      <div class="input-group flex justify-content-end">
        <Button label="비밀번호 변경" />
      </div>
  </from>
</template>

<style scoped>
.text-valid {
  padding-left: 10px;
  padding-top: 10px;
}

:deep(.p-message .p-message-text) {
  font-size: 0.75rem;
}

.form-container {
  width: 100%;
  display: flex;
  flex-direction: column;
  padding: 0 50px;
}

.input-group {
  padding: 10px 0;
}

.input-label {
  padding-bottom: 10px;
}

:deep(.p-password .p-inputtext) {
  width: 100%;
}

</style>
