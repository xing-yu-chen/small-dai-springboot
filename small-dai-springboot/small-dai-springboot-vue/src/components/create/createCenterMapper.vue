<template>
    <div id="smalldai_create_center_project">
        <el-card class="box-card">
                    <h1 style="text-align:center">
                        项目创建<span class="h1_span">创建表数据关联</span>
                    </h1>
                    <el-form label-position="right" label-width="200px">
                        <el-form-item label="是否联表查询单条">
                            <el-switch inactive-value="false" active-value="true" v-model="mapperVO.selectByIdMore"></el-switch>
                        </el-form-item>
                        <el-form-item label="是否联表查询多条">
                            <el-switch inactive-value="false" active-value="true" v-model="mapperVO.selectListMore"></el-switch>
                        </el-form-item>
                        <el-form-item label="是否联表删除某条数据">
                            <el-switch inactive-value="false" active-value="true" v-model="mapperVO.deleteMore"></el-switch>
                        </el-form-item>
                    </el-form>
                    <div style="text-align:center">
                        <el-popover
                            placement="top-start"
                            title="警告"
                            width="200"
                            trigger="hover"
                            content="请您确认您的信息已经填写无误，如有异常您的项目会创建失败，请认真阅读左侧项目创建帮助内容。">
                            <el-button slot="reference" @click="saveMapper">确认联表关系</el-button>
                        </el-popover>
                    </div>
                </el-card>
    </div>
</template>
<script>
import {saveMapper} from '../../api/axiosApi'
export default {
    name: 'SmallDaiCreateCenterBanner',
    data(){
        return{
            mapperVO: {
                selectByIdMore: false,
                selectListMore: false,
                deleteMore: false,
                tId: 0
            }
        }
    },
    methods:{
        /**
         * 插入mapper需求
         */
       saveMapper(){
           saveMapper(this.mapperVO).then(res=>{
               this.$message.success(res.data.data)
               this.$router.push({
                   path: '/createXml',
                   query:{
                       tId: this.mapperVO.tId
                   }
               })
           })
       }
    },
    created(){
        this.mapperVO.tId = this.$route.query.tId
    }
}
</script>
<style scoped>
#smalldai_create_center_project{
    width: 100%;
    height: 95vh;
    margin: 0 auto;
}
.h1_span{
    font-size: 18px;
    font-weight: 100;
    color: rgb(43, 43, 44);
}
</style>