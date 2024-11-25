import { ref, inject } from "vue";
import { defineStore } from "pinia";

export const usePlanStore = defineStore("planStore", () => {
  const axios = inject("axios");

  const getRankList = async (page) => {
    try {
      // const response = await axios.get("/plan/rank", {
      //   params: { page: page },
      // });
      const response = await axios.get("/user/rank");
      return response.data;
    } catch (error) {
      console.log(error);
    }
  };

  const getUserRankList = async () => {
    try {
      const response = await axios.get("/user/rank");
      return response.data;
    } catch (error) {
      console.log(error);
    }
  };

  const planListInSido = ref([]);
  const getPlanListInSido = async (sidoCode) => {
    try {
      const response = await axios.get(`/plan/search/${sidoCode}`);
      planListInSido.value = response.data;
    } catch (error) {
      console.log(error);
    }
  };

  const createReview = async (plan) => {
    try {
      const response = await axios.post('/plan/review', plan)
      return response.data
    } catch (error) {
      console.log(error)
    }
  }

  // const planInfoById = ref();
  const getPlanInfoById = async (planId) => {
    try {
      const response = await axios.get(`/plan/copy/${planId}`);

      return response.data
    } catch (error) {
      console.log(error);
    }
  }
 
  return {
    getRankList,
    getUserRankList,

    planListInSido,
    getPlanListInSido,

    createReview,

    getPlanInfoById
  };
});
