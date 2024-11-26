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

  const getPlanInfoById = async (planId) => {
    try {
      const response = await axios.get(`/plan/copy/${planId}`);
      return response.data
    } catch (error) {
      console.log(error);
    }
  }

  const uploadImage = async (formData) => {
    try {
      const response = await axios.post('/plan/upload', formData, {
        headers: { 'Content-Type': 'multipart/form-data' },
        withCredentials: true
      })
      return response.data
    } catch (error) {
      console.error('업로드 실패:', error)
    }
  }
  const compareImage = async (missionImg, userImg) => {
    try {
      const response = await axios.get('/plan/comparision', {params: {url1: missionImg, url2: userImg}})
      return response.data
    } catch (error) {
      console.error(error)
    }
  }

  const gainExp = ref(0)
  const questClear = async (planId) => {
    try {
      const response = await axios.patch('plan/quest-clear', {planId: planId, gainExp: gainExp.value}, { withCredentials: true })
      console.log(response.data)
    } catch (error) {
      console.error(error)
    }
  }
 
  return {
    getRankList,
    getUserRankList,

    planListInSido,
    getPlanListInSido,

    createReview,

    getPlanInfoById,

    uploadImage,
    compareImage,

    gainExp,
    questClear
  };
});
