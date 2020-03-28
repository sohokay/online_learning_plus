<template>
    <div>

        <van-dropdown-menu>
            <van-dropdown-item v-model="value1" :options="option1" @change="hi"/>
            <van-dropdown-item v-model="value2" :options="option2" />
        </van-dropdown-menu>
        <ve-pie :data="chartData"></ve-pie>
        <ve-line :data="chartData"></ve-line>
    </div>
</template>
<script>
    export default {
        name: "StudyAnalysis",
        data() {
            return {
                chartData: {
                    columns: ['日期', '访问用户', '下单用户', '下单率'],
                    rows: [
                        {'日期': '1/1', '访问用户': 1393, '下单用户': 1093, '下单率': 0.32},
                        {'日期': '1/2', '访问用户': 3530, '下单用户': 3230, '下单率': 0.26},
                        {'日期': '1/3', '访问用户': 2923, '下单用户': 2623, '下单率': 0.76},
                        {'日期': '1/4', '访问用户': 1723, '下单用户': 1423, '下单率': 0.49},
                        {'日期': '1/5', '访问用户': 3792, '下单用户': 3492, '下单率': 0.323},
                        {'日期': '1/6', '访问用户': 4593, '下单用户': 4293, '下单率': 0.78}
                    ]
                },
                value1: 0,
                value2: 'a',
                option1: [
                    {text: '全部课程', value: 0},
                    {text: '新款商品', value: 1},
                    {text: '活动商品', value: 2}
                ],
                option2: [
                    {text: '今日', value: 'a'},
                    {text: '一周内', value: 'b'},
                    {text: '本月内', value: 'c'},
                ]
            }
        },
        methods:{
            hi(value){
                alert(value)
            }
        }

    }
</script>

<style scoped lang="scss">
    body {
        height: 100%;
    }
</style>
