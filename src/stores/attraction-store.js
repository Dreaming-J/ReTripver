import { ref, inject } from "vue"
import { defineStore } from "pinia"

export const useAttractionStore = defineStore("attractionStore", () => {
  const axios = inject('axios')

  const getSidoList = async () => {
    const res = await axios.get('/attraction/sidos')
      .then( (response) => {
        return response.data
      })
      .catch( (error) => {
        console.log(error)
      })

    return res
  }

  return {
    getSidoList,
  }
})