<template>
    <div id="smalldai_create_center_project">
        <el-card class="box-card">
                    <h1 style="text-align:center">
                        项目创建<span class="h1_span">创建表映射XML</span>
                    </h1>
                    <p style="font-size:8px;text-align:center">对哪个表进行联表操作就把哪个表写在前面。</p>
                    <el-form label-position="right" label-width="100px">
                        <div style="border: 1px dashed #000;">
                            <h3 style="text-indent:2em">联表查询ID</h3>
                            <el-form-item label="SELECT">
                                <el-input v-model="XmlVO.sqlArgs"></el-input>
                            </el-form-item>
                            <el-form-item label="FROM">
                                <el-input v-model="XmlVO.sqlFrom"></el-input>
                            </el-form-item>
                            <el-form-item label="WHERE">
                                <el-input v-model="XmlVO.sqlEqual"></el-input>
                            </el-form-item>
                            <el-form-item label="AND">
                                <el-input v-model="XmlVO.sqlIdOne"></el-input>
                            </el-form-item>
                        </div>
                        <br>
                        <div style="border: 1px dashed #000;">
                            <h3 style="text-indent:2em">联表查询列表</h3>
                            <el-form-item label="SELECT">
                                <el-input v-model="XmlVO.sqlArgs"></el-input>
                            </el-form-item>
                            <el-form-item label="FROM">
                                <el-input v-model="XmlVO.sqlFrom"></el-input>
                            </el-form-item>
                            <el-form-item label="WHERE">
                                <el-input v-model="XmlVO.sqlEqual"></el-input>
                            </el-form-item>
                        </div>
                        <br>
                        <div style="border: 1px dashed #000;">
                            <h3 style="text-indent:2em">联表删除ID</h3>
                            <el-form-item label="DELETE">
                                <el-input v-model="XmlVO.sqlFrom"></el-input>
                            </el-form-item>
                            <el-form-item label="FROM">
                                <el-input v-model="XmlVO.sqlFrom"></el-input>
                            </el-form-item>
                            <el-form-item label="WHERE">
                                <el-input v-model="XmlVO.sqlEqual"></el-input>
                            </el-form-item>
                            <el-form-item label="AND">
                                <el-input v-model="XmlVO.sqlIdThree"></el-input>
                            </el-form-item>
                        </div>
                    </el-form>
                    <div style="text-align:center">
                        <el-popover
                            placement="top-start"
                            title="警告"
                            width="200"
                            trigger="hover"
                            content="请您确认您的信息已经填写无误，如有异常您的项目会创建失败，请认真阅读左侧项目创建帮助内容。">
                            <el-button slot="reference" @click="createXml">创建XML</el-button>
                        </el-popover>
                    </div>
                </el-card>
    </div>
</template>
<script>
import {createXml} from '../../api/axiosApi'
export default {
    name: 'SmallDaiCreateCenterBanner',
    data(){
        return{
            XmlVO: {
                sqlArgs: '',
                sqlFrom: '',
                sqlEqual: '',
                sqlIdOne: '',
                sqlIdThree: '',
                tId: 0,
            }
        }
    },
    methods:{
        /**
         * 创建XML
         */
        createXml(){
            createXml(this.XmlVO).then(res=>{
                this.$message.success(res.data.data)
                this.$router.push('/listTables')
            })
        }
    },
    created(){
      this.XmlVO.tId = this.$route.query.tId
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