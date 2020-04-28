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
      :data="studentList"
      element-loading-text="Loading"
      border
      fit
      highlight-current-row
    >
      <el-table-column
        type="selection"
        width="55" align="center">
      </el-table-column>
      <el-table-column align="center" label="学生ID" prop="studentId"/>
      <el-table-column label="学生名字" prop="name" align="studentName"/>
      <el-table-column label="拼音" align="center" prop="pinyin"/>
      <el-table-column label="是否热门" align="center" prop="isHot"/>
      <!--      <el-table-column label="操作" align="center">-->
      <!--        <template slot-scope="scope">-->
      <!--          <el-button-->
      <!--            size="mini"-->
      <!--            @click="editCity(scope.$index, scope.row) ">编辑-->
      <!--          </el-button>-->
      <!--          <el-button-->
      <!--            size="mini"-->
      <!--            type="danger"-->
      <!--            @click="handleDelete(scope.$index, scope.row)">删除-->
      <!--          </el-button>-->
      <!--        </template>-->
      <!--      </el-table-column>-->
    </el-table>
  </div>
</template>

<script>
  export default {
    name: "StudentList",
    data() {
      return {
        studentList: [


        ]
      }
    }
  }
</script>

<style scoped>

</style>
