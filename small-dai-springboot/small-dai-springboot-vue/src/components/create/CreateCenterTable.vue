<template>
    <div id="smalldai_create_center_table">
        <el-card class="box-card">
                    <h1 style="text-align:center">
                        项目创建<span class="h1_span">创建自己需要的数据表</span>
                    </h1>
                    <el-form label-position="right" label-width="100px">
                        <el-form-item label="表名">
                            <el-input v-model="tableVO.name"></el-input>
                            <p class="detail_p">*前缀默认“t_”+表名，“t_”已后端处理帮助生成</p>
                        </el-form-item>
                        <el-form-item label="备注">
                            <el-input v-model="tableVO.remark"></el-input>
                        </el-form-item>
                    </el-form>
                    <div style="text-align:center">
                        <el-popover
                            placement="top-start"
                            title="警告"
                            width="200"
                            trigger="hover"
                            content="请您确认您的信息已经填写无误，如有异常您的项目会创建失败，请认真阅读左侧项目创建帮助内容。">
                            <el-button slot="reference" @click="createTable">创建项目</el-button>
                        </el-popover>
                    </div>
                </el-card>
    </div>
</template>
<script>
import {createTableDB} from '../../api/axiosApi'
export default {
    name: 'SmallDaiCreateCenterProject',
    data(){
        return{
            tableVO: {
               name: '',
               remark: '',
               dId: 0,
               uId: 0
            }
        }
    },
    methods:{
        /**
         * 创建项目
         */
        createTable(){
            createTableDB(this.tableVO).then(res=>{
                // 成功
                if(res.data.code === 200){
                     this.$confirm(res.data.msg + '是否完成所有表的创建，如果没有请点击取消，如果已经完成请点击确认前往创建数据库字段').then(() => {
                         this.$router.push('/listTables')
                    })
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
        this.tableVO.uId =  JSON.parse(window.localStorage.getItem('userInfo')).uId
        this.tableVO.dId = JSON.parse(window.localStorage.getItem('database')).dId
    }
}
</script>
<style scoped>
#smalldai_create_center_table{
    width: 100%;
    height: 95vh;
    margin: 0 auto;
}
.h1_span{
    font-size: 18px;
    font-weight: 100;
    color: rgb(43, 43, 44);
}
.detail_p{
    font-size: 13px;
    margin: 0;
    padding: 0;
    color: rgb(51, 49, 48);
}
</style>