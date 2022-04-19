<template>
    <div id="smalldai_create_center_project">
        <el-card class="box-card">
                    <h1 style="text-align:center">
                        项目创建<span class="h1_span">快速创建自己的项目</span>
                    </h1>
                    <el-form label-position="right" label-width="100px">
                        <el-form-item label="包名">
                            <el-input v-model="databaseVO.springGroup"></el-input>
                        </el-form-item>
                        <el-form-item label="项目名">
                            <el-input v-model="databaseVO.springArtifact"></el-input>
                        </el-form-item>
                        <el-form-item label="项目描述">
                            <el-input v-model="databaseVO.springDescription"></el-input>
                        </el-form-item>
                        <el-form-item label="数据库地址">
                            <el-input v-model="databaseVO.address"></el-input>
                        </el-form-item>
                        <el-form-item label="数据库端口">
                            <el-input v-model="databaseVO.port"></el-input>
                        </el-form-item>
                        <el-form-item label="数据库备注">
                            <el-input v-model="databaseVO.remark"></el-input>
                        </el-form-item>
                        <el-form-item label="数据库用户名">
                            <el-input v-model="databaseVO.username"></el-input>
                        </el-form-item>
                        <el-form-item label="数据库密码">
                            <el-input v-model="databaseVO.password"></el-input>
                        </el-form-item>
                    </el-form>
                    <div style="text-align:center">
                        <el-popover
                            placement="top-start"
                            title="警告"
                            width="200"
                            trigger="hover"
                            content="请您确认您的信息已经填写无误，如有异常您的项目会创建失败，请认真阅读左侧项目创建帮助内容。">
                            <el-button slot="reference" @click="createProject">创建项目</el-button>
                        </el-popover>
                    </div>
                </el-card>
    </div>
</template>
<script>
import {createProject} from '../../api/axiosApi'
export default {
    name: 'SmallDaiCreateCenterProject',
    data(){
        return{
            databaseVO: {
                springGroup: 'org.example',
                springArtifact: 'demoproject',
                springDescription: 'Demo Project of SpringBoot',
                address: 'localhost',
                port: '3306',
                username: 'root',
                password: '123456',
                remark: 'Demo Database',
                uId: 0
            }
        }
    },
    methods:{
        /**
         * 创建项目
         */
        createProject(){
            createProject(this.databaseVO).then(res=>{
                // 成功
                if(res.data.code === 200){
                    window.localStorage.setItem('database', JSON.stringify(res.data.data))
                    this.$message({
                        type: 'success',
                        message: '项目创建成功'
                    });
                    this.$router.push('/createBanner')
                }else{
                    // 失败
                    this.$message({
                            type: 'error',
                            message: res.data.data
                    });
                }
            })
        }
    },
    created(){
        this.databaseVO.uId =  JSON.parse(window.localStorage.getItem('userInfo')).uId
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