<script setup>
import StatusInfo from "@/components/user/status/StatusInfo.vue";
import StatusMap from "@/components/user/status/StatusMap.vue";
import StatusPlan from "@/components/user/status/StatusPlan.vue";

import { ref, onMounted } from 'vue'
import { useUserStore } from "@/stores/user-store"

const userStore = useUserStore()
const { getUserStatus } = userStore

const userStatus = ref({
  userInfo: {
     id: '',
     profileImg: '',
     profileDesc: '',
     exp: 0,
     achievementTitle: '',
     tierInfo: {
      tierName: '',
      tierImg: '',
      nextTierExp: 0,
     }
  },
  follow: {
    followerCount: 0,
    followingCount: 0,
  },
  questRate: {
	  successQuestRate: 0,
	  successQuestCount: 0,
	  totalQuestCount: 0,
  }
})
onMounted(async () => {
  userStatus.value = await getUserStatus()
})

const isPlansVisible = ref(false);
const selectedSidoCode = ref(null);

const selectSido = (sidoCode) => {
  isPlansVisible.value = true;
  selectedSidoCode.value = sidoCode;
};

const closePlansPanel = () => {
  isPlansVisible.value = false;
};
</script>

<template>
  <div class="status-container">
    <div class="info-container">
      <StatusInfo :user-status="userStatus" />
    </div>
    <div class="map-container">
      <StatusMap @select-sido-code="selectSido" />
    </div>
    <div class="plans-container">
      <div
        class="plan-panel shadow-3"
        :class="{ 'plan-panel-hide': !isPlansVisible }"
      >
        <StatusPlan
          @close-plans-panel="closePlansPanel"
          :sidoCode="selectedSidoCode"
        />
      </div>
    </div>
  </div>
</template>

<style scoped>
.status-container {
  display: flex;
  flex-wrap: nowrap;
  width: 100%;
  height: 100%;
  position: relative;
}

.info-container {
  width: 400px;
  min-width: 400px;
  /* border: 1px solid lightgray; */
  /* border-right: 1px solid lightgray; */
  /* border-radius: 10px; */
  /* box-shadow: 4px 0 8px -4px rgba(0, 0, 0, 0.2); 오른쪽 선에만 그림자 */
}

.map-container {
  width: 100%;
}

.plans-container {
  position: relative;
  width: 600px;
  min-width: 400px;
  height: 100%;
  overflow: hidden;
  /* border: 1px solid purple; */
}

.plan-panel {
  width: 100%;
  height: 100%;
  border-radius: 15px;
  position: absolute;
  right: 0;
  top: 0;
  background-color: white;
  transition: 0.5s ease;
}

.plan-panel-hide {
  transform: translate(400px, 0);

  /* background-color: pink; */
}
</style>
