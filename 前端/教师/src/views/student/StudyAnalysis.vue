<template>
  <div class="app-container">
    <el-row :gutter="20" style="margin-bottom: 20px">
      <el-col :span="4">
        <el-card>
          <div slot="header" class="clearfix">
            <span>学生姓名</span>
          </div>
          {{userName}}
        </el-card>
      </el-col>
      <el-col :span="4">
        <el-card>
          <div slot="header" class="clearfix">
            <span>学生ID</span>
          </div>
          {{studentId}}
        </el-card>
      </el-col>
      <el-col :span="4">
        <el-card>
          <div slot="header" class="clearfix">
            <span>当前课程</span>
          </div>
          {{courseName}}
        </el-card>
      </el-col>
      <el-col :span="4">
        <el-card>
          <div slot="header" class="clearfix">
            <span>学习时间min</span>
          </div>
          {{learningTime}}
        </el-card>
      </el-col>
      <el-col :span="4">
        <el-card>
          <div slot="header" class="clearfix">
            <span>系统智能分析学生成绩（满10）</span>
          </div>
          功能暂缓开通
        </el-card>
      </el-col>
      <el-col :span="4">
        <el-card>
          <div slot="header" class="clearfix">
            <span>自动评级</span>
          </div>
          功能暂缓开通
        </el-card>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="6" :offset="6">
        <PieChart :chart-data="PieChart"/>
      </el-col>
      <el-col :span="6">
        <APieChart :chart-data="AveragePie"/>
      </el-col>
      <el-col :span="24">
        <column-chart :chart-data="columnChart"/>
      </el-col>
    </el-row>
    <el-table
      :data="tableData"
      style="width: 100%"
      :default-sort="{prop: 'date', order: 'descending'}"
    >
      <el-table-column
        prop="evaluateId"
        label="评价ID"
        sortable
        width="90">
      </el-table-column>
      <el-table-column
        prop="date"
        label="日期"
        sortable
        width="200">
      </el-table-column>
      <el-table-column
        prop="title"
        label="标题"
        width="300">
      </el-table-column>
      <el-table-column
        prop="brief"
        label="简评"

      >
      </el-table-column>
      <el-table-column
        prop="grade"
        label="等级"

        width="90">
      </el-table-column>
    </el-table>
    <el-row>
      <el-col :offset="20" style="margin-top: 10px">
        <el-button @click=" dialogFormVisible=true">
          向学生发送评价
        </el-button>
      </el-col>
    </el-row>
    <el-dialog title="向学生发送评价" :visible.sync="dialogFormVisible">
      <el-form :model="evaluateForm" ref="form">
        <el-form-item label="标题" required prop="title">
          <el-input v-model=" evaluateForm.title" autocomplete="off"/>
        </el-form-item>
        <el-form-item label="简评" required prop="brief">
          <el-input v-model=" evaluateForm.brief" autocomplete="off"/>
        </el-form-item>
        <el-form-item label="等级" required prop="grade">
          <el-select v-model="evaluateForm.grade" placeholder="请选择等级">
            <el-option label="优" value="优"/>
            <el-option label="良" value="良"/>
            <el-option label="中" value="中"/>
            <el-option label="合格" value="合格"/>
            <el-option label="不合格" value="不合格"/>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="addAnalysis">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import PieChart from "@/views/student/Chart/PieChart";
  import ColumnChart from "@/views/student/Chart/ColumnChart";
  import APieChart from "@/views/student/Chart/APieChart";
  import {getData, getStudentEvaluate, postEvaluate} from '@/api/analysis'

  export default {
    name: "StudyAnalysis",
    components: {ColumnChart, PieChart, APieChart},
    created() {
      this.actionId = this.$route.query.actionId
      this.courseId = this.$route.query.courseId
      this.studentId = this.$route.query.studentId
      this.evaluateForm.selectionId = this.$route.query.selectionId
      getData(this.courseId, this.studentId, this.actionId).then(res => {
        this.columnChart.AverageScore = res.data.AverageScore
        this.columnChart.StudentScore = res.data.StudentScore
        this.PieChart = res.data.LearningBehavior
        this.AveragePie = res.data.AverageLearningBehavior
        this.userName = res.data.studentUser.userName
        this.courseName = res.data.courseName.courseName
        this.learningTime = res.data.LearningBehavior.learningTime
        getStudentEvaluate(this.courseName).then(res => {
          this.tableData = res.data
        })
      })
    },
    data() {
      return {
        evaluateForm: {
          studentId: '',
          selectionId: '',
          courseName: '',
          brief: '',
          title: '',
          grade: ''
        },
        dialogFormVisible: false,
        actionId: '',
        courseId: '',
        studentId: '',
        columnChart: {
          AverageScore: '',
          StudentScore: ''
        },
        PieChart: '',
        AveragePie: '',
        userName: '',
        courseName: '',
        learningTime: '',
        tableData: [{
          evaluateId: 'fd ',
          date: 'fd ',
          title: 'sdf ',
          evaluate: 'gfd  '
        }]
      }
    },
    methods: {
      addAnalysis() {
        this.$refs.form.validate((valid) => {
          if (valid) {
            this.evaluateForm.studentId = this.studentId
            this.evaluateForm.courseName = this.courseName
            postEvaluate(this.evaluateForm).then(res => {
              this.$refs.form.resetFields()
              this.dialogFormVisible = false
              getStudentEvaluate(this.courseName).then(res => {
                this.tableData = res.data
              })
            })

          } else {
            return
          }
        })
      }
    }
  }
</script>

<style scoped>

</style>
