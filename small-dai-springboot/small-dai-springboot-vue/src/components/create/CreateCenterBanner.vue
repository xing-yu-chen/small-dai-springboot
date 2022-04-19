<template>
    <div id="smalldai_create_center_project">
        <el-card class="box-card">
                    <h1 style="text-align:center">
                        项目创建<span class="h1_span">创建自己的个人标识</span>
                    </h1>
                    <el-form label-position="right" label-width="100px">
                        <el-form-item label="作者">
                            <el-input v-model="bannerVO.author"></el-input>
                        </el-form-item>
                        <el-form-item label="创建时间">
                            <el-date-picker
                                v-model="bannerVO.createTime"
                                type="date"
                                placeholder="选择日期">
                            </el-date-picker>
                        </el-form-item>
                    </el-form>
                    <div style="text-align:center">
                        <el-popover
                            placement="top-start"
                            title="警告"
                            width="200"
                            trigger="hover"
                            content="请您确认您的信息已经填写无误，如有异常您的项目会创建失败，请认真阅读左侧项目创建帮助内容。">
                            <el-button slot="reference" @click="createBanner">创建Banner</el-button>
                        </el-popover>
                    </div>
                </el-card>
    </div>
</template>
<script>
import {createBanner} from '../../api/axiosApi'
export default {
    name: 'SmallDaiCreateCenterBanner',
    data(){
        return{
            bannerVO: {
                author: 'small-dai',
                createTime: '',
                dId: 0,
            }
        }
    },
    methods:{
        createBanner(){
            createBanner(this.bannerVO).then(res=>{
                if(res.data.code === 200){
                    this.$message({
                        type: 'success',
                        message: res.data.data
                    });
                    this.$router.push('/createYml')
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
        this.bannerVO.dId =  JSON.parse(window.localStorage.getItem('database')).dId
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