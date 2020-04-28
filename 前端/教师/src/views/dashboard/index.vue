<template>
  <div class="dashboard-container">
    <el-row :gutter="32">
      <el-col :span="12">
        <el-card shadow="hover" class="card-plane" @click.native="courseLine()">
          <svg class="icon" aria-hidden="true">
            <use xlink:href="#iconkechengxingshi"/>
          </svg>
          <div class="card-text">
            发布课程总数
            <p>{{courseNum}}</p>
          </div>
        </el-card>
      </el-col>
      <el-col :span="12" class="card-plane">
        <el-card shadow="hover" @click.native="selectLine()">
          <svg class="icon" aria-hidden="true">
            <use xlink:href="#iconkechengchengji"/>
          </svg>
          <div class="card-text">
            报名总人数
            <p>{{selectNum}}</p>
          </div>
        </el-card>
      </el-col>
    </el-row>
    <el-card style="margin-top: 20px">
      <line-chart :chartData="dataChart"/>
    </el-card>
  </div>
</template>
<script>
  import LineChart from "@/views/dashboard/echarts/LineChart";
  import {getCourseTimeChart, getSelectTimeChart} from '@/api/dashboard'

  export default {
    name: 'Dashboard',
    components: {LineChart},
    created() {
      getCourseTimeChart().then(res => {
        this.courseChartData = res.data
        this.dataChart=this.courseChartData;
      })
      getSelectTimeChart().then(res => {
        this.selectChartData = res.data
      })
    },
    computed: {
      courseNum: function () {
        let i = 0;
        this.courseChartData.map(x => {
          i = i + x.num
        })
        return i;
      },
      selectNum: function () {
        let i = 0;
        this.selectChartData.map(x => {
          i = i + x.num
        })
        return i;
      },
    },
    data() {
      return {
        dataChart:[],
        courseChartData: [],
        selectChartData: [],
      }
    },
    methods: {
      selectLine() {
      this.dataChart=this.selectChartData

      },
      courseLine() {
        this.dataChart=this.courseChartData
      }

    }
  }
</script>
<style lang="scss" scoped>
  .icon {
    width: 80px;
    height: 80px;
    vertical-align: -0.15em;
    fill: currentColor;
    overflow: hidden;
  }

  .card-plane {
    cursor: pointer;

    .card-text {
      font-family: Helvetica Neue, Helvetica, PingFang SC, Hiragino Sans GB, Microsoft YaHei, Arial, sans-serif;
      margin-top: 10px;
      margin-right: 5px;
      float: right;
      font-size: 20px;
      color: rgba(0, 0, 0, 0.45);
    }

    p {
      color: #666666;
      font-size: 20px;
      font-family: Helvetica Neue, Helvetica, PingFang SC, Hiragino Sans GB, Microsoft YaHei, Arial, sans-serif;
    }
  }


  .dashboard {
    &-container {
      margin: 30px;
    }

    &-text {
      font-size: 30px;
      line-height: 46px;
    }
  }
</style>
