import { ref, inject } from "vue"
import { defineStore } from "pinia"

export const usePlanStore = defineStore("planStore", () => {
  const axios = inject('axios')

  const getRankList = async (page) => {
    try {
      const response = await axios.get('/plan/rank', {params: {page: page}})
      return response.data
    } catch (error) {
      console.log(error)
    }
  }

  return {
    getRankList,
  }
})