<template>
    <div id="smalldai_create_column">
        <el-row>
            <el-col :span="5">
                    <small-dai-create-side-message></small-dai-create-side-message>
            </el-col>
            <el-col :span="18" :offset="1">
                <br>   
                <br>
                <el-row>
                    <el-col :span="4">
                        <el-button  @click="show = true">批量新增(暂存)</el-button>
                    </el-col>
                    <el-col :span="3">
                        <el-button type="warning" @click="deleteBatch">批量删除</el-button>
                    </el-col>
                    <el-col :span="4">
                        <el-button type="primary" @click="insertAllColumns">提交所有暂存新增</el-button>
                    </el-col>
                    <el-col :span="5">
                        <el-button type="primary" @click="oneShow = true" v-if="columns.length > 0">单条新增</el-button>
                    </el-col>
                </el-row>
                <br>
                <el-card class="box-card">
                    <el-table
                    :data="columns"
                    style="width: 100%"
                    @selection-change="handleSelectionChange">
                        <el-table-column
                        type="selection"
                        width="55">
                        </el-table-column>
                        <el-table-column
                        label="ID"
                        width="100">
                            <template slot-scope="scope">
                                {{scope.row.tcId}}
                            </template>
                        </el-table-column>
                        <el-table-column
                        label="字段名"
                        width="100">
                            <template slot-scope="scope">
                              {{scope.row.name}}
                            </template>
                        </el-table-column>
                         <el-table-column
                        label="备注"
                        width="100">
                            <template slot-scope="scope">
                              {{scope.row.remark}}
                            </template>
                        </el-table-column>
                         <el-table-column
                        label="字段属性"
                        width="100">
                            <template slot-scope="scope">
                              {{scope.row.type}}
                            </template>
                        </el-table-column>
                         <el-table-column
                        label="字段大小"
                        width="100">
                            <template slot-scope="scope">
                              {{scope.row.size}}
                            </template>
                        </el-table-column>
                        <el-table-column
                        label="是否为主键"
                        width="100">
                            <template slot-scope="scope">
                              {{scope.row.primaryKey}}
                            </template>
                        </el-table-column>
                        <el-table-column
                        label="是否非空"
                        width="100">
                            <template slot-scope="scope">
                              {{scope.row.ifNull}}
                            </template>
                        </el-table-column>
                        <el-table-column
                        label="是否自增"
                        width="100">
                            <template slot-scope="scope">
                              {{scope.row.auto}}
                            </template>
                        </el-table-column>
                        <el-table-column
                        label="创建时间"
                        width="100">
                            <template slot-scope="scope">
                              {{scope.row.gmtCreate}}
                            </template>
                        </el-table-column>
                        <el-table-column label="操作" width="200">
                            <template slot-scope="scope">
                                <el-button
                                size="mini"
                                @click="updateColumnNameShow(scope.row)">修改名称</el-button>
                                <br>
                                <el-button 
                                size="mini"
                                @click="updateColumnTypeShow(scope.row)">修改类型</el-button>
                                <br>
                                 <el-button 
                                size="mini"
                                @click="updateColumnRemarkShow(scope.row)">修改备注</el-button>
                                <br>
                                <el-button
                                size="mini"
                                type="danger"
                                @click="removeColumn(scope.row)">&nbsp;&nbsp;&nbsp;删&nbsp;除&nbsp;&nbsp;&nbsp;</el-button>
                            </template>
                        </el-table-column>
                    </el-table>
                    <br>
                    <div>
                        <el-button @click="turnToTable">返回表列表</el-button>
                    </div>
                </el-card>
            </el-col>
        </el-row>
       
       <el-dialog
        title="提示"
        :visible.sync="show"
        width="30%"
        >
           <el-form ref="form" :model="insertColumn" label-width="80px">
               <el-form-item label="字段名">
                    <el-input v-model="insertColumn.name"></el-input>
                </el-form-item>
                <el-form-item label="备注">
                    <el-input v-model="insertColumn.remark"></el-input>
                </el-form-item>
                <el-form-item label="字段属性">
                    <el-select v-model="insertColumn.type" placeholder="请选择字段类型">
                        <el-option  v-for="(type,index) in typeArr" :key="index"  :label="type" :value="type"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="字段大小">
                    <el-input v-model="insertColumn.size"></el-input>
                </el-form-item>
                <el-form-item label="是否主键">
                    <el-switch inactive-value="false" active-value="true" v-model="insertColumn.primaryKey"></el-switch>
                </el-form-item>
                <el-form-item label="是否非空">
                    <el-switch  inactive-value="false" active-value="true" v-model="insertColumn.ifNull"></el-switch>
                </el-form-item>
                <el-form-item label="是否自增">
                    <el-switch inactive-value="false" active-value="true" v-model="insertColumn.auto"></el-switch>
                </el-form-item>
           </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="show = false">取 消</el-button>
                <el-button type="primary" @click="insertColumnMethod">确 定</el-button>
            </span>
        </el-dialog>

        <el-dialog
        title="提示"
        :visible.sync="oneShow"
        width="30%"
        >
           <el-form ref="form" :model="column" label-width="80px">
               <el-form-item label="字段名">
                    <el-input v-model="column.name"></el-input>
                </el-form-item>
                <el-form-item label="备注">
                    <el-input v-model="column.remark"></el-input>
                </el-form-item>
                <el-form-item label="字段属性">
                    <el-select v-model="column.type" placeholder="请选择字段类型">
                        <el-option  v-for="(type,index) in typeArr" :key="index"  :label="type" :value="type"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="字段大小">
                    <el-input v-model="column.size"></el-input>
                </el-form-item>
                <el-form-item label="是否主键">
                    <el-switch inactive-value="false" active-value="true" v-model="column.primaryKey"></el-switch>
                </el-form-item>
                <el-form-item label="是否非空">
                    <el-switch  inactive-value="false" active-value="true" v-model="column.ifNull"></el-switch>
                </el-form-item>
                <el-form-item label="是否自增">
                    <el-switch inactive-value="false" active-value="true" v-model="column.auto"></el-switch>
                </el-form-item>
           </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="oneShow = false">取 消</el-button>
                <el-button type="primary" @click="saveColumn">确 定</el-button>
            </span>
        </el-dialog>
        <el-dialog
        title="提示"
        :visible.sync="updateShow1"
        width="30%"
        >
           <el-form ref="form" :model="updateColumn1" label-width="80px">
               <el-form-item label="字段名">
                    <el-input v-model="updateColumn1.name"></el-input>
                </el-form-item>
                <el-form-item label="备注">
                    <el-input v-model="updateColumn1.remark" disabled></el-input>
                </el-form-item>
                <el-form-item label="字段属性">
                    <el-select v-model="updateColumn1.type" placeholder="请选择字段类型" disabled>
                        <el-option  v-for="(type,index) in typeArr" :key="index"  :label="type" :value="type" ></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="字段大小">
                    <el-input v-model="updateColumn1.size" disabled></el-input>
                </el-form-item>
                <el-form-item label="是否主键">
                    <el-switch inactive-value="false" active-value="true" v-model="updateColumn1.primaryKey" disabled></el-switch>
                </el-form-item>
                <el-form-item label="是否非空">
                    <el-switch  inactive-value="false" active-value="true" v-model="updateColumn1.ifNull" disabled></el-switch>
                </el-form-item>
                <el-form-item label="是否自增">
                    <el-switch inactive-value="false" active-value="true" v-model="updateColumn1.auto" disabled></el-switch>
                </el-form-item>
           </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="updateShow1 = false">取 消</el-button>
                <el-button type="primary" @click="updateColumnName">确 定</el-button>
            </span>
        </el-dialog>
        <el-dialog
        title="提示"
        :visible.sync="updateShow2"
        width="30%"
        >
           <el-form ref="form" :model="updateColumn2" label-width="80px">
               <el-form-item label="字段名">
                    <el-input v-model="updateColumn2.name" disabled></el-input>
                </el-form-item>
                <el-form-item label="备注">
                    <el-input v-model="updateColumn2.remark" disabled></el-input>
                </el-form-item>
                <el-form-item label="字段属性">
                    <el-select v-model="updateColumn2.type" placeholder="请选择字段类型" >
                        <el-option  v-for="(type,index) in typeArr" :key="index"  :label="type" :value="type" ></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="字段大小">
                    <el-input v-model="updateColumn2.size" ></el-input>
                </el-form-item>
                <el-form-item label="是否主键">
                    <el-switch inactive-value="false" active-value="true" v-model="updateColumn2.primaryKey" disabled></el-switch>
                </el-form-item>
                <el-form-item label="是否非空">
                    <el-switch  inactive-value="false" active-value="true" v-model="updateColumn2.ifNull" disabled></el-switch>
                </el-form-item>
                <el-form-item label="是否自增">
                    <el-switch inactive-value="false" active-value="true" v-model="updateColumn2.auto" disabled></el-switch>
                </el-form-item>
           </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="updateShow2 = false">取 消</el-button>
                <el-button type="primary" @click="updateColumnType">确 定</el-button>
            </span>
        </el-dialog>
        <el-dialog
        title="提示"
        :visible.sync="updateShow3"
        width="30%"
        >
           <el-form ref="form" :model="updateColumn3" label-width="80px">
               <el-form-item label="字段名">
                    <el-input v-model="updateColumn3.name" disabled></el-input>
                </el-form-item>
                <el-form-item label="备注">
                    <el-input v-model="updateColumn3.remark" ></el-input>
                </el-form-item>
                <el-form-item label="字段属性">
                    <el-select v-model="updateColumn3.type" placeholder="请选择字段类型" disabled>
                        <el-option  v-for="(type,index) in typeArr" :key="index"  :label="type" :value="type" ></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="字段大小">
                    <el-input v-model="updateColumn3.size" disabled></el-input>
                </el-form-item>
                <el-form-item label="是否主键">
                    <el-switch inactive-value="false" active-value="true" v-model="updateColumn3.primaryKey" disabled></el-switch>
                </el-form-item>
                <el-form-item label="是否非空">
                    <el-switch  inactive-value="false" active-value="true" v-model="updateColumn3.ifNull" disabled></el-switch>
                </el-form-item>
                <el-form-item label="是否自增">
                    <el-switch inactive-value="false" active-value="true" v-model="updateColumn3.auto" disabled></el-switch>
                </el-form-item>
           </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="updateShow3 = false">取 消</el-button>
                <el-button type="primary" @click="updateColumnRemark">确 定</el-button>
            </span>
        </el-dialog>

    </div>
