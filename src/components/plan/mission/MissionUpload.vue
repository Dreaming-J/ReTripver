<script setup>
defineProps({
    course : {
        type:Object,
        default : () => ({
            uploadImg: new URL("@/assets/img/sido.1.jpg", import.meta.url).href,
        })
    }
})
</script>

<template>
  <div class="container">
    <div class="img-container">
        <img :src="course.questImg" width="100%" height="100%">
    </div>
    <div class="img-container">
        <form>
        <label for="uploadImg">
            <img :src="course.uploadImg" width="100%" height="100%">
        </label>
            <input type="file" id="uploadImg" style="display: none;"/>
        </form>
    </div>
  </div>
</template>

<style scoped>
.container {
    width: 100%;
    height: 100%;
    display: flex;
    justify-content: space-around;
    /* align-items: center; */
}

.img-container {
    width: 300px;
    height: 200px;
    /* border: 1px solid peru; */
}

.img-container img {
    border-radius: 10px;
}

form img {
    filter: brightness(0.7);
}

form img:hover {
    filter: brightness(0.4);
}

</style>
