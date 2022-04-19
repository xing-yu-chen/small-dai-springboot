<template>
    <div id="smalldai_register">
        <br>
        <el-row>
            <el-col :md="12" :offset="6">
                <h1 style="text-align:center">
                    小呆生成器 | 注册
                </h1>
            </el-col>
        </el-row>
        <el-row>
            <el-col :md="12" :offset="6">
                <el-card class="box-card">
                    <el-form :inline="true" :model="account" class="demo-form-inline"  size="medium">
                         <el-form-item label="邮箱" label-width="80px">  
                                <el-input v-model="account.email" placeholder="请输入邮箱" style="width: 500px"></el-input>
                        </el-form-item>
                        <el-row>
                             <el-col :span="16">
                                 <el-form-item label="验证码" label-width="80px">  
                                    <el-input v-model="account.code" placeholder="请输入验证码" style="width:300px"></el-input>
                                 </el-form-item>
                            </el-col>
                            <el-col :span="8">
                                    <el-button @click="sendEmail">发送验证码</el-button>
                            </el-col>
                        </el-row>
                        <el-form-item label="密码" label-width="80px">
                            <el-input v-model="account.password" show-password placeholder="请输入密码" style="width: 500px"></el-input>
                        </el-form-item>
                        <el-form-item label="用户名" label-width="80px">  
                                <el-input v-model="account.name" placeholder="请输入用户名" style="width: 500px"></el-input>
                        </el-form-item>
                        <el-form-item label="手机号" label-width="80px">  
                                <el-input v-model="account.tel" placeholder="请输入手机号" style="width: 500px"></el-input>
                        </el-form-item>
                        <el-form-item label="年龄" label-width="80px">  
                                <el-input v-model="account.age" placeholder="请输入年龄" style="width: 500px"></el-input>
                        </el-form-item>
                    </el-form>
                    <el-row>
                        <el-col :span="10" style="text-align:right">
                            <el-button type="primary" @click="register">  注  册  </el-button>
                        </el-col>
                        <el-col :span="12" :offset="2" style="text-align:left">
                            <el-button @click="turnToRegister">已有账号？前往登陆</el-button>
                        </el-col>
                    </el-row>
                </el-card>
            </el-col>
        </el-row>
    </div>
</template>
<script>
import {sengRegisterEmail, register} from '../api/axiosApi'
export default {
    name: 'SmallDaiRegister',
    data(){
        return{
            account:{
                name: '',
                password: '',
                code: '',
                tel: '',
                email: '',
                age: ''
            }
        }
    },
    methods:{
        /**
         * 发送验证码
         */
        sendEmail(){
            var email = {
                'email' : this.account.email
            }
            sengRegisterEmail(email).then(res=>{
                this.$message({
                    message: res.data.data,
                    type: 'success'
                });
            })
        },
        /**
         * 注册
         */
        register(){
            register(this.account).then(res=>{
                if(res.data.code == 200){
                    this.$confirm(res.data.data)
                    .then(() => {
                        this.$router.push('/login')
                    })
                }else{
                    this.$confirm(res.data.data)
                    .then(() => {
                    })
                }
            })
        },
        /**
         * 跳转注册
         */
        turnToRegister(){
            this.$router.push('/login')
        }
    }
}
</script>
<style scoped>
#smalldai_register{
    width: 100%;
    height: 85vh;
}
</style>