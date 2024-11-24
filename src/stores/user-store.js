import { ref, inject } from "vue"
import { defineStore } from "pinia"
import { useRouter } from "vue-router";

export const useUserStore = defineStore("userStore", () => {
  const axios = inject('axios')
  const router = useRouter();

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
        
        router.replace({name: 'main'})
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
  const getUserInfo = async() => {
    if (isLogin.value)
      return

    try {
      const response = await axios.get('/auth/info', { withCredentials: true})
      userInfo.value = response.data
      isLogin.value = true
    } catch (error) {
      userInfo.value = new UserInfo()
      isLogin.value = false
    }
  }

  const userSignup = async (signupForm) => {
    try {
      await axios.post(`/auth/signup`, signupForm)
      
      router.replace({name : 'login'})
    } catch (error) {
      console.log(error)
      alert(error)
    }
  }
  const existId = async (id) => {
    try {
      const response = await axios.get(`/auth/exist/id/${id}`)
      return !response.data
    } catch (error) {
      console.log(error)
    }
  }
  const existEmail = async (email) => {
    try {
      const response = await axios.get(`/auth/exist/email/${email}`)
      return !response.data
    } catch (error) {
      console.log(error)
    }
  }
  const authEmail = async (email) => {
    try {
      const response = await axios.get(`/auth/email/${email}`)
      return response.data
    } catch (error) {
      console.log(error)
    }
  }
  const authVerify = async (email, code) => {
    try {
      const response = await axios.post(`/auth/email/verify`, {email: email, code: code})
      return response.data
    } catch (error) {
      console.log(error)
    }
  }

  const getUserStatus = async () => {
    try {
      const response = await axios.get('/user/status/info', { withCredentials: true })
      return response.data
    } catch (error) {
      console.log(error)
    }
  }

  return {
    userInfo,

    isLogin,
    userLogin,
    userLogout,
    getUserInfo,

    userSignup,
    existId,
    existEmail,
    authEmail,
    authVerify,

    getUserStatus
  }
})