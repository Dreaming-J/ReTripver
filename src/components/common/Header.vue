<script setup>
import { RouterLink } from "vue-router";

import Button from "primevue/button";

import Logo from "@/assets/img/logo_zip.png";
import ProfileImg from "@/assets/img/profile.jpg";
</script>

<template>
  <div>
    <nav>
      <RouterLink :to="{ name: 'main' }">
        <img :src="Logo" width="200px" />
      </RouterLink>
      |
      <RouterLink :to="{ name: 'plan' }" class="fa-sm text-base">
        Plans
      </RouterLink>
      |
      <RouterLink :to="{ name: 'attraction' }" class="fa-sm text-base">
        Attractions
      </RouterLink>
      |
      <RouterLink :to="{ name: 'user' }" class="fa-sm text-base">
        Rank
      </RouterLink>
      |
      <RouterLink :to="{ name: 'status' }" class="fa-sm text-base">
        My Status
      </RouterLink>
    </nav>
    <div>
      <span>
        <RouterLink :to="{ name: 'make-plan' }" class="fa-sm text-base">
          <Button label="Start New Trip" severity="warn" variant="outlined" />
        </RouterLink>
      </span>
      <span>
        <font-awesome-icon :icon="['far', 'bell']" class="fa-xl" />
      </span>
      <span>
        <img :src="ProfileImg" class="profile-img" />
      </span>
      <span class="fa-sm text-base"> Kim_JJong </span>
      <span>
        <RouterLink :to="{ name: 'signup' }" class="fa-sm text-base">
          <Button label="Sign up" severity="warn" variant="outlined" />
        </RouterLink>
      </span>
      <span>
        <RouterLink :to="{ name: 'login' }" class="fa-sm text-base">
          <Button label="Login" severity="warn" variant="outlined" />
        </RouterLink>
      </span>
    </div>
  </div>
</template>

<style scoped>
.profile-img {
  border-radius: 100%;
  width: 45px;
}
</style>
