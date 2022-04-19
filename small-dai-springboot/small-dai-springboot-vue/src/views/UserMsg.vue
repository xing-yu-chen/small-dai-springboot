<template>
    <div id="smalldai_user_msg">
        <br>
        <el-row v-if="show">
            <el-col :span="12" :offset="6">
                <el-card class="box-card">
                    <el-descriptions title="用户信息">
                        <el-descriptions-item label="用户ID">{{userInfo.uId}}</el-descriptions-item>
                        <el-descriptions-item label="用户名">{{userInfo.name}}</el-descriptions-item>
                        <el-descriptions-item label="手机号">{{userInfo.tel}}</el-descriptions-item>
                        <el-descriptions-item label="邮箱">{{userInfo.email}}</el-descriptions-item>
                        <el-descriptions-item label="年龄">{{userInfo.age}}</el-descriptions-item>
                        <el-descriptions-item label="注册IP">{{userInfo.createIp}}</el-descriptions-item>
                        <el-descriptions-item label="创建时间">{{userInfo.gmtCreate}}</el-descriptions-item>
                        <el-descriptions-item label="修改时间">{{userInfo.gmtModified}}</el-descriptions-item>
                        <el-descriptions-item label="角色">{{userInfo.roleTO.nameZh}}</el-descriptions-item>
                    </el-descriptions>
                    <el-row>
                        <el-col :span="6" :offset="9">
                            <el-button @click="showChange">修改个人信息</el-button>
                        </el-col>
                    </el-row>
                </el-card>
            </el-col>
        </el-row>
        <el-row v-if="!show">
            <el-col :span="12" :offset="6">
                <el-card class="box-card">
                    <el-descriptions title="用户信息修改">
                        <el-descriptions-item label="用户ID">{{userInfo.uId}}</el-descriptions-item>
                        <el-descriptions-item label="用户名">
                           <el-input v-model="userInfo.name" placeholder="请输入用户名"></el-input>
                        </el-descriptions-item>
                        <el-descriptions-item label="手机号">
                            <el-input v-model="userInfo.tel" placeholder="请输入手机号"></el-input>
                        </el-descriptions-item>
                        <el-descriptions-item label="邮箱">{{userInfo.email}}</el-descriptions-item>
                        <el-descriptions-item label="年龄">
                            <el-input-number v-model="userInfo.age"  :min="1" :max="100"></el-input-number>
                        </el-descriptions-item>
                        <el-descriptions-item label="注册IP">{{userInfo.createIp}}</el-descriptions-item>
                        <el-descriptions-item label="创建时间">{{userInfo.gmtCreate}}</el-descriptions-item>
                        <el-descriptions-item label="修改时间">{{userInfo.gmtModified}}</el-descriptions-item>
                        <el-descriptions-item label="角色">{{userInfo.roleTO.nameZh}}</el-descriptions-item>
                    </el-descriptions>
                    <el-row>
                        <el-col :span="6" :offset="9">
                            <el-button type="primary" @click="updateUser">确认修改</el-button>
                        </el-col>
                    </el-row>
                </el-card>
            </el-col>
        </el-row>
    </div>
</template>
<script>
import {updateUser} from '../api/axiosApi'
export default {
    name: 'SmallDaiUserMsg',
    data(){
        return{
            userInfo:{},
            show: true
        }
    },
    methods:{
        updateUser(){
            updateUser(this.userInfo).then(res=>{
                this.$message({
                    message: res.data.msg,
                    type: 'success'
                });
                this.show = !this.show
            })
        },
        showChange(){
            this.show = !this.show
        }
    },
    created(){
        this.userInfo = JSON.parse(window.localStorage.getItem('userInfo'))
    }
}
</script>
<style scoped>
#smalldai_user_msg{
    width: 100%;
    height: 85%;
}
</style>