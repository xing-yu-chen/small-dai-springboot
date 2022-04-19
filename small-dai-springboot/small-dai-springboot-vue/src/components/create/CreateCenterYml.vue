<template>
    <div id="smalldai_create_center_yml">
        <el-card class="box-card">
                    <h1 style="text-align:center">
                        项目创建<span class="h1_span">创建自己的配置文件</span>
                    </h1>
                    <el-form label-position="right" label-width="100px">
                       <el-form-item label="数据库地址">
                            <el-input v-model="ymlVO.address"></el-input>
                        </el-form-item>
                        <el-form-item label="数据库端口">
                            <el-input v-model="ymlVO.port"></el-input>
                        </el-form-item>
                        <el-form-item label="用户名">
                            <el-input v-model="ymlVO.username"></el-input>
                        </el-form-item>
                        <el-form-item label="密码">
                            <el-input v-model="ymlVO.password"></el-input>
                        </el-form-item>
                        <el-form-item label="邮箱账号">
                            <el-input v-model="ymlVO.qqEmail"></el-input>
                        </el-form-item>
                        <el-form-item label="邮箱token">
                            <el-input v-model="ymlVO.token"></el-input>
                        </el-form-item>
                    </el-form>
                    <div style="text-align:center">
                        <el-popover
                            placement="top-start"
                            title="警告"
                            width="200"
                            trigger="hover"
                            content="请您确认您的信息已经填写无误，如有异常您的项目会创建失败，请认真阅读左侧项目创建帮助内容。">
                            <el-button slot="reference" @click="createYml">创建配置文件</el-button>
                        </el-popover>
                    </div>
                </el-card>
    </div>
</template>
<script>
import {createYml} from '../../api/axiosApi'
export default {
    name: 'SmallDaiCreateCenterProject',
    data(){
        return{
             ymlVO: {
                address: 'localhost',
                port: 3306,
                username: 'root',
                password: '123456',
                qqEmail: 'demo@qq.com',
                token: 'demoxx',
                dId: 0,
            }
        }
    },
    methods:{
        createYml(){
            createYml(this.ymlVO).then(res=>{
                if(res.data.code === 200){
                    this.$message({
                        type: 'success',
                        message: res.data.data
                    });
                    this.$router.push('/createConfig')
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
        this.ymlVO.dId =  JSON.parse(window.localStorage.getItem('database')).dId
    }
}
</script>
<style scoped>
#smalldai_create_center_yml{
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