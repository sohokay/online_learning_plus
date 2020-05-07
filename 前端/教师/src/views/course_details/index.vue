<template>
  <div class="app-container">
    <el-row :gutter="20">
      <el-col :span="4" style="height: 440px">
        <el-card class="card">
          <div slot="header" class="clearfix">
            <span style="line-height: 28px">课程名称</span>
            <el-button size="mini" style="float: right" @click="toInfo()">查看详情</el-button>
          </div>
          {{courseName}}
        </el-card>
        <el-card shadow="hover" class="card" @click.native="setSignUpData">
          <svg class="icon" aria-hidden="true">
            <use xlink:href="#iconkechengchengji"/>
          </svg>
          <div class="card-text">
            总报名数
            <p>{{SignUpNum}}</p>
          </div>
        </el-card>
        <el-card shadow="hover" class="card" @click.native="setNoticeNum">
          <svg class="icon" aria-hidden="true">
            <use xlink:href="#iconqushixingtai"/>
          </svg>
          <div class="card-text">
            总公告数
            <p>{{NoticeNum}}</p>
          </div>
        </el-card>
        <el-card shadow="hover" class="card" @click.native="setDiscussNum">
          <svg class="icon" aria-hidden="true">
            <use xlink:href="#iconshujuqushi"/>
          </svg>
          <div class="card-text">
            总讨论数
            <p>{{DiscussNum}}</p>
          </div>
        </el-card>
      </el-col>
      <el-col :span="20">
        <el-card>
          <line-chart :chartData="courseChartData"/>
        </el-card>
      </el-col>
    </el-row>
    <el-tabs type="border-card" style="margin-top: 30px">
      <el-tab-pane :lazy="true">
        <span slot="label"><i class="el-icon-date" style="margin-right: 6px"/>公告</span>
        <Notice :course-id="courseId"/>
      </el-tab-pane>
      <el-tab-pane label="课件" :lazy="true">
        <span slot="label"> <i class="el-icon-folder-opened" style="margin-right: 6px"/>课件</span>
        <Courseware :course-id="courseId"/>
      </el-tab-pane>
      <el-tab-pane label="测验" :lazy="true">
        <span slot="label"> <i class="el-icon-success" style="margin-right: 6px"/>测试</span>
        <Test :course-id="courseId"/>
      </el-tab-pane>
      <el-tab-pane label="讨论" :lazy="true">
        <span slot="label"> <i class="el-icon-user-solid" style="margin-right: 6px"/>讨论</span>
        <Discuss :course-id="courseId"/>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
  import LineChart from "@/views/course_details/echarts/LineChart";
  import Notice from "@/views/course_details/Notice";
  import Courseware from "@/views/course_details/Courseware";
  import {initial, getChartData} from '@/api/courseDetail'
  import Discuss from "@/views/course_details/Discuss";
  import Test from "@/views/course_details/Test";

  export default {
    name: "index",
    components: {Test, Discuss, Courseware, Notice, LineChart},
    created() {
      initial(this.courseId).then(res => {
        if (res.code === 201) {
          this.$router.push("/")
        }
        this.courseName = res.data
      })
      getChartData(this.courseId).then(res => {
        this.courseChartData = res.data.signUpNum
        this.signUpNum = res.data.signUpNum
        this.noticeNum = res.data.noticeNum
        this.discussNum = res.data.discussNum
      })
    },
    methods: {
      toInfo() {
        this.$router.push("/course_info/" + this.courseId)
      },
      setSignUpData() {
        this.courseChartData = this.signUpNum;
      },
      setNoticeNum() {
        this.courseChartData = this.noticeNum
      }, setDiscussNum() {
        this.courseChartData = this.discussNum
      },

    },
    computed: {
      SignUpNum: function () {
        let i = 0;
        this.signUpNum.map(s => {
          i = i + s.num
        })
        return i;
      },
      NoticeNum: function () {
        let i = 0;
        this.noticeNum.map(s => {
          i = i + s.num
        })
        return i;
      }, DiscussNum: function () {
        let i = 0;
        this.discussNum.map(s => {
          i = i + s.num
        })
        return i;
      },
    },
    props: ["courseId"],
    data() {
      return {
        signUpNum: [],
        noticeNum: [],
        discussNum: [],
        courseChartData: [],
        courseName: ''
      }
    },

  }
</script>

<style scoped lang="scss">
  .icon {
    width: 50px;
    height: 60px;
    vertical-align: -0.15em;
    fill: currentColor;
    overflow: hidden;
  }

  .card {
    cursor: pointer;
    margin-bottom: 4px;

    .card-text {
      font-family: Helvetica Neue, Helvetica, PingFang SC, Hiragino Sans GB, Microsoft YaHei, Arial, sans-serif;
      margin-top: 10px;
      margin-right: 5px;
      float: right;
      font-size: 14px;
      color: rgba(0, 0, 0, 0.45);
    }

    p {
      color: #666666;
      font-size: 16px;
      font-family: Helvetica Neue, Helvetica, PingFang SC, Hiragino Sans GB, Microsoft YaHei, Arial, sans-serif;
    }

  }
</style>
