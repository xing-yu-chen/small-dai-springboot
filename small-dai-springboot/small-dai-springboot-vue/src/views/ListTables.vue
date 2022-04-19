<template>
    <div id="smalldai_list_tables">
        <el-row>
            <el-col :span="5">
                    <small-dai-create-side-message></small-dai-create-side-message>
            </el-col>
            <el-col :span="12" :offset="1">
                 <small-dai-list-center-tables></small-dai-list-center-tables>
            </el-col>
            <el-col :span="5" :offset="1">
                <div>
                    <el-button @click="turnToTable">新建表</el-button>
                    <el-button @click="createEntityPom">前往实体/Pom生成</el-button>
                </div>
                <br>
                <div>
                    <el-button @click="createFixed">创建系统提供登陆模块</el-button>
                </div>
                <br>
                <div>
                    <el-button @click="turnToGenerateHtml">生成前端页面</el-button>
                    <el-button @click="outputSqlScript">导出SQL脚本</el-button>
                </div>
            </el-col>
        </el-row>
    </div>
</template>
<script>
import {creatEntityAndPom, createFixed, generateTables, outputSqlScript} from '../api/axiosApi'
import SmallDaiListCenterTables from '../components/create/ListCenterTables.vue'
import SmallDaiCreateSideMessage from '../components/create/CreateSideMessage.vue'
export default {
    name: 'SmallDaiCreateYml',
    components:{
        SmallDaiListCenterTables,
        SmallDaiCreateSideMessage
    },
    methods:{
        /**
         * 前往表创建
         */
        turnToTable(){
            this.$router.push('/createTable')
        },
        /**
         * 生成实体和Pom
         */
        createEntityPom(){
            creatEntityAndPom(JSON.parse(window.localStorage.getItem('database')).dId).then(res=>{
                this.$message.success(res.data.data)
            })
        },
        /**
         * 生成系统生成登录模块
         */
        createFixed(){
            createFixed(JSON.parse(window.localStorage.getItem('database')).dId).then(res=>{
                this.$message.success(res.data.data)
            })
            generateTables(JSON.parse(window.localStorage.getItem('database')).dId).then(res=>{
                this.$message.success(res.data.data)
            })
        },
        /**
         * 生成前端页面
         */
        turnToGenerateHtml(){
            this.$router.push('/createHtml')
        },
        /**
         * 导出SQL脚本
         */
        outputSqlScript(){
            let data = {
                dId: JSON.parse(window.localStorage.getItem('database')).dId
            }
            outputSqlScript(data).then(res=>{
                this.$message.success(res.data.data)
            })
        }
    }
}
</script>
<style scoped>
#smalldai_list_tables{
    width: 100%;
    height: 100vh;
}
</style>