<template>
    <div id="center_index">
        <el-row>
            <el-col :md="12">
                <el-collapse v-model="activeName" accordion>
                    <el-collapse-item :title="msg.title" :name="msg.id" v-for="msg in msgList">
                        <div>{{msg.content}}</div>
                    </el-collapse-item>
                </el-collapse>
            </el-col>
            <el-col :md="3" :offset="2">
                <el-steps direction="vertical" :active="1" style="height: 200px;">
                    <el-step :title="activity" v-for="activity in activities"></el-step>
                </el-steps>
            </el-col>
            <el-col :md="7" class="experience_go">
                <div class="experience_go_button">
                    <p>此处我们提供了免费的在线生成平台提供体验</p>
                    <el-button type="primary" plain >免费使用</el-button>
                    <br>
                    <br>
                    <el-button  plain>个性定制</el-button>
                </div>
            </el-col>
        </el-row>
    </div>
</template>
<script>
export default {
    name: 'SmallDaiCenterIndex',
    data(){
        return{
            activeName: '1',
            msgList: [
                {id: '1', title: '搭建，轻而易举', content: '创建所需数据表，简单几步实现系统逻辑'},
                {id: '2', title: '工作，得心应手', content: '自定义生成模型，避免时间无效浪费'},
                {id: '3', title: '增长，敏捷迭代', content: '随时修改，随时发布，敏捷开发'}
            ],
            activities: [
               '登录',
               '进入控制台',
               '按提示创建数据',
               '个性化界面',
               '下载项目源文件'
            ]
        }
    }
}
</script>
<style scoped>
#center_index{
    width: 100%;
    height: 40vh;
}
.experience_go{
    position: relative;
    text-align: center;
}
.experience_go_button{
    position: absolute;
    top: 30px;
}
</style>