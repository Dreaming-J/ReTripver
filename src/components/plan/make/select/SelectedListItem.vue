<script setup>
defineProps({
  select: {
    type: Object,
  },
});
</script>

<template>
  <div>
    <div>no : {{ select.no }}</div>
    <div>title : {{ select.title }}</div>
  </div>
</template>

<style scoped></style>
