import Vue from 'vue'
import VueRouter from 'vue-router'
import SmallDaiIndex from '../views/Index'
import SmallDaiAboutUs from '../views/AboutUs'
import SmallDaiLogin from '../views/Login'
import SmallDaiRegister from '../views/Register'
import SmallDaiUserMsg from '../views/UserMsg'
import SmallDaiCreateProject  from '../views/CreateProject'
import SmallDaiCreateBanner from '../views/CreateBanner'
import SmallDaiCreateYml from '../views/CreateYml'
import SmallDaiCreateConfig from '../views/CreateConfig'
import SmallDaiCreateTable from '../views/CreateTable'
import SmallDaiListTables from '../views/ListTables'
import SmallDaiCreateColumn from '../views/CreateColumn'
import SmallDaiCreateMapper from '../views/CreateMapper'
import SmallDaiCreateXml from '../views/CreateXml'
import SmallDaiCreateHtml from '../views/CreateHtml'
import SmallDaiManager from '../views/admin/Manager'

Vue.use(VueRouter)

const routes = [
  {
    path:'/',
    name: 'SmallDaiIndex',
    meta:{
      title: '首页',
    },
    component: SmallDaiIndex
  },
  {
    path:'/aboutUs',
    name: 'SmallDaiAboutUs',
    meta:{
      title: '关于我们',
    },
    component: SmallDaiAboutUs
  },
  {
    path:'/login',
    name: 'SmallDaiLogin',
    meta:{
      title: '登录',
    },
    component: SmallDaiLogin
  },
  {
    path:'/register',
    name: 'SmallDaiRegister',
    meta:{
      title: '注册',
    },
    component: SmallDaiRegister
  },
  {
    path:'/userMsg',
    name: 'SmallDaiUserMsg',
    meta:{
      title: '用户信息',
    },
    component: SmallDaiUserMsg
  },
  {
    path:'/createProject',
    name: 'SmallDaiCreateProject',
    meta:{
      title: '创建项目',
    },
    component: SmallDaiCreateProject
  },
  {
    path:'/createBanner',
    name: 'SmallDaiCreateBanner',
    meta:{
      title: '创建Banner',
    },
    component: SmallDaiCreateBanner
  },
  {
    path:'/createYml',
    name: 'SmallDaiCreateYml',
    meta:{
      title: '创建Yml',
    },
    component: SmallDaiCreateYml
  },
  {
    path:'/createConfig',
    name: 'SmallDaiCreateConfig',
    meta:{
      title: '创建Config',
    },
    component: SmallDaiCreateConfig
  },
  {
    path:'/createTable',
    name: 'SmallDaiCreateTable',
    meta:{
      title: '创建表格',
    },
    component: SmallDaiCreateTable
  },
  {
    path:'/listTables',
    name: 'SmallDaiListTables',
    meta:{
      title: '列出所有表格',
    },
    component: SmallDaiListTables
  },
  {
    path:'/createColumns',
    name: 'SmallDaiCreateColumn',
    meta:{
      title: '创建字段们',
    },
    component: SmallDaiCreateColumn
  },
  {
    path:'/createMapper',
    name: 'SmallDaiCreateMapper',
    meta:{
      title: '创建Mapper',
    },
    component: SmallDaiCreateMapper
  },
  {
    path:'/createXml',
    name: 'SmallDaiCreateXml',
    meta:{
      title: '创建Xml',
    },
    component: SmallDaiCreateXml
  },
  {
    path:'/createHtml',
    name: 'SmallDaiCreateHtml',
    meta:{
      title: '生成Html',
    },
    component: SmallDaiCreateHtml
  },
  {
    path:'/manager',
    name: 'SmallDaiManager',
    meta:{
      title: '管理系统',
    },
    component: SmallDaiManager
  },
]

const router = new VueRouter({
  routes
})

export default router
