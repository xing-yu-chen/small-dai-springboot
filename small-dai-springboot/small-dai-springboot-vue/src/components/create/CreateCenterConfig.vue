<template>
    <div id="smalldai_create_center_config">
        <el-card class="box-card">
                    <h1 style="text-align:center">
                        项目创建<span class="h1_span">创建依赖配置</span>
                    </h1>
                    <el-form label-position="right" label-width="100px">
                        <el-form-item label="IP黑名单">
                            <el-input v-model="configVO.blackIP"></el-input>
                        </el-form-item>
                        <el-form-item label="IP白名单">
                             <el-input v-model="configVO.whiteIP"></el-input>
                        </el-form-item>
                        <el-form-item label="Druid账号">
                             <el-input v-model="configVO.loginUsername"></el-input>
                        </el-form-item>
                        <el-form-item label="Druid密码">
                             <el-input v-model="configVO.loginPassword"></el-input>
                        </el-form-item>
                        <el-form-item label="Swagger标题">
                             <el-input v-model="configVO.swaggerHeader"></el-input>
                        </el-form-item>
                        <el-form-item label="Swagger描述">
                             <el-input v-model="configVO.swaggerDescription"></el-input>
                        </el-form-item>
                        <el-form-item label="Swagger版本">
                             <el-input v-model="configVO.swaggerVersion"></el-input>
                        </el-form-item>
                    </el-form>
                    <div style="text-align:center">
                        <el-popover
                            placement="top-start"
                            title="警告"
                            width="200"
                            trigger="hover"
                            content="请您确认您的信息已经填写无误，如有异常您的项目会创建失败，请认真阅读左侧项目创建帮助内容。">
                            <el-button slot="reference" @click="createConfig">创建依赖配置</el-button>
                        </el-popover>
                    </div>
                </el-card>
    </div>
</template>
<script>
import {createConfig} from '../../api/axiosApi'
export default {
    name: 'SmallDaiCreateCenterConfig',
    data(){
        return{
            configVO: {
                blackIP: '',
                whiteIP: '',
                loginUsername: 'admin',
                loginPassword: 'admin',
                swaggerHeader: 'Demo的Swagger接口文档',
                swaggerDescription: 'Demo的测试描述',
                swaggerVersion: 'v1',
                dId: 0,
            }
        }
    },
    methods:{
        createConfig(){
            createConfig(this.configVO).then(res=>{
                if(res.data.code === 200){
                    this.$message({
                        type: 'success',
                        message: res.data.data
                    });
                    this.$router.push('/createTable')
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
        this.configVO.dId =  JSON.parse(window.localStorage.getItem('database')).dId
    }
}
</script>
<style scoped>
#smalldai_create_center_config{
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