<template>
  <div class="chart" style="height:400px;width:100%"/>
</template>

<script>
  import echarts from 'echarts'

  require('echarts/theme/macarons') // echarts theme
  export default {
    name: "LineChart",
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
          xAxis: {
            type: 'category',
            data:  chartData.map(x => x.time),
            boundaryGap: false,
            axisTick: {
              show: false
            }
          },
          grid: {
            left: 18,
            right: 35,
            bottom: 50,
            top: 10,
            containLabel: true
          },
          tooltip: {
            trigger: 'axis',
            axisPointer: {
              type: 'cross'
            },
            padding: [5, 10]
          },
          yAxis: {
            type: 'value'
          },
          dataZoom: [{
            start: 0,
            end: 100,

          }, {
            type: 'inside'
          }],
          series: [{
            data: chartData.map(x => x.num),
            type: 'line',
            smooth: true
          }]
        })
      }
    }
  }
</script>

<style scoped>

</style>
