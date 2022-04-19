<template>
    <div id="smalldai_header">
        <el-menu
        router
        :default-active="$route.path"
        class="el-menu-demo"
        mode="horizontal">
            <span class="el-menu-item header_title">
                {{title}}<el-tag  effect="dark" size="mini">springboot</el-tag>
            </span>
            <el-menu-item index="/" route="/">  首  页  </el-menu-item>
            <el-submenu index="2">
                <template slot="title">  产 品 源 码  </template>
                <el-menu-item>
                    <a href="https://github.com/xing-yu-chen/small-dai-open-source">开源版生成器&nbsp;&nbsp;&nbsp;&nbsp;</a>
                    <el-tag  effect="dark" type="info" size="mini">boot</el-tag>
                    </el-menu-item>
                <el-menu-item >
                    <a href="https://github.com/xing-yu-chen/small-dai-platform">
                         开源版生成平台&nbsp;<el-tag  effect="dark" type="success" size="mini">boot</el-tag>
                    </a>
                </el-menu-item>
                <el-menu-item>
                     <a href="https://github.com/xing-yu-chen/small-dai-platform">
                        开元版生成平台&nbsp;<el-tag  effect="dark" type="warning" size="mini">cloud</el-tag>
                    </a>
                </el-menu-item>
            </el-submenu>
            <el-submenu index="3">
                <template slot="title">  方  案  </template>
                <el-menu-item index="3-1">进销存管理系统</el-menu-item>
                <el-menu-item index="3-2">人事管理系统</el-menu-item>
                <el-menu-item index="3-3">图书管理系统</el-menu-item>
                <el-menu-item index="3-4"> . . . </el-menu-item>
            </el-submenu>
            <el-submenu index="4">
                <template slot="title">  合  作  </template>
                <el-menu-item index="4-1">方舟计划</el-menu-item>
                <el-menu-item index="4-2">成为合作伙伴</el-menu-item>
            </el-submenu>
            <el-menu-item index="/aboutUs">  关 于 我 们  </el-menu-item>
            <el-menu-item index="/createProject">  在  线 体  验   </el-menu-item>
            <span class="el-menu-item header_right" >
                <div v-if="!loginStatus">
                    <el-button  icon="el-icon-user" @click="turnToLogin">登 录  </el-button>
                    <el-button  icon="el-icon-circle-plus-outline" @click="turnToRegister">注 册  </el-button>
                </div>
                <el-submenu index="7" v-if="loginStatus">
                    <template slot="title">
                        <el-avatar size="small" src="https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fshouji.sogou.com%2Fsapp%2Fdesign%2Fdata%2Fuploads%2F20131227%2F2013122713565131437400.png&refer=http%3A%2F%2Fshouji.sogou.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1652083571&t=503d300e6cd8dd07269d84e858b4cf30"></el-avatar>
                        {{userName}}&nbsp;&nbsp;&nbsp;
                    </template>
                    <el-menu-item index="/userMsg">个人信息</el-menu-item>
                    <el-menu-item index="7-2">我的项目</el-menu-item>
                    <el-menu-item @click="logout">
                        退出
                    </el-menu-item>
                </el-submenu>
            </span>
            
        </el-menu>
    </div>
</template>
<script>
import {logout} from '../../api/axiosApi'
export default {
    name: 'smallDaiHeader',
    data() {
      return {
        title: '小呆代码生成平台',
        menuIndex: '1',
        loginStatus: false,
        userName: '小呆'
      };
    },
    methods: {
        /**
         * 跳转登录
         */
        turnToLogin(){
            this.$router.push('/login')
        },
        /**
         * 跳转注册
         */
        turnToRegister(){
            this.$router.push('/register')
        },
        /** 
         * 退出
        */
       logout(){
          logout().then(res=>{
               window.localStorage.removeItem('userInfo')
               window.localStorage.removeItem('token')
                this.$message({
                    message: res.data.data,
                    type: 'success'
                });
                window.location.reload()
          })
       }
    },
    created(){
        var userInfo = JSON.parse(window.localStorage.getItem('userInfo'))
        if( userInfo !== '' && userInfo !== null){
            this.loginStatus = true
            this.userName = userInfo.name
        }else{
            this.loginStatus = false
        }
    }
}
</script>
<style scoped>
#samlldai_header{
    width: 100%;
    height: 10vh;
}
.header_title{
    width: 20%;
    text-align: center;
    font-size: 20px;
}
.header_right{
    position: absolute;
    right: 5%;
}
a{
    text-decoration: none;
    color: #96999D;
}
</style>