<script setup>
import { RouterLink } from "vue-router";

import Button from "primevue/button";

import Logo from "@/assets/img/logo_zip.png";
import ProfileImg from "@/assets/img/profile.jpg";
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
    <div class="">
      <span>
        <RouterLink :to="{ name: 'make-plan' }" class="fa-sm ">
          <Button label="Start New Trip" severity="warn" variant="outlined" />
        </RouterLink>
      </span>
      <span>
        <Button icon="pi pi-bell" severity="contrast" variant="text" />
        <!-- <font-awesome-icon :icon="['far', 'bell']" class="fa-xl" /> -->
      </span>
      <span>
        <img :src="ProfileImg" class="profile-img" />
      </span>
      <span class="fa-sm"> Kim_JJong </span>
      <span>
        <RouterLink :to="{ name: 'signup' }" class="fa-sm">
          <Button label="Sign up" severity="warn" variant="outlined" />
        </RouterLink>
      </span>
      <span>
        <RouterLink :to="{ name: 'login' }" class="fa-sm">
          <Button label="Login" severity="warn" variant="outlined" />
        </RouterLink>
      </span>
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
  padding: 0px 15px;
}


.nav-item:hover {
  color: navajowhite
}



.profile-img {
  border-radius: 100%;
  width: 45px;
}

</style>
