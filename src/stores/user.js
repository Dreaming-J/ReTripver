import { ref } from "vue"
import axios from "axios"
import { useRouter } from "vue-router"
import { defineStore } from "pinia"

import { login } from "@/api/user"
import { httpStatusCode } from "@/util/http-status"

export const useUserStore = defineStore("userStore", () => {
  const router = useRouter()

  const isLogin = ref(false)
  const isLoginError = ref(false)
  const userInfo = ref(null)
  const isValidToken = ref(false)

  const userLogin = async (loginUser) => {
    await login(
      loginUser,
      (response) => {
        if (response.status === httpStatusCode.OK) {
          console.log("로그인 성공!!!!")
          let { data } = response
          userInfo.value = {
            id: data.id,
            profileImg: data.profileImg
          }
          isLogin.value = true
          isLoginError.value = false
          isValidToken.value = true
          axios.defaults.headers.common['Authorization'] = response.headers.authorization
        }
      },
      (error) => {
        console.log("로그인 실패!!!!")
        isLogin.value = false
        isLoginError.value = true
        isValidToken.value = false
        console.error(error)
      }
    )
  }

  return {
    isLogin,
    isLoginError,
    userInfo,
    isValidToken,
    userLogin,
  }
})