<template>
    <div id="smalldai_create_table">
        <el-row>
            <el-col :span="5">
                    <small-dai-create-side-message></small-dai-create-side-message>
            </el-col>
            <el-col :span="12" :offset="1">
                 <small-dai-create-center-table></small-dai-create-center-table>
            </el-col>
            <el-col :span="5" :offset="1">
                
            </el-col>
        </el-row>
       
    </div>
</template>
<script>
import SmallDaiCreateCenterTable from '../components/create/CreateCenterTable.vue'
import SmallDaiCreateSideMessage from '../components/create/CreateSideMessage.vue'
export default {
    name: 'SmallDaiCreateProject',
    components:{
        SmallDaiCreateCenterTable,
        SmallDaiCreateSideMessage
    }
}
</script>
<style scoped>
#smalldai_create_table{
    width: 100%;
    height: 100vh;
}
</style>