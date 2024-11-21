import { ref, inject } from "vue"
import { defineStore } from "pinia"

export const useAttractionStore = defineStore("attractionStore", () => {
  const axios = inject('axios')

  const sidoList = ref([])
  const getSidoList = async () => {
    await axios.get('/attraction/sidos')
    .then( (response) => {
        let { data } = response

        sidoList.value = data
    })
    .catch( (error) => {
      sidoList.value = []
      console.log(error)
    })
  }

  return {
    sidoList,
    getSidoList,
  }
})