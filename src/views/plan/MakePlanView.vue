<script setup>
import SelectedList from "@/components/plan/make/select/SelectedList.vue";
import SearchList from "@/components/plan/make/search/SearchList.vue";
import MapContent from "@/components/plan/make/map/MapContent.vue";

import { Button, Dialog, InputText, Select, Message, ConfirmDialog } from "primevue";
import { useConfirm } from "primevue/useconfirm";
import { useMakePlanStore } from "@/stores/makePlan-store";
import { ref } from "vue";
import { useRouter, onBeforeRouteLeave } from "vue-router";
import { storeToRefs } from "pinia";


const confirm = useConfirm();

const router = useRouter();
const store = useMakePlanStore();
const { makeNewPlan } = store;
const { newPlan } = storeToRefs(store)

const writePlanInfo = ref(true);
const writePlanMessage = ref("");
const wrietePlanTitleClose = () => {
  if (!newPlan.value.title || newPlan.value.title === "") {
    writePlanMessage.value = "여행 제목을 입력해주세요.";

    writePlanInfo.value = true;
  } else {
    writePlanInfo.value = false;
  }
}

const isSearchVisible = ref(true);
const routeType = ref(false);

const isNotSelected = ref(false);
const isMakePlanvVisible = ref(false);

const changeRouteType = (changedRouteType) => {
  routeType.value = changedRouteType;
};

const makePlan = (selectList) => {
  if (selectList.length <= 0) {
    isNotSelected.value = true;
  } else {
    isMakePlanvVisible.value = true;
  }
};

const goQuestPage = async () => {
  const courses = store.selectList.map(item => ({
    attractionNo: item.no,
  courseOrder: item.courseOrder,
  missionImg: item.firstImage1
  }));

  newPlan.value.courses = courses;

  await makeNewPlan();
};

onBeforeRouteLeave((to, from, next) => {
  const excludePaths = [{name: "make-mission"}];

  const isExcluded = excludePaths.some((path) => path.name === to.name);

  if (isExcluded) {
    next();
    return;
  }

  return new Promise((resolve) => {
    confirm.require({
        message: '변경사항이 저장되지 않습니다.',
        header: ' ',
        icon: 'pi pi-exclamation-triangle',
        rejectProps: {
            label: '취소',
            severity: 'secondary',
            outlined: true
        },
        acceptProps: {
            label: '확인'
        },
        accept: () => {
            store.resetSelectList();

            next();
            resolve();
        },
        reject: () => {
            next(false);
            resolve();
        }
    });
  })

});
</script>

<template>
  <div class="container">
    <div class="content-wrapper">
      <div class="seletced-list-container">
        <div class="seletced-list">
          <SelectedList
            @change-route-type="changeRouteType"
            @make-plan="makePlan"
          />
        </div>

        <div
          :class="[
            'search-list shadow-2',
            { 'search-list-hide': !isSearchVisible },
          ]"
        >
          <div>
            <SearchList />
          </div>

          <div class="btn-search-toggle">
            <Button
              class="btn-toggle"
              :icon="
                isSearchVisible ? 'pi pi-chevron-left' : 'pi pi-chevron-right'
              "
              severity="secondary"
              @click="isSearchVisible = !isSearchVisible"
            />
          </div>
        </div>
      </div>

      <div class="map-content">
        <MapContent :route-type="routeType" />
      </div>
    </div>

    <Dialog
      v-model:visible="writePlanInfo"
      modal
      header=" "
      :style="{ width: '25rem' }"
      :closable="false"
    >
      <div class="px-3" style=" height: 200px">
        <div class="input-group">
          <div class="input-label">여행 제목</div>
          <div>
            <InputText v-model="newPlan.title" class="w-full" type="text" />
          </div>
          <div class="text-valid">
          <Message
            v-if="writePlanMessage !== ''"
            class="text-sm"
            severity="error"
            variant="simple"
          >
            {{ writePlanMessage }}
          </Message>
        </div>
        </div>
        <div class="input-group pt-4">
          <Button
            class="w-full"
            severity="warn"
            type="button"
            label="여행 만들기"
            @click="wrietePlanTitleClose"
          ></Button>
        </div>
      </div>
    </Dialog>

    <Dialog
      v-model:visible="isNotSelected"
      modal
      header=" "
      :style="{ width: '25rem' }"
    >
      <span class="text-lg dark:text-surface-400 block mb-6 text-center"
        >여행지를 추가해주세요.</span
      >
      <div class="flex justify-end gap-2">
        <Button
          class="w-full"
          type="button"
          severity="warn"
          label="확인"
          @click="isNotSelected = false"
        ></Button>
      </div>
    </Dialog>

    <Dialog
      v-model:visible="isMakePlanvVisible"
      modal
      header="여행을 만들겠습니까?"
      :style="{ width: '25rem' }"
    >
      <span class="text-lg dark:text-surface-400 block mt-6 mb-6"
        >확인 후, 미션 사진이 생성됩니다!</span
      >
      <div class="flex items-center text-sm mb-5">
        * 만드신 여행 계획은 수정이 불가능합니다.
      </div>
      <div class="flex justify-end gap-2">
        <Button
          class="col-6"
          type="button"
          label="취소"
          severity="secondary"
          @click="isMakePlanvVisible = false"
        ></Button>
        <Button
          class="col-6"
          type="button"
          severity="warn"
          label="확인"
          @click="goQuestPage"
        ></Button>
      </div>
    </Dialog>

    <ConfirmDialog />
  </div>
</template>

<style scoped>
.container {
  display: flex;
  height: calc(100vh - 100px);
  width: 100%;
}

.content-wrapper {
  position: relative;
  display: flex;
  height: 100%;
  width: 100%;
}

.seletced-list-container {
  display: flex;
  width: 400px;
  height: 100%;
  position: relative;
  flex-shrink: 0;
}

.seletced-list {
  width: 400px;
  height: 100%;
  position: relative;
  z-index: 3;
  background-color: white;
}

.search-list {
  width: 400px;
  border-radius: 15px;
  background-color: white;
  /* border: 1px solid lightgray; */
  height: 100%;
  left: 410px;
  position: absolute;
  transition: 0.3s ease;
  z-index: 2;
}

.search-list-hide {
  transform: translate(-410px, 0);
}

.btn-search-toggle {
  position: absolute;
  top: 40%;
  left: 100%;
}

.btn-toggle {
  height: 50px;
  width: 25px;
  border-radius: 0px;
  border-top-right-radius: 10px;
  border-bottom-right-radius: 10px;
}

.map-content {
  /* width: calc(100vw - 510px); */
  flex: 1;
  height: 100%;
  position: relative;
  z-index: 1;
  min-width: 0;
}

.input-group {
  padding: 10px 0;
}

.input-label {
  padding-bottom: 10px;
}

.text-valid {
  padding-top: 10px;
}
</style>
