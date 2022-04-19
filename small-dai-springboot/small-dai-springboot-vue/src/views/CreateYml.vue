<template>
    <div id="smalldai_create_project">
        <el-row>
            <el-col :span="5">
                    <small-dai-create-side-message></small-dai-create-side-message>
            </el-col>
            <el-col :span="12" :offset="1">
                 <small-dai-create-center-yml></small-dai-create-center-yml>
            </el-col>
            <el-col :span="5" :offset="1">

            </el-col>
        </el-row>
       
    </div>
</template>
<script>
import SmallDaiCreateCenterYml from '../components/create/CreateCenterYml.vue'
import SmallDaiCreateSideMessage from '../components/create/CreateSideMessage.vue'
export default {
    name: 'SmallDaiCreateYml',
    components:{
        SmallDaiCreateCenterYml,
        SmallDaiCreateSideMessage
    }
}
</script>
<style scoped>
#smalldai_create_project{
    width: 100%;
    height: 100vh;
}
</style>