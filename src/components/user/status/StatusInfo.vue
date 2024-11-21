<script setup></script>

<template>
  <div>
    <div class="profile-container">
      <div class="profile-header">
        <div class="profile-image">
          <img src="@/assets/img/profile.jpg" alt="Diver profile image" />
        </div>
        <div class="profile-info">
          <div class="profile-name">
            Kim_JJong
            <span class="edit-icon">✎</span>
          </div>
          <div class="stats">
            <div class="stat-item">
              <span class="stat-value">50</span>
              <span>팔로워</span>
            </div>
            <div class="stat-item">
              <span class="stat-value">50</span>
              <span>팔로잉</span>
            </div>
          </div>
        </div>
      </div>

      <div class="bio">
        드라이빙 중...<br />
        바다에서 쉬고 놀기는 사람...
      </div>

      <button class="follow-button">+ 팔로우</button>

      <div class="progress-section">
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
      </div>
    </div>
  </div>
</template>

<style scoped>
.profile-container {
  font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, sans-serif;
  max-width: 400px;
  padding: 20px;
}

.profile-header {
  display: flex;
  align-items: center;
  gap: 16px;
  margin-bottom: 20px;
}

.profile-image {
  width: 80px;
  height: 80px;
  border-radius: 50%;
  overflow: hidden;
  background: linear-gradient(45deg, #0066cc, #0099ff);
}

.profile-image img {
  width: 100%;
  height: 100%;
  object-fit: cover;
  opacity: 0.7;
}

.profile-info {
  flex: 1;
}

.profile-name {
  font-size: 18px;
  font-weight: 600;
  margin-bottom: 4px;
  display: flex;
  align-items: center;
  gap: 8px;
}

.edit-icon {
  color: #666;
  font-size: 14px;
}

.stats {
  display: flex;
  gap: 16px;
  color: #666;
  font-size: 14px;
}

.stat-item {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.stat-value {
  font-weight: 600;
  color: #000;
}

.bio {
  color: #666;
  font-size: 14px;
  margin-bottom: 16px;
}

.follow-button {
  background-color: #f0f0f0;
  border: none;
  border-radius: 20px;
  padding: 8px 16px;
  font-size: 14px;
  cursor: pointer;
}

.progress-section {
  margin-top: 20px;
}

.exp-bar {
  height: 8px;
  background: #ffe4b5;
  border-radius: 4px;
  margin-bottom: 8px;
}

.mission-bar {
  height: 8px;
  background: #ffb6c1;
  border-radius: 4px;
}

.exp-label,
.mission-label {
  display: flex;
  align-items: center;
  gap: 8px;
  font-size: 14px;
  color: #666;
  margin-bottom: 4px;
}

.badge-icon {
  width: 16px;
  height: 16px;
}
</style>
