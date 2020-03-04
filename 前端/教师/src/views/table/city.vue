<template>
  <div class="app-container">
    <el-row :gutter="20" style="bottom: 10px">
      <el-col :span="4" :offset="20">
        <el-input
          v-model="search"
          size="mini"
          placeholder="输入关键字搜索" @input="searchCity">
          <el-button slot="append" icon="el-icon-search"/>
        </el-input>
      </el-col>
    </el-row>

    <el-table
      v-loading="listLoading"
      ref="multipleTable"
      :data="list"
      element-loading-text="Loading"
      border
      fit
      highlight-current-row
    >
      <el-table-column
        type="selection"
        width="55" align="center">
      </el-table-column>
      <el-table-column align="center" label="城市ID" prop="cityId"/>
      <el-table-column label="城市名称" prop="name" align="center"/>

      <el-table-column label="拼音" align="center" prop="pinyin"/>

      <el-table-column label="是否热门" align="center" prop="isHot"/>

      <el-table-column label="操作" align="center">
        <template slot-scope="scope">
          <el-button
            size="mini"
            @click="editCity(scope.$index, scope.row) ">编辑
          </el-button>
          <el-button
            size="mini"
            type="danger"
            @click="handleDelete(scope.$index, scope.row)">删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-row :gutter="20" style="top: 10px">
      <el-col :span="6">
        <el-button type="danger" @click="deleteCityList">删除所选<i class="el-icon-delete el-icon--right"/></el-button>
        <el-button type="primary" @click="releaseCity">发布新城市<i class="el-icon-upload2 el-icon--right"/></el-button>
      </el-col>
      <el-col :span="6" :offset="10">
        <el-pagination
          v-if="showPage"
          background
          @current-change="currentChange"
          layout="prev, pager, next"
          :total="total">
        </el-pagination>
      </el-col>
    </el-row>
    <!--弹出的表单-->
    <el-dialog title="编辑城市" :visible.sync="dialogFormVisible">
      <el-form :model="form">
        <el-form-item label="城市ID">
          <el-input v-model="form.cityId" autocomplete="off" :disabled="form.isDisable"/>
        </el-form-item>
        <el-form-item label="城市名称">
          <el-input v-model="form.name" autocomplete="off"/>
        </el-form-item>
        <el-form-item label="城市拼音">
          <el-input v-model="form.pinyin" autocomplete="off"/>
        </el-form-item>
        <el-form-item label="是否热门">
          <el-select v-model="form.isHot" placeholder="请选择是否热门">
            <el-option label="热门" value="1"/>
            <el-option label="非热门" value="0"/>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="addCity">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import {getCityList, getCity, deleteCity, deleteCityList,postCity} from '../../api/city'

  export default {
    filters: {
      statusFilter(status) {
        const statusMap = {
          published: 'success',
          draft: 'gray',
          deleted: 'danger'
        }
        return statusMap[status]
      }
    },
    data() {
      return {
        // 城市表格数据列表
        list: null,
        //是否显示加载样式
        listLoading: true,
        //页总数
        total: null,
        //表单是否弹出
        dialogFormVisible: false,
        //搜索框内容
        search: '',
        //表单数据
        form: {
          cityId: '',
          name: '',
          pinyin: '',
          isHot: '',
        },
        //是否禁用cityId输入框
        isDisable: false,
        //是否分页显示
        showPage: true
      }
    },
    created() {
      //初始化数据 页数定位1
      this.fetchDataList(1)
    },
    methods: {
      /**
       * 获取城市列表
       * @param val 页数
       */
      fetchDataList(val) {
        this.listLoading = true
        getCityList({num: val}).then(response => {
          this.list = response.data.records
          this.total = response.data.total
          this.listLoading = false
        })
      },
      handleEdit(index, row) {
        console.log(city, row);
      },
      handleDelete(index, row) {
        deleteCity(row)
      },
      /**
       * 页数改变时 重新从后端获取数据
       * @param val
       */
      currentChange(val) {
        this.fetchDataList(val)
      },
      searchCity() {
        if (this.search.trim() !== '') {
          getCity({name: this.search.trim()}).then(res => {
            this.list = res.data
            this.showPage = false
          });
        } else {
          this.showPage = true
          this.fetchDataList(1)
        }
      },
      /**
       * 删除城市列表
       */
      deleteCityList() {
        deleteCityList(this.$refs.multipleTable.selection)
      },
      /**
       * 编辑修改数据
       */
      editCity(index,data){
        this.form=data
        this.list[city]=data
        this.dialogFormVisible = true
        this.form.isDisable=true
      },
      /**
       * 发布新城市
       */
      releaseCity(){
        this.dialogFormVisible = true
      },
      /**
       * 后台修改/增加数据
       */
      addCity(){
        postCity(this.form)
      }

    }
  }
</script>
