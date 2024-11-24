<script setup>
import SidePanel from "@/components/user/status/SidePanel.vue";
import {computed} from 'vue'

const props = defineProps({
  userStatus: {
    type: Object,
    required: true
  }
})

const expPercantage = computed(() => {
  const percentage = (props.userStatus.userInfo.exp / props.userStatus.userInfo.tierInfo.nextTierExp * 100).toFixed(1)
  return {
    '--target-width': `${percentage}%`
  }
})
</script>

<template>
  <div>
    <div class="profile-container">
      <div class="profile-header">
        <div class="profile-image">
          <img :src="userStatus.userInfo.profileImg" />
        </div>

        <div class="profile-info">
          <div class="ahievement-title">{{ userStatus.userInfo.achievementTitle }}</div>
          <div class="profile-id">
            {{ userStatus.userInfo.id }}
            <span class="edit-icon">
              <font-awesome-icon
                :icon="['far', 'pen-to-square']"
                class="text-xl"
              />
              <!-- <button class="text-xs">follow</button> -->
            </span>
          </div>
        </div>
      </div>

      <div class="follow-info">
        <div class="follow-num text-center col-6">
          팔로워 <span class="pl-2">{{ userStatus.follow.followerCount }}</span>
        </div>
        <div class="follow-num text-center col-6">
          팔로우 <span class="pl-2">{{ userStatus.follow.followingCount }}</span>
        </div>
      </div>

      <div class="bio-container">
        {{ userStatus.userInfo.profileDesc }}
      </div>

      <!-- <div class="btn-container">
        <button class="btn-follow">+ 팔로우</button>
      </div> -->

      <div class="exp-container">
        <div class="tier-img">
          <img src="@/assets/img/medal.png" />
        </div>
        <div class="exp-info">
          <div
            class="exp-text text-sm"
          >
            EXP. {{userStatus.userInfo.exp}}/{{userStatus.userInfo.tierInfo.nextTierExp}}[{{ (userStatus.userInfo.exp / userStatus.userInfo.tierInfo.nextTierExp * 100).toFixed(1) }}%]
          </div>
          <div class="exp-bar">
            <div class="exp-background"></div>
            <div class="exp-fill":style="expPercantage"></div>
          </div>
        </div>
      </div>

      <div class="quest-container">
        <div class="quest-text">Quest Succes</div>
        <div class="quest-rate">
          <SidePanel
            :value="userStatus.questRate.successQuestRate"
            :size="120"
            :thickness="10"
            :fill="{ gradient: ['#E0E0E0', '#355C7D', '#1F3649'] }"
            line-cap="round"
          >
            <template #default="{ progress }">
              {{ Math.round(progress * 100) }}%
            </template>
          </SidePanel>
        </div>
      </div>

      <!-- <div class="progress-section">
        <div class="exp-label">
          <svg class="badge-icon" viewBox="0 0 24 24">
            <circle
              cx="12"
              cy="12"
              r="10"
              fill="none"
              stroke="currentColor"
              stroke-width="2"
            />
            <text x="12" y="16" text-anchor="middle" font-size="12">E</text>
          </svg>
          EXP
        </div>
        <div class="exp-bar"></div>

        <div class="mission-label">Mission Success</div>
        <div class="mission-bar"></div>
      </div> -->
    </div>
  </div>
</template>

<style scoped>
.profile-container {
  width: 100%;
  padding: 20px;
}

.profile-header {
  height: 110px;
  width: 100%;
  display: flex;
}

.profile-image {
  width: 110px;
  height: 100%;
}

.profile-image img {
  width: 100%;
  height: 100%;
  border-radius: 50%;
}

.profile-info {
  display: flex;
  flex-direction: column;
  padding-left: 20px;
}

.ahievement-title {
  height: 40%;
  display: flex;
  align-items: center;
}

.profile-id {
  height: 60%;
  display: flex;
  align-items: center;
}

.edit-icon {
  padding-left: 20px;
}

.follow-info {
  height: 50px;
  display: flex;
  align-items: center;
}

.bio-container {
  height: 120px;
  padding-top: 10px;
  border-top: 1px solid lightgray;
  border-bottom: 1px solid lightgray;
}

.exp-container {
  height: 75px;
  width: 100%;
  display: flex;
}

.tier-img {
  height: 100%;
  width: 75px;
  flex-shrink: 0;
}

.tier-img img {
  height: 100%;
  width: 100%;
}

.exp-info {
  width: 100%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  padding-left: 10px;
}

.exp-text {
  display: flex;
  justify-content: flex-end;
  margin-bottom: 5px;
}

.exp-bar {
  height: 20%;
  width: 100%;
  position: relative;
}

.exp-background {
  background-color: lightgray;
  border-radius: 15px;
  width: 100%;
  height: 100%;
  position: absolute;
  top: 0;
  left: 0;
  z-index: 1;
}

.exp-fill {
  background: linear-gradient(
    90deg,
    rgb(255, 239, 148) 0%,
    rgb(255, 220, 100) 50%,
    rgb(255, 200, 50) 100%
  );
  border-radius: 15px;
  width: 50%;
  height: 100%;
  position: absolute;
  top: 0;
  left: 0;
  z-index: 2;
  animation: fill-animation 1.5s ease-out forwards; /* 애니메이션 설정 */
}

@keyframes fill-animation {
  from {
    width: 0%; /* 시작 너비 */
  }
  to {
    width: var(--target-width); /* 종료 너비 */
  }
}

.quest-container {
  padding-top: 10px;
}

.quest-text {
  padding-bottom: 10px;
}

.quest-rate {
  display: flex;
  justify-content: center;
}
</style>
