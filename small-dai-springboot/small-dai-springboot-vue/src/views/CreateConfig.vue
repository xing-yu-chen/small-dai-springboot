<template>
    <div id="smalldai_create_config">
        <el-row>
            <el-col :span="5">
                    <small-dai-create-side-message></small-dai-create-side-message>
            </el-col>
            <el-col :span="12" :offset="1">
                <small-dai-create-center-config></small-dai-create-center-config>
            </el-col>
            <el-col :span="5" :offset="1">

            </el-col>
        </el-row>
       
    </div>
</template>
<script>
import SmallDaiCreateCenterConfig from '../components/create/CreateCenterConfig.vue'
import SmallDaiCreateSideMessage from '../components/create/CreateSideMessage.vue'
export default {
    name: 'SmallDaiCreateConfig',
    components:{
        SmallDaiCreateCenterConfig,
        SmallDaiCreateSideMessage
    }
}
</script>
<style scoped>
#smalldai_create_config{
    width: 100%;
    height: 100vh;
}
</style>