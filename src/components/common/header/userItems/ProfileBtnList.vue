<script setup>
import NoticeList from "@/components/common/header/userItems/NoticeList.vue";
import { Button, Drawer, Menu } from "primevue";

import { ref } from "vue";
import { useRouter } from "vue-router";
import { storeToRefs } from "pinia";
import { useUserStore } from "@/stores/user-store";

const visibleNotice = ref(false);

const router = useRouter();

const userStore = useUserStore();
const { userInfo } = storeToRefs(userStore);
const { userLogout } = userStore;

const menu = ref();
const items = ref([
  {
    items: [
      {
        label: "My Page",
        command: () => {
          router.push({ name: "myPage" });
        },
      },
      {
        separator: true,
      },
      {
        label: "Logout",
        command: async () => {
          await userLogout();
          router.push({ name: "main"});
        },
      },
    ],
  },
]);

const toggle = (event) => {
  menu.value.toggle(event);
};
</script>

<template>
  <div class="profile-items">
    <div class="btn-plan-start">
      <RouterLink :to="{ name: 'make-plan' }" class="fa-sm">
        <Button label="Start New Trip" severity="warn" variant="outlined" />
      </RouterLink>
    </div>
    <div class="profile-item">
      <Button
        icon="pi pi-bell"
        @click="visibleNotice = true"
        severity="contrast"
        variant="text"
        size="large"
      />

      <Drawer v-model:visible="visibleNotice" header="Notice" position="right">
        <NoticeList />
      </Drawer>
    </div>
    <div class="profile-item">
      <img :src="userInfo.profileImg" class="profile-img" />
    </div>
    <div class="profile-item">
      <Button
        type="button"
        class="text-sm"
        @click="toggle"
        severity="contrast"
        text
        aria-haspopup="true"
        aria-controls="overlay_menu"
      >
        {{ userInfo.id }}
        <font-awesome-icon
          class="profile-icon"
          :icon="['fas', 'chevron-down']"
        />
      </Button>
      <Menu
        class="profile-menu"
        ref="menu"
        id="overlay_menu"
        :model="items"
        :popup="true"
      />
    </div>
  </div>
</template>

<style scoped>
.user-itmes {
  display: flex;
  align-items: center;
}

.btn-plan-start {
  padding-right: 40px;
}

.profile-items {
  display: flex;
  align-items: center;
}

.profile-item {
  display: flex;
  align-items: center;
}

.profile-img {
  border-radius: 100%;
  width: 45px;
  height: 45px;
  object-fit: cover;
  margin-left: 10px;
}

.profile-icon {
  padding-left: 5px;
}
</style>
