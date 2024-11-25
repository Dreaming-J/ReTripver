<script setup>
import Card from "primevue/card";
import { ref } from "vue";
import { useRouter } from "vue-router";

const router = useRouter();

const props = defineProps({
  rank: {
    type: Object,
    required: true,
  },
  index: {
    type: Number,
    required: true,
  },
});

const getRankColor = (index) => {
  if (index === 0) return "gold-rank";
  if (index === 1) return "silver-rank";
  if (index === 2) return "bronze-rank";
  return "";
};

const getCardStyle = (index) => {
  if (index === 0) return "gold-card";
  if (index === 1) return "silver-card";
  if (index === 2) return "bronze-card";
  return "";
};

const cardClickEvent = (userId) => {
  console.log(userId);
  router.push({ name: "status-user", params: { userId } });
};
</script>

<template>
  <div class="item-container">
    <Card
      class="card-container h-full m-5 shadow-2"
      :class="getCardStyle(index)"
      @click="cardClickEvent(rank.id)"
    >
      <template #content>
        <div class="user-content">
          <div class="user-info">
            <div class="user-item rank-number" :class="getRankColor(index)">
              {{ index + 1 }}
            </div>
            <div class="user-item">
              <img :src="rank.profileImg" width="60" height="60" />
            </div>
            <div class="user-item">
              {{ rank.id }}
            </div>
          </div>
          <div class="tier-info">
            <div class="tier-item">
              {{ rank.achievementTitle }}
            </div>
            <div class="tier-item">
              <img :src="rank.tierInfo.tierImg" height="60" />
            </div>
            <div class="tier-item">{{ rank.exp }} EXP</div>
          </div>

          <!-- <p>순위 : {{ index + 1 }}</p>
          <p>사용자: {{ rank.id }}</p>
          <p>이미지: {{ rank.profileImg }}</p>
          <p>경험치 : {{ rank.exp }}</p>
          <p>타이틀 : {{ rank.achievementTitle }}</p>
          <p>티어 : {{ rank.tierInfo.tierImg }}</p> -->

          <!-- <p>등수: {{ rank.rank }}</p>
          <p>사용자: {{ rank.userId }}</p>
          <p>이미지: {{ rank.porfile }}</p>
          <p>제목: {{ rank.title }}</p>
          <p>지역: {{ rank.sidoName }}</p>
          <p>좋아요: {{ rank.likeCount }}</p>
          <p>방문지 {{ rank.courses.length }}개</p> -->
        </div>
      </template>
    </Card>
  </div>
</template>

<style scoped>
.item-container {
  /* border: 1px solid red; */
  width: 100%;
  height: 100%;
}

.card-container {
  /* border: 1px solid blue; */
  background: white;
  transition: transform 0.3s ease;
  /* overflow: visible !important; 발광 효과가 잘리지 않도록 */
  overflow: hidden;
  position: relative;
}

.card-container:hover {
  transform: translateY(-2px);
}

:deep(.p-card-body) {
  padding: 10px 30px;
}

.user-content {
  display: flex;
  justify-content: space-between;
  align-items: center;
  /* border: 1px solid purple; */
}

.user-info {
  display: flex;
  align-items: center;
}

.user-item {
  padding: 0 10px;
}

.tier-info {
  display: flex;
  align-items: center;
}

.tier-item {
  padding: 0 15px;
}

.tier-info img {
  display: flex;
  align-items: center;
}

.user-info img {
  /* border: 1px solid red; */
  display: flex;
  align-items: center;
  border-radius: 50%;
}

/* 순위별 스타일 */
.rank-number {
  font-weight: bold;
  font-size: 1.2em;
  width: 24px;
  text-align: center;
}

/* 순위 색상 */
.gold-rank {
  color: #ffd700;
}

.silver-rank {
  color: #c0c0c0;
}

.bronze-rank {
  color: #cd7f32;
}

/* 순위별 배경 그라데이션 */
.gold-card {
  background: linear-gradient(
    135deg,
    rgba(255, 215, 0, 0.1) 0%,
    rgba(255, 215, 0, 0.15) 15%,
    rgba(255, 215, 0, 0.05) 40%,
    rgba(255, 255, 255, 1) 100%
  );
  border: 1px solid rgba(255, 215, 0, 0.3);
  position: relative;
}

.gold-card::after {
  content: "";
  position: absolute;
  top: 0;
  left: -100%;
  width: 100%;
  height: 100%;
  background: linear-gradient(
    90deg,
    transparent,
    rgba(255, 215, 0, 0.2),
    transparent
  );
  animation: shine 2s infinite;
}

.silver-card {
  background: linear-gradient(
    135deg,
    rgba(192, 192, 192, 0.1) 0%,
    rgba(192, 192, 192, 0.15) 15%,
    rgba(192, 192, 192, 0.05) 40%,
    rgba(255, 255, 255, 1) 100%
  );
  border: 1px solid rgba(192, 192, 192, 0.3);
  position: relative;
}

.silver-card::after {
  content: "";
  position: absolute;
  top: 0;
  left: -100%;
  width: 100%;
  height: 100%;
  background: linear-gradient(
    90deg,
    transparent,
    rgba(192, 192, 192, 0.2),
    transparent
  );
  animation: shine 2s infinite 0.5s;
}

.bronze-card {
  background: linear-gradient(
    135deg,
    rgba(205, 127, 50, 0.1) 0%,
    rgba(205, 127, 50, 0.15) 15%,
    rgba(205, 127, 50, 0.05) 40%,
    rgba(255, 255, 255, 1) 100%
  );
  border: 1px solid rgba(205, 127, 50, 0.3);
  position: relative;
}

.bronze-card::after {
  content: "";
  position: absolute;
  top: 0;
  left: -100%;
  width: 100%;
  height: 100%;
  background: linear-gradient(
    90deg,
    transparent,
    rgba(205, 127, 50, 0.2),
    transparent
  );
  animation: shine 2s infinite 1s;
}

/* 반짝이는 애니메이션 */
@keyframes shine {
  0% {
    left: -100%;
  }
  50% {
    left: 100%;
  }
  100% {
    left: 100%;
  }
}

/* 반짝이는 애니메이션 */
@keyframes shine {
  0% {
    left: -100%;
  }
  50% {
    left: 100%;
  }
  100% {
    left: 100%;
  }
}

/* 순위 색상 */
.gold-rank {
  color: #ffd700;
  font-weight: 700;
}

.silver-rank {
  color: #c0c0c0;
  font-weight: 700;
}

.bronze-rank {
  color: #cd7f32;
  font-weight: 700;
}

/* 카드 컨텐츠 영역 */
:deep(.p-card-body) {
  padding: 10px 30px;
  background: transparent;
}
</style>
