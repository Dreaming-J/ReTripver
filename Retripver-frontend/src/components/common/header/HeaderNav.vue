<script setup>
import Logo from "@/assets/img/logo.png";
</script>

<template>
  <div>
    <nav>
      <div class="nav-img">
        <RouterLink :to="{ name: 'main' }">
          <img :src="Logo" width="200px" />
        </RouterLink>
      </div>

      <div class="nav-items">
        <RouterLink :to="{ name: 'plan' }" class="nav-item"> Plans </RouterLink>

        <RouterLink :to="{ name: 'attraction' }" class="nav-item">
          Attractions
        </RouterLink>

        <RouterLink :to="{ name: 'user' }" class="nav-item"> Rank </RouterLink>
      </div>
    </nav>
  </div>
</template>

<style scoped>
* {
  text-decoration: none;
  color: black;
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
  color: navajowhite;
}
</style>
