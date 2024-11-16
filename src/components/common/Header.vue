<script setup>
import { ref } from "vue";
import { RouterLink, useRouter } from "vue-router";

import NoticeList from "@/components/common/NoticeList.vue";

import Button from "primevue/button";
import Menu from 'primevue/menu';

import Drawer from 'primevue/drawer';

// import { TieredMenu } from "primevue";
import Logo from "@/assets/img/logo_zip.png";
import ProfileImg from "@/assets/img/profile.jpg";

const router = useRouter();

const isLogin = ref(true);

const menu = ref();
const items = ref([
    {
        items: [
            {
                label: 'My Page',
                command:() => { router.push({ name: 'myPage'}) }
            },
            {
                separator: true
            },
            {
                label: 'Logout',
                command:() => { console.log("Logout")}
            }
        ]
    }
]);

const toggle = (event) => {
    menu.value.toggle(event);
};

const onItemClick = (event) => {
    const to = event.item.to;
    if (to) {
        // Vue Router를 사용하여 페이지 이동
        // router.push(to);
        console.log(to);
    }
};

const visibleRight = ref(false);




</script>

<template>
  <div class="header-continer">
    <nav>
      <div class="nav-img">
        <RouterLink :to="{ name: 'main' }">
          <img :src="Logo" width="200px" />
        </RouterLink>
      </div>
      <div class="nav-items">
        <RouterLink :to="{ name: 'plan' }" class="fa-sm nav-item">
          Plans
        </RouterLink>
        <RouterLink :to="{ name: 'attraction' }" class="fa-sm nav-item">
          Attractions
        </RouterLink>
        <RouterLink :to="{ name: 'user' }" class="fa-sm nav-item">
          Rank
        </RouterLink>
        <RouterLink :to="{ name: 'status' }" class="fa-sm nav-item">
          My Status
        </RouterLink>
      </div>
    </nav>
    <div class="user-itmes">
      <div class="btn-plan-start">
        <RouterLink :to="{ name: 'make-plan' }" class="fa-sm ">
          <Button label="Start New Trip" severity="warn" variant="outlined" />
        </RouterLink>
      </div>
      <div v-if="isLogin" class="profile-items">
        <div class="profile-item">
          <!-- <Button icon="pi pi-bell" severity="contrast" variant="text" size="large"/> -->
          <!-- <font-awesome-icon :icon="['far', 'bell']" class="fa-xl" /> -->
          <Button icon="pi pi-bell" @click="visibleRight = true" severity="contrast" variant="text" size="large" />
        
          <Drawer v-model:visible="visibleRight" header="Notice" position="right">
            <!-- <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</p> -->
            <NoticeList />
          </Drawer>
        
        
        </div>
        <div class="profile-item">
          <img :src="ProfileImg" class="profile-img" />
        </div>
        <div class="profile-item">
          <Button type="button" class="text-sm" @click="toggle" severity="contrast" text aria-haspopup="true" aria-controls="overlay_menu">
            Jim_JJong
            <font-awesome-icon class="profile-icon" :icon="['fas', 'chevron-down']" />
          </Button>
          <Menu class="profile-menu" ref="menu" id="overlay_menu" :model="items" @item-click="onItemClick" :popup="true" />
        </div>
      </div>
      <div v-else class="btn-list-sign">
        <div class="btn-sign">
          <RouterLink :to="{ name: 'signup' }" class="fa-sm">
            <Button class="btn-signup" label="Sign up" severity="secondary" variant="text" iconPos="left"/>
          </RouterLink>
        </div>
        <div class="btn-sign">
          <RouterLink :to="{ name: 'login' }" class="fa-sm">
            <Button class="btn-login" label="Login" severity="warn" rounded />
          </RouterLink>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
* {
  text-decoration: none;
  color: black;
}

.header-continer{
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 20px;
}

nav {
  display: flex;
}

.nav-img {
  padding-right: 35px;
}

.nav-items {
  margin-top: auto;
  margin-bottom: auto;
}

.nav-item {
  padding: 0px 13px;
}

.nav-item:hover {
  color: navajowhite
}

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

.btn-list-sign {
  display: flex;
  align-items: center;
}

.btn-signup {
  padding-left: 10px;
}

.btn-login {
  width: 120px;
  height: 45px;
}
</style>
