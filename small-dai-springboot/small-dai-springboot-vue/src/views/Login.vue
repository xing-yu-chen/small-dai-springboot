<template>
    <div id="smalldai_login">
        <br>
        <el-row>
            <el-col :md="12" :offset="6">
                <h1 style="text-align:center">
                    小呆生成器 | 登录
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
                        <el-form-item label="密码" label-width="80px">
                            <el-input v-model="account.password" show-password placeholder="请输入密码" style="width: 500px"></el-input>
                        </el-form-item>
                    </el-form>
                    <el-row>
                        <el-col :span="10" style="text-align:right">
                            <el-button type="primary" @click="login">  登  陆  </el-button>
                        </el-col>
                        <el-col :span="12" :offset="2" style="text-align:left">
                            <el-button @click="turnToRegister">没有账号？前往注册</el-button>
                        </el-col>
                    </el-row>
                </el-card>
            </el-col>
        </el-row>
    </div>
</template>
<script>
import { login } from '../api/axiosApi'
export default {
    name: 'SmallDaiLogin',
    data(){
        return{
            account: {
                'email': '',
                'password': ''
            }
        }
    },
    methods:{
        /**
         * 跳转注册
         */
        turnToRegister(){
            this.$router.push('/register')
        },
        /**
         * 登录
         */
        login(){
            login(this.account).then(res=>{
                window.localStorage.setItem('userInfo', JSON.stringify(res.data.data))
                window.localStorage.setItem('token', JSON.stringify(res.data.data.token))
                this.$confirm(res.data.msg)
                    .then(() => {
                        this.$router.push('/')
                        window.location.reload();
                    })
            })
        }
    }
}
</script>
<style scoped>
#smalldai_login{
    width: 100%;
    height: 85vh;
}
</style>