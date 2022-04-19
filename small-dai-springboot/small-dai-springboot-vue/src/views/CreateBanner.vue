<template>
    <div id="smalldai_create_banner">
        <el-row>
            <el-col :span="5">
                    <small-dai-create-side-message></small-dai-create-side-message>
            </el-col>
            <el-col :span="12" :offset="1">
                <small-dai-create-center-banner></small-dai-create-center-banner>
            </el-col>
            <el-col :span="5" :offset="1">

            </el-col>
        </el-row>
       
    </div>
</template>
<script>
import SmallDaiCreateCenterBanner from '../components/create/CreateCenterBanner.vue'
import SmallDaiCreateSideMessage from '../components/create/CreateSideMessage.vue'
export default {
    name: 'SmallDaiCreateBanner',
    components:{
        SmallDaiCreateSideMessage,
        SmallDaiCreateCenterBanner
    }
}
</script>
<style scoped>
#smalldai_create_banner{
    width: 100%;
    height: 100vh;
}
</style>