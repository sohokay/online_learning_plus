<template>
  <div class="chart" style="height:400px;width:100%"/>
</template>

<script>
  import echarts from 'echarts'

  require('echarts/theme/macarons') // echarts theme
  export default {
    name: "ColumnChart",
    props: [
      'chartData'
    ],
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
      console.log(this.chartData)
      this.initChart(this.chartData)
    },
    methods: {
      initChart(chartData) {
        this.chart = echarts.init(this.$el, 'macarons')
        this.chart.setOption({
          tooltip: {
            trigger: 'axis',
            axisPointer: {
              type: 'shadow'
            }
          },
          legend: {
            data: ['此学生成绩', '平均成绩']
          },
          grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
          },
          xAxis: [
            {
              type: 'category',
              data: chartData.AverageScore.map(s=>s.testName)
            }
          ],
          yAxis: [
            {
              type: 'value'
            }
          ],
          series: [
            {
              name: '此学生成绩',
              type: 'bar',
              data: chartData.StudentScore.map(s=>s.record)
            },
            {
              name: '平均成绩',
              type: 'bar',
              stack: '广告',
              data: chartData.AverageScore.map(s=>s.averageScore)
            }
          ]
        })
      }
    }
  }
</script>

<style scoped>

</style>
