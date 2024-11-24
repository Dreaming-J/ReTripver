<script setup>

import {Card, Button} from 'primevue';

</script>

<template>
  <div class="container">
    <Card style="width: 100%; height:100%; overflow: hidden">
        <template #header>
            <img alt="user header" src="https://primefaces.org/cdn/primevue/images/usercard.png" />
        </template>
        <template #title><p class="text-lg">Advanced Card</p></template>
        <template #subtitle><p class="text-base">Card subtitle</p></template>
        <template #footer>
            <div class="flex justify-content-end">
                <Button label="Save" class="col-2" text size="small">
                  <font-awesome-icon class="btn-like" :icon="['far', 'heart']" />
                </Button>
            </div>
        </template>
    </Card>
  </div>
</template>

<style scoped>

:deep(.svg-inline--fa) {
  height: 25px;
}
</style>
