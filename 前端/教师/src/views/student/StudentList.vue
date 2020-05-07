<template>
  <div class="app-container">
    <el-row :gutter="20" style="bottom: 10px">
      <el-col :span="4">
        <el-select v-model="courseId" filterable placeholder="请选择">
          <el-option
            v-for="course in  courseList"
            :key="course.courseId"
            :label="course.courseName"
            :value="course.courseId">
          </el-option>
        </el-select>
      </el-col>
      <el-col :span="4" :offset="20">
        <el-input
          v-model="search"
          size="mini"
          placeholder="输入学生名字进行搜索" @input="searchStudent">
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
      <el-table-column align="center" label="学生ID" prop="studentId"/>
      <el-table-column label="学生名字" prop="student.userName" align="center"/>
      <el-table-column label="总学习时间（分钟）"  sortable prop="userActionStatistic.learningTime" align="center"/>
      <el-table-column label="进入课程学习页面次数" sortable prop="userActionStatistic.intoNum" align="center"/>
      <el-table-column label="查看公告次数"  sortable prop="userActionStatistic.noticeNum" align="center"/>
      <el-table-column label="发布帖子数目"  sortable prop="userActionStatistic.postNum" align="center"/>
      <el-table-column label="参与讨论参数"  sortable prop="userActionStatistic.discussNum" align="center"/>
      <el-table-column label="浏览课件次数"  sortable prop="userActionStatistic.coursewareNum" align="center"/>
      <el-table-column label="分析页" align="center">
        <template slot-scope="scope">
          <el-button
            size="mini" @click="toAnalysis(scope.row)">进入
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-pagination style="margin-top: 10px"
                   background
                   layout="prev, pager, next"
                   @current-change="current"
                   :total="total">
    </el-pagination>
  </div>
</template>

<script>
  import {getCourse} from '@/api/course'
  import {getStudentList, getByName} from '@/api/student'

  export default {
    name: "StudentList",
    watch: {
      courseId: function (courseId) {
        this.listLoading = true
        getStudentList(courseId, 1).then(res => {
          this.studentList = res.data.records
          this.total = res.data.total
          this.listLoading = false
        })
      }
    },
    created() {
      getCourse().then(res => {
        this.courseList = res.data
        this.courseId = res.data[0].courseId
      })
    },
    methods: {
      toAnalysis(row) {
        console.log(row)
        this.$router.push({
          path: '/student/analysis', query: {
            courseId: row.courseId,
            actionId: row.userActionStatistic.actionId,
            studentId: row.studentId,
            selectionId:row.selectionId
          }
        })
      },
      searchStudent() {
        if (this.search.trim() !== '') {
          getByName(this.search, this.courseId).then(res => {
            this.studentList = res.data
            this.total = 0
          })
        } else {
          this.listLoading = true
          getStudentList(this.courseId, 1).then(res => {
            this.studentList = res.data.records
            this.total = res.data.total
            this.listLoading = false
          })
        }
      },
      current(number) {
        this.listLoading = true
        getStudentList(this.courseId, number).then(res => {
          this.studentList = res.data.records
          this.total = res.data.total
          this.listLoading = false
        })
      }
    },
    data() {
      return {
        total: 0,
        studentList: [],
        courseId: '',
        search: "",
        listLoading: false,
        courseList: []
      }
    }
  }
</script>

<style scoped>

</style>
