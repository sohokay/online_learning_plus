<template>
  <div class="chart" style="height:400px;width:400px"/>
</template>

<script>
  import echarts from 'echarts'

  require('echarts/theme/macarons') // echarts theme
  export default {
    name: "PieChart",
    props: [
      'chartData'
    ]
    ,
    data() {
      return {
        chart: null
      }
    },
    watch: {
      chartData: {
        deep: true,
        handler(chartData) {
          this.initChart(chartData)
        }
      }
    },
    mounted() {
      this.initChart(this.chartData)
    },
    methods: {
      initChart(chartData) {
        this.chart = echarts.init(this.$el, 'macarons')
        this.chart.setOption({
          title: {
            text: '此学生学习行为统计',
            left: 'right'
          },
          tooltip: {
            trigger: 'item',
            formatter: '{a} <br/>{b} : {c} ({d}%)'
          },
          legend: {
            orient: 'vertical',
            left: 'left',
            data: ['进入学习页次数', '查看公告次数', '发布帖子数目', '参与讨论参数', '浏览课件次数']
          },
          series: [
            {
              name: '访问来源',
              type: 'pie',
              radius: '55%',
              center: ['60%', '60%'],
              data: [
                {value: chartData.intoNum, name: '进入学习页次数'},
                {value: chartData.noticeNum, name: '查看公告次数'},
                {value: chartData.postNum, name: '发布帖子数目'},
                {value: chartData.discussNum, name: '参与讨论参数'},
                {value: chartData.coursewareNum, name: '浏览课件次数'}
              ],
              emphasis: {
                itemStyle: {
                  shadowBlur: 10,
                  shadowOffsetX: 0,
                  shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
              }
            }
          ]
        })
      }
    }
  }
</script>

<style scoped>

</style>
