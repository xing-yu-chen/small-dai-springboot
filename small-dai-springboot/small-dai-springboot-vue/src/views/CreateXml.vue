<template>
    <div id="smalldai_create_xml">
        <el-row>
            <el-col :span="5">
                <small-dai-create-side-message></small-dai-create-side-message>
            </el-col>
            <el-col :span="12" :offset="1">
                <small-dai-create-center-xml></small-dai-create-center-xml>
            </el-col>
            <el-col :span="5" :offset="1">

            </el-col>
        </el-row>
       
    </div>
</template>
<script>
import SmallDaiCreateCenterXml from '../components/create/CreateCenterXml.vue'
import SmallDaiCreateSideMessage from '../components/create/CreateSideMessage.vue'
export default {
    name: 'SmallDaiCreateXml',
    components:{
        SmallDaiCreateSideMessage,
        SmallDaiCreateCenterXml
    }
}
</script>
<style scoped>
#smalldai_create_xml{
    width: 100%;
    height: 150vh;
}
</style>