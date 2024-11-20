<template>
  <div class="flex m-10">
    <VueDraggableNext
      class="dragArea list-group w-full"
      v-model="store.selectList"
      @change="log"
    >
      <div
        class="list-group-item bg-gray-300 m-1 p-3 rounded-md text-center"
        v-for="element in store.selectList"
        :key="element.no"
      >
        {{ element.no }}
      </div>
    </VueDraggableNext>
    {{ store.selectList }}
  </div>
</template>
<script setup>
import { defineComponent } from "vue";
import { VueDraggableNext } from "vue-draggable-next";
import { useMakePlansStore } from "@/stores/makePlans";

const store = useMakePlansStore();
const log = (event) => {
  if (event.moved) {
    // moved 데이터를 활용하여 순서 업데이트
    const { newIndex, oldIndex } = event.moved;
    console.log(`Moved from ${oldIndex} to ${newIndex}`);

    // courseOrder 재정렬
    store.selectList = store.selectList.map((item, index) => ({
      ...item,
      courseOrder: index + 1,
    }));
  }
};

// export default defineComponent({
//   components: {
//     draggable: VueDraggableNext,
//   },
//   setup() {
//     const store = useMakePlansStore();
//     const log = (event) => {
//       if (event.moved) {
//         // moved 데이터를 활용하여 순서 업데이트
//         const { newIndex, oldIndex } = event.moved;
//         console.log(`Moved from ${oldIndex} to ${newIndex}`);

//         // courseOrder 재정렬
//         store.selectList = store.selectList.map((item, index) => ({
//           ...item,
//           courseOrder: index + 1,
//         }));
//       }
//     };

//     return {
//       store,
//       log,
//     };
//   },
//   data() {
//     return {
//       enabled: true,
//       dragging: false,
//     };
//   },
//   methods: {
//     onDragEnd(event) {
//       console.log(event);
//     },
//   },
// });
</script>
