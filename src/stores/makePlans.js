import { defineStore } from "pinia";
import { ref } from "vue";

export const useMakePlansStore = defineStore("plans", () => {
  let courseOrder = 1;

  const searchList = ref([])

  const selectList = ref([
    {
      no: 3837,
      title: "경복궁",
      lat: 37.5788222356,
      lng: 126.9769930325,
      courseOrder: courseOrder++,
    },
    {
      no: 4486,
      title: "남산서울타워",
      lat: 37.5511089858,
      lng: 126.9878983791,
      courseOrder: courseOrder++,
    },
    {
      no: 3931,
      title: "창덕궁과 후원 [유네스코 세계유산]",
      lat: 37.5809583673,
      lng: 126.9919888278,
      courseOrder: courseOrder++,
    },
  ]);

  const addItem = () => {
    const lastOrder = selectList.value.length;

    selectList.value.push({
      no: 3877,
      title: "광화문광장",
      lat: 37.5727035021,
      lng: 126.9769709861,
      courseOrder: lastOrder + 1,
    });
  };

  const removeItem = (no) => {
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

  return {
    selectList,
    addItem,
    removeItem,
    updateOrder,
  };
});
