import { ref, inject } from "vue"
import { defineStore } from "pinia"

export const useUserStore = defineStore("userStore", () => {
  const axios = inject('axios')

  const isLogin = ref(false)
  const userInfo = ref({
    id:"",
    profileImg:""
  })
  const userLogin = async (loginUser) => {
    await axios.post('/auth/login', loginUser, { withCredentials: true })
    .then( (response) => {
        axios.defaults.headers.common['Authorization'] = response.headers.authorization

        let { data } = response
        userInfo.value.id = data.id
        userInfo.value.profileImg = data.profileImg
        isLogin.value = true
    })
    .catch( (error) => {
        console.log(error)
        isLogin.value = false
    })
  }

  const myPage = async (myPage) => {
    await axios.mypage(

    )
  }

  const rankList = ref([])
  const getRankList = async (page) => {
    await axios.get('/plan/rank', {params: {page: page}})
    .then( (response) => {
        let { data } = response

        rankList.value = data
    })
    .catch( (error) => {
      rankList.value = []
      console.log(error)
    })
  }

  return {
    isLogin,
    userInfo,
    userLogin,
    myPage,

    rankList,
    getRankList
  }
})