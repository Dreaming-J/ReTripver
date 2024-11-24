d<script setup>
import { ref } from 'vue';
import { useRouter } from 'vue-router';

const router = useRouter;

const menuItems = ref([
    {label: 'Profile', route: 'myPage-profile', active: true},
    {label: 'Email', route: 'myPage-email', active: false },
    {label: 'Password', route: 'myPage-password', active: false },
])

const menuItemClickEvent = (selectedItem) => {
    menuItems.value = menuItems.value.map(item => ({
        ...item,
        active: item.label === selectedItem.label
    }))
};
</script>

<template>
  <div class="container">
    <nav class="menu-list">
        <div v-for="item in menuItems"
             :key = "item.label"
             :class="['menu-item', {'menu-item-active': item.active}]"
            >
            <RouterLink :to="{ name: `${item.route}` }" @click="menuItemClickEvent(item)">{{ item.label }} </RouterLink>
        </div>
    </nav>
  </div>
</template>

<style scoped>
a {
  text-decoration: none; /* 하이퍼링크 밑줄 제거 */
  color: inherit; /* 부모 요소의 색상 상속 */
}

.container {
    width: 100%;
    height: 100%;
}

.menu-list {
    display: flex;
    flex-direction: column;
    padding: 20px;
}

.menu-item {
    padding-bottom: 20px;
    text-decoration: none;
    color : gray
}

.menu-item-active {
    color: black;
}
</style>
