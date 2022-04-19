<template>
    <div id="smalldai_index">
        <small-dai-banner-index></small-dai-banner-index>
        <br>
        <small-dai-center-index></small-dai-center-index>
    </div>
</template>
<script>
import SmallDaiBannerIndex from '../components/index/BannerIndex.vue'
import SmallDaiCenterIndex from '../components/index/CenterIndex.vue'
export default {
    name: 'SmallDaiIndex',
    components:{
        SmallDaiBannerIndex,
        SmallDaiCenterIndex
    }
}
</script>
<style scoped>
#smalldai_index{
    width: 100%;
    height: 100vh;
}
</style>