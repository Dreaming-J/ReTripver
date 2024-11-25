import { defineStore } from "pinia";
import { ref, inject } from "vue";

export const useMakePlanStore = defineStore("plans", () => {
  const axios = inject("axios");

  const searchOption = ref({
    sidoCode: "",
    gugunCode: "",
    keyword: "",
    page: 1,
  });

  const searchList = ref([]);

  const searchAttractions = async () => {
    await axios
      .get("/attraction/search", { params: searchOption.value })
      .then((response) => {
        console.log("Success Load Attractions");
        searchList.value = response.data;
      })
      .catch((error) => {
        console.log(error);
      });
  };

  const selectList = ref([]);

  const addSelectItem = (item) => {
    // 이미 선택된 아이템인지 확인
    const existingItem = selectList.value.find(
      (selected) => selected.no === item.no
    );
    if (existingItem) return;

    // searchList에서 해당 아이템 찾아서 isSelected 상태 변경
    const searchItem = searchList.value.find((search) => search.no === item.no);
    if (searchItem) {
      searchItem.isSelected = true;
    }

    const lastOrder = selectList.value.length;

    selectList.value.push({
      ...item,
      courseOrder: lastOrder + 1,
    });
  };

  const removeSeletedItem = (no) => {
    const searchItem = searchList.value.find((search) => search.no === no);
    if (searchItem) {
      searchItem.isSelected = false;
    }

    const removedItem = selectList.value.find((item) => item.no === no);
    if (!removedItem) return;

    const removedOrder = removedItem.courseOrder;

    selectList.value = selectList.value.filter((item) => item.no !== no);

    selectList.value = selectList.value.map((item) => {
      if (item.courseOrder > removedOrder) {
        return {
          ...item,
          courseOrder: item.courseOrder - 1,
        };
      }
      return item;
    });
  };

  const updateOrder = () => {
    selectList.value = selectList.value.map((item, index) => ({
      ...item,
      courseOrder: index + 1, // 배열의 순서에 따라 courseOrder 재정렬
    }));
  };

  const optimizeCourses = ref({
    size: 0,
    optimizeCourses: [],
    fixFirst: false,
    fixLast: false,
  });
  const optimizeList = async (optimizeOptions) => {
    optimizeCourses.value.size = selectList.value.length;

    if (optimizeOptions.includes("fixFirst"))
      optimizeCourses.value.fixFirst = true;
    else optimizeCourses.value.fixFirst = false;

    if (optimizeOptions.includes("fixLast"))
      optimizeCourses.value.fixLast = true;
    else optimizeCourses.value.fixLast = false;

    try {
      const response = await axios.post(
        "plan/optimizeCourses",
        optimizeCourses.value
      );
      const newOrder = response.data;

      const tempList = JSON.parse(JSON.stringify(selectList.value));
      for (let idx = 0; idx < selectList.value.length; idx++) {
        selectList.value[idx] = tempList[newOrder[idx]];
      }
    } catch (error) {
      console.log(error);
    }
  };

  return {
    searchOption,
    searchList,
    searchAttractions,

    selectList,
    addSelectItem,
    removeSeletedItem,
    updateOrder,

    optimizeCourses,
    optimizeList,
  };
});
