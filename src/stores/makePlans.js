import { defineStore } from "pinia";
import { ref } from "vue";

export const useMakePlansStore = defineStore("plans", () => {
  const searchList = ref([
    {
      no: 3837,
      title: "경복궁",
      img: "http://tong.visitkorea.or.kr/cms/resource/33/2678633_image2_1.jpg",
      lat: 37.5788222356,
      lng: 126.9769930325,
      addr: "서울특별시 종로구",
      isSelected: false,
    },
    {
      no: 3931,
      title: "남산서울타워",
      img: "",
      lat: 37.5511089858,
      lng: 126.9878983791,
      addr: "서울특별시 용산구",
      isSelected: false,
    },
    {
      no: 4486,
      title: "창덕궁과 후원 [유네스코 세계유산]",
      img: "http://tong.visitkorea.or.kr/cms/resource/03/3092503_image2_1.jpg",
      lat: 37.5809583673,
      lng: 126.9919888278,
      addr: "서울특별시 종로구",
      isSelected: false,
    },
  ]);

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

  return {
    searchList,
    selectList,
    addSelectItem,
    removeSeletedItem,
    updateOrder,
  };
});
