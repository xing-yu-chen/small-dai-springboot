<template>
    <div id="about_us_header">
        <el-image
        style="width: 100%; height: 100%"
        :src="url">
        </el-image>
    </div>
</template>
<script>
export default {
    name: 'SmallDaiAboutUsHeader',
    data(){
        return{
            url: require('../../assets/aboutUs/aboutUs.jpg')
        }
    }
}
</script>
<style scoped>
#about_us_header{
    width: 100%;
    height: 50vh;
}
</style>