<script setup>
import SelectedListItem from '@/components/plan/make/SelectedListItem.vue';

import Button from "primevue/button";

import ConfirmDialog from 'primevue/confirmdialog';
import { useConfirm } from "primevue/useconfirm";
import { useToast } from "primevue/usetoast";

const confirm = useConfirm();
const toast = useToast();

const showTemplate = () => {
    confirm.require({
        group: 'templating',
        header: '  ',
        message: 'Please confirm to proceed moving forward.',
        icon: 'pi pi-exclamation-circle',
        rejectProps: {
            label: 'Cancel',
            icon: 'pi pi-times',
            outlined: true,
            size: 'small'
        },
        acceptProps: {
            label: 'Save',
            icon: 'pi pi-check',
            size: 'small'
        },
        accept: () => {
            toast.add({ severity: 'info', summary: 'Confirmed', detail: 'You have accepted', life: 3000 });
        },
        reject: () => {
            toast.add({ severity: 'error', summary: 'Rejected', detail: 'You have rejected', life: 3000 });
        }
    });
};


</script>

<template>
  <div class="container">
    <div class="seleted-header">
        <div class="header-item fa-lg">
            <font-awesome-icon :icon="['fas', 'location-dot']" />
        </div>
        <div class="header-item fa-lg">
            여행 장소 선택
        </div>
        <div class="header-item">
            <Button label="코스 최적화" severity="warn" variant="outlined" />
<!-- 
                ConfirmDialog 컴포넌트
                <ConfirmDialog group="templating">
                <template #message="slotProps">
                    <div class="flex flex-col items-center w-full gap-4 border-b border-surface-200 dark:border-surface-700">
                    <!-- 컴포넌트 삽입 -->
                    <!-- <OptimizationDialog /> -->
                    <!-- </div>
                </template> -->
                <!-- </ConfirmDialog> -->
                
                <!-- <Button @click="showTemplate()" label="Save"></Button> --> 


                <!-- <ConfirmDialog group="templating">
                    <template #message="slotProps">
                        <div class="flex flex-col items-center w-full gap-4 border-b border-surface-200 dark:border-surface-700">
                            <i :class="slotProps.message.icon" class="!text-6xl text-primary-500"></i>
                            <p>{{ slotProps.message.message }}</p>
                        </div>
                    </template>
                </ConfirmDialog>
                <Button @click="showTemplate()" label="Save"></Button> -->


        </div>
    </div>
    <div class="select-list">
        <div class="selected-list-item">
            <SelectedListItem />
        </div>
        <div class="selected-list-item">
            <SelectedListItem />
        </div>
        <div class="selected-list-item">
            <SelectedListItem />
        </div>
        <div class="selected-list-item">
            <SelectedListItem />
        </div>
    </div>
    
  </div>
  <div class="select-btn">
    <Button label="여행 만들기" severity="warn" variant="outlined" />
  </div>
</template>

<style scoped>

.seleted-header {
    display: flex;
    align-items: center;
    height: 50px;
    padding: 5px;
}

.header-item {
    padding: 0px 10px;
    margin-right: 5px;
}

.header-item:last-child {
    margin-right: 0px;
    margin-left: auto;
}

.select-list {
    padding: 5px;
}

.selected-list-item {
    border: 1px solid lightgray;
    margin: 10px;
    height: 80px;
    border-radius: 5px;
}

.select-btn {
    display: flex;
    justify-content: flex-end;
    padding: 15px;
}

</style>
