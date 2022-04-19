<template>
    <div id="smalldai_create_mapper">
        <el-row>
            <el-col :span="5">
                <small-dai-create-side-message></small-dai-create-side-message>
            </el-col>
            <el-col :span="12" :offset="1">
                <small-dai-create-center-mapper></small-dai-create-center-mapper>
            </el-col>
            <el-col :span="5" :offset="1">
                
            </el-col>
        </el-row>
    </div>
</template>
<script>
import SmallDaiCreateCenterMapper from '../components/create/createCenterMapper.vue'
import SmallDaiCreateSideMessage from '../components/create/CreateSideMessage.vue'
export default {
    name: 'SmallDaiCreateMapper',
    components:{
        SmallDaiCreateCenterMapper,
        SmallDaiCreateSideMessage
    }
}
</script>
<style scoped>
#smalldai_create_mapper{
    width: 100%;
    height: 100vh;
}
</style>