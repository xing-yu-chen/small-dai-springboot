<template>
  <div id="smalldai_manager">
    <el-row>
      <el-col :span="4">
        <el-menu default-active="2" class="el-menu-vertical-demo">
          <el-submenu :index="index" v-for="(val, index) in menus">
            <template slot="title">
              <i :class="val.iconName"></i>
              <span>{{ val.nameZh }}</span>
            </template>
            <el-menu-item-group>
              <el-menu-item
                :index="index + '-' + i"
                v-for="(value, i) in val.menuTOS"
                @click="change(value)"
              >
                <i :class="value.iconName"></i>
                <span>{{ value.nameZh }}</span>
              </el-menu-item>
            </el-menu-item-group>
          </el-submenu>
        </el-menu>
      </el-col>
      <el-col :span="20">
        <h2 style="text-align: center">{{ tableRemark }}</h2>
        <el-table :data="tableData" border style="width: 100%">
          <el-table-column
            :prop="val.name"
            :label="val.comment"
            :index="index"
            v-for="(val, index) in tableHead"
          >
          </el-table-column>
          <el-table-column fixed="right" label="操作" width="100">
            <template slot-scope="scope">
              <el-button @click="update(scope.row)" type="text" size="small"
                >修改</el-button
              >
              <el-button type="text" size="small">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-col>
    </el-row>

    <el-dialog :title="tableRemark + '修改'" :visible.sync="showUpdate">
      <el-form :model="updateDao">
        <el-form-item :label="val.comment" v-for="(val, index) in tableHead">
          <el-input v-model="updateDao[val.name]" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="showUpdate = false">取 消</el-button>
        <el-button type="primary" @click="updateSend">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>
<script>
import {
  listColumnsBytable,
  listAllData,
  updateData,
} from "../../api/axiosApi";
export default {
  name: "SmallDaiManager",
  data() {
    return {
      menus: [],
      tableData: [],
      tableHead: [],
      tableRemark: "",
      nowPath: "",
      updateDao: {},
      saveDao: {},
      showUpdate: false,
      showSave: false,
    };
  },
  methods: {
    /**
     * 列出所有数据
     */
    listAllData() {
      listAllData(this.nowPath).then((res) => {
        this.tableData = res.data.data;
      });
    },
    /**
     * 修改数据
     */
    updateData() {
      updateData(this.nowPath, this.updateDate).then((res) => {
        this.$message.success(res.data.data);
      });
    },
    /**
     * 点击修改触发事件
     */
    update(row) {
      this.updateDao = row;
      this.showUpdate = true;
    },
    /**
     * 点击修改发送
     */
    updateSend() {
      this.updateData();
      this.showUpdate = false;
    },
    /**
     * 修改数据
     */
    change(data) {
      this.nowPath = data.url;
      let params = {
        tableName: data.nameEn,
      };
      listColumnsBytable(params).then((res) => {
        this.tableRemark = res.data.data.remark;
        this.tableHead = res.data.data.columns;
      });
      this.listAllData();
    },
  },
  created() {
    this.menus = JSON.parse(
      window.localStorage.getItem("userInfo")
    ).roleTO.menuTOS;
    this.nowPath = this.menus[0].menuTOS[1].url;
    // 首次默认拿取第一个遍历的第一个数据
    let params = {
      tableName: this.menus[0].menuTOS[1].nameEn,
    };
    listColumnsBytable(params).then((res) => {
      this.tableRemark = res.data.data.remark;
      this.tableHead = res.data.data.columns;
    });
    this.listAllData();
  },
};
</script>
<style scoped>
#smalldai_manager {
  width: 100%;
  height: 100vh;
}
</style>