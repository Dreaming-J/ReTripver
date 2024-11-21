import { ref, inject } from "vue"
import { defineStore } from "pinia"

export const usePlanStore = defineStore("planStore", () => {
  const axios = inject('axios')

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
    rankList,
    getRankList,
  }
})