<template>
    <div id="smalldai_list_center_tables">
        <el-card class="box-card">
            <el-table
                :data="tableData"
                border
                style="width: 100%">
                <el-table-column
                fixed
                prop="tId"
                label="ID"
                width="150">
                </el-table-column>
                <el-table-column
                prop="name"
                label="表名"
                width="120">
                </el-table-column>
                <el-table-column
                prop="remark"
                label="备注"
                width="120">
                </el-table-column>
                <el-table-column
                prop="gmtCreate"
                label="创建时间"
                width="120">
                </el-table-column>
                <el-table-column
                fixed="right"
                label="操作"
                width="100">
                <template slot-scope="scope">
                    <el-button @click="handleClick(scope.row)" type="text" size="small">操作字段</el-button>
                    <br>
                    <el-button @click="updateTableRemarkShow(scope.row)" type="text" size="small">修改备注</el-button>
                    <br>
                    <el-button @click="updateTableNameShow(scope.row)" type="text" size="small">修改表名</el-button>
                    <br>
                    <el-button @click="turnToMapper(scope.row)" type="text" size="small" v-if="tableData.length > 1">功能需求</el-button>
                </template>
                </el-table-column>
            </el-table>
        </el-card>
        <el-dialog
        title="提示"
        :visible.sync="showRemark"
        width="30%"
        >
           <el-form ref="form" :model="tableRemark" label-width="80px">
               <el-form-item label="ID">
                    <el-input v-model="tableRemark.tId" disabled></el-input>
                </el-form-item>
               <el-form-item label="表名">
                    <el-input v-model="tableRemark.name" disabled></el-input>
                </el-form-item>
                <el-form-item label="备注">
                    <el-input v-model="tableRemark.remark" ></el-input>
                </el-form-item>
                <el-form-item label="创建时间">
                    <el-input v-model="tableRemark.gmtCreate" disabled></el-input>
                </el-form-item>
           </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="showRemark = false">取 消</el-button>
                <el-button type="primary" @click="updateTableRemark">确 定</el-button>
            </span>
        </el-dialog>
        <el-dialog
        title="提示"
        :visible.sync="showName"
        width="30%"
        >
           <el-form ref="form" :model="tableName" label-width="80px">
               <el-form-item label="ID">
                    <el-input v-model="tableName.tId" disabled></el-input>
                </el-form-item>
               <el-form-item label="表名">
                    <el-input v-model="tableName.name"></el-input>
                </el-form-item>
                <el-form-item label="备注">
                    <el-input v-model="tableName.remark" disabled></el-input>
                </el-form-item>
                <el-form-item label="创建时间">
                    <el-input v-model="tableName.gmtCreate" disabled></el-input>
                </el-form-item>
           </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="showName = false">取 消</el-button>
                <el-button type="primary" @click="updateTableName">确 定</el-button>
            </span>
        </el-dialog>
    </div>
</template>
<script>
import {listAllTables, updateTableRemark, updateTableName} from '../../api/axiosApi'
export default {
    name: 'SmallDaiListCenterTables',
    data(){
        return{
            tableData: [{
                tId: 1,
                name: '',
                remark: '',
                gmtCreate: '',
            }],
            dId: 0,
            showRemark: false,
            showName: false,
            tableRemark:{},
            tableName:{}
        }
    },
    methods:{
        /** 
         * 列出所有的表
        */
       listTables(){
           let data = {
               dId: JSON.parse(window.localStorage.getItem('database')).dId
           }
           listAllTables(data).then(res=>{
               this.tableData = res.data.data
           })
       },
       /**
        * 对每个数据进行操作
        */
       handleClick(row){
           this.$router.push({
               path: "/createColumns",
               query:{
                   tId: row.tId
               }
           })
       },
       /**
        * 跳转到Mapper页
        */
       turnToMapper(row){
           this.$router.push({
               path: '/createMapper',
               query:{
                   tId: row.tId
               }
           })
       },
       /**
        * 修改表备注
        */
       updateTableRemarkShow(row){
           this.showRemark = true
           this.tableRemark = row
       },
       updateTableRemark(){
           updateTableRemark(this.tableRemark).then(res=>{
               this.$message.success(res.data.data)
               this.showRemark = false
           })
       },
       /**
        * 修改表名
        */
       updateTableNameShow(row){
           this.showName = true
           this.tableName = row
       },
       updateTableName(){
           updateTableName(this.tableName).then(res=>{
               this.$message.success(res.data.data)
               this.showName = false
           })
       }
    },
    created(){
        this.listTables()
    }
}
</script>
<style scoped>
#smalldai_list_center_tables{
    width: 100%;
    height: 100vh;
}
</style>