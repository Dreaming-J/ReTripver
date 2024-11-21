import { ref, inject } from "vue"
import { defineStore } from "pinia"

export const useUserStore = defineStore("userStore", () => {
  const axios = inject('axios')

  function UserInfo(id='', profileImg='') {
    this.id = id
    this.profileImg = profileImg
  }

  const userInfo = ref(new UserInfo())

  const isLogin = ref(false)
  const userLogin = async (loginUser) => {
    await axios.post('/auth/login', loginUser, { withCredentials: true })
    .then( (response) => {
        console.log("Success Login")
        axios.defaults.headers.common['Authorization'] = response.headers.authorization

        let { data } = response
        userInfo.value = new UserInfo(data.id, data.profileImg)
        isLogin.value = true
    })
    .catch( (error) => {
        console.log(error)
        isLogin.value = false
    })
  }
  const userLogout = async () => {
    await axios.post('/auth/logout', { withCredentials: true })
    .then( (response) => {
        console.log("Success Logout")

        axios.defaults.headers.common['Authorization'] = ''
        document.cookie = 'refresh-token=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;'

        userInfo.value = new UserInfo()
        isLogin.value = false
    })
    .catch( (error) => {
        console.log(error)
        isLogin.value = false
    })
  }

  return {
    userInfo,

    isLogin,
    userLogin,
    userLogout
  }
})