</template>
<script>
import {listAllColumnsBytId, insertAllColumns, newColumn, deletedColumn, updateColumnName, updateColumnType, updateColumnRemark} from '../api/axiosApi'
import SmallDaiCreateSideMessage from '../components/create/CreateSideMessage.vue'
export default {
    name: 'SmallDaiCreateColumn',
    components:{
        SmallDaiCreateSideMessage
    },
    data(){
        return{
            tId: 0,
            uId: 0,
            columns: [],
            insertColumns: [],
            insertColumn:{
                name: '',
                remark: '',
                type: '',
                tId: this.tId,
                uId: this.uId,
                primaryKey: false,
                ifNull: false,
                auto: false
            },
            show: false,
            oneShow: false,
            updateShow1: false,
            updateShow2: false,
            updateShow3: false,
            typeArr:[
                'tinyint',
                'int',
                'bigint',
                'float',
                'double',
                'decimal',
                'char',
                'varchar',
                'text',
                'longtext',
                'datetime'
            ],
            column:{},
            updateColumn1:{},
            updateColumn2:{},
            updateColumn3:{},
            deleteColumns: []
        }
    },
    methods:{
        /**
         * 获取tId
         */
        getId(){
            this.tId = this.$route.query.tId
            this.uId = JSON.parse(window.localStorage.getItem('userInfo')).uId
            this.listAllColumnsBytId()
        },
        /**
         * 获取该表下的所有字段
         */
        listAllColumnsBytId(){
            let data = {
                tId: this.tId
            }
            listAllColumnsBytId(data).then(res=>{
                this.columns = res.data.data
            })
        },
        /**
         * 新增字段
         */
        insertColumnMethod(){
            this.insertColumn.uId = this.uId
            this.insertColumn.tId = this.tId

            let data = this.insertColumn
            this.insertColumns.push(data)
            this.columns.push(data)
            this.insertColumn = {}
            this.show = false
        },
        /**
         * 提交所有暂存字段
         */
        insertAllColumns(){
            insertAllColumns(this.insertColumns).then(res=>{
                this.$message.success(res.data.data)
            })
        },
        /**
         * 仅新增一条字段
         */
        saveColumn(){
            this.column.uId = this.uId
            this.column.tId = this.tId
             this.oneShow = false;
             console.log(this.column)
            newColumn(this.column).then(res=>{
                this.$message.success(res.data.data)
                window.location.reload()
            })
        },
        /**
         * 跳转回表创建
         */
        turnToTable(){
            this.$router.push('/listTables')
        },
        /** 
         * 删除某一个对象
        */
       removeColumn(row){
           deletedColumn(row).then(res=>{
               this.$message.success(res.data.data)
           })
       },
       /**
        * 修改某一对象名称界面显示
        */
       updateColumnNameShow(row){
           this.updateColumn1 = row
           this.updateShow1 = true
       },
        updateColumnName(){
            updateColumnName(this.updateColumn1).then(res=>{
                this.updateShow1 = false
                this.$message.success(res.data.data)
            })
        },
        /**
         * 修改某一对象类型界面显示
         */
        updateColumnTypeShow(row){
            this.updateColumn2 = row
            this.updateShow2 = true
        },
        updateColumnType(){
            updateColumnType(this.updateColumn2).then(res=>{
                this.updateShow2 = false
                this.$message.success(res.data.data)
            })
        },
        /** 
         * 修改某一对象备注界面显示
        */
       updateColumnRemarkShow(row){
           this.updateColumn3 = row
           this.updateShow3 = true
       },
       updateColumnRemark(){
           updateColumnRemark(this.updateColumn3).then(res=>{
               this.updateShow3 = false
                this.$message.success(res.data.data)
           })
       },
       /** 
        * 批量删除
       */
      handleSelectionChange(val){
          this.deleteColumns = val
      },
      /**
       * 批量删除数据
       */
      deleteBatch(){
          
      }
    },
    created(){
        this.getId()
    }
}
</script>
<style scoped>
#smalldai_create_column{
    width: 100%;
    height: 100vh;
}
</style>