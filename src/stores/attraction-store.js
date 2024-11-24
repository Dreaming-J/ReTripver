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

  const sidoCodes = [1, 2, 3, 4, 5, 6, 7, 8, 31, 32, 33, 34, 35, 36, 37, 38, 39]

  function makePlanList() {
    const planList = []

    for (let idx = 0; idx < sidoCodes.length; idx++) {
      planList.push({
        sidoCode: sidoCodes[idx],
        sidoName: '',
        plans: [
          { courses: [] }
        ]
      })
    }

    return planList
  }

  const myPlanList = ref(makePlanList())
  const getMyPlanList = async () => {
    try {
      for (let idx = 0; idx < sidoCodes.length; idx++) {
        let response = await axios.get(`/plan/mylist/${sidoCodes[idx]}`, { withCredentials: true })
        myPlanList.value[idx].plans = response.data
        
        response = await axios.get(`/attraction/sido/${sidoCodes[idx]}`)
        myPlanList.value[idx].sidoName = response.data
      }
    } catch (error) {
      console.log(error)
    }
  }

  const selectMyPlans = ref({
    sidoCode: 0,
    sidoName: '',
    plans: [
      { courses: [] }
    ]
  })
  const setMyPlan = (sidoCode) => {
    selectMyPlans.value = myPlanList.value.find(planList => planList.sidoCode === sidoCode)
  }
  const unSetMyPlan = () => {
    selectMyPlans.value = {
      sidoCode: 0,
      sidoName: '',
      plans: [
        { courses: [] }
      ]
    }
  }

  return {
    getSidoList,

    myPlanList,
    getMyPlanList,

    selectMyPlans,
    setMyPlan,
    unSetMyPlan
  }
})