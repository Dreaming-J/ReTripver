<script setup>
  import { ref } from 'vue'

  const props = defineProps({
    rank: {
      type: Object,
      required: true
    }
  })
</script>

<template>
  <div>
    <br>
    <p>{{ rank.userId }}의 {{ rank.title }}</p>
    <br>
  </div>
</template>

<style scoped></style>
