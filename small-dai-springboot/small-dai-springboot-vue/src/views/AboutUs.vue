<template>
    <div id="about_us">
        <small-dai-about-us-header></small-dai-about-us-header>
        <br>
        <small-dai-about-us-center></small-dai-about-us-center>
    </div>
</template>
<script>
import SmallDaiAboutUsHeader from '../components/aboutUs/AboutUsHeader.vue'
import SmallDaiAboutUsCenter from '../components/aboutUs/AboutUsCenter.vue'
export default {
    name: 'SmallDaiAboutUs',
    components:{
        SmallDaiAboutUsHeader,
        SmallDaiAboutUsCenter
    }
}
</script>
<style scoped>
#about_us{
    width: 100%;
    height: 100vh;
}
</style>