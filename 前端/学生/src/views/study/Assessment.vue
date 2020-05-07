<template>
    <div>
        <div class="box">
            <van-row style="background-color: white;height:40px;line-height: 40px;border-radius: 8px"
                     @click="show=true">
                <van-col span="8" offset="1">
                    评价标准
                </van-col>
                <van-col span="2" offset="13">
                    <span class="iconfont icon-qianjin"/>
                </van-col>
            </van-row>
            <div class="box-item" v-for="test in testList">
                <p>标题：{{test.title}}</p>
                <p>提交截止时间：{{test.deadline}}</p>
                <p v-if="test.studentTest!=null">当前最高分:{{test.studentTest.record}}
                    剩余提交次数:{{test.allowNum-test.studentTest.num}}</p>
                <p v-else>还未提交，允许提交次数：{{test.allowNum}}</p>
                <van-row>
                    <van-col span="10">
                        <p style="display: inline">总分：{{test.totalScore}}</p>
                    </van-col>
                    <van-col span="6" offset="8">
                        <van-button type="mini"
                                    @click="toQuestions(test.testId)"
                                    style="border-radius:10px;color:white;background-color: #55a532;border: 0px">进入测验
                        </van-button>
                    </van-col>
                </van-row>
            </div>
        </div>
        <van-popup
                v-model="show"
                position="bottom"
                closeable
                round
                :style="{ height: '60%' }"
        >
            <div>
                <p style="text-align: center;font-size: 18px">评价标准</p>
                <p style="text-align: center" v-if="requirement.trim()!==''" v-html="requirement"/>
                <p v-else>无通知</p>
            </div>
        </van-popup>
        <!--        <div style="text-align: center;padding-top: 40px;color:#babab6">-->
        <!--            <span class="iconfont icon-biaodan" style="font-size:62px;"/>-->
        <!--            <p>老师还没有发布考核内容，请耐心等待</p>-->
        <!--        </div>-->
    </div>
</template>
<script>
    import {getRequirement, getTest} from '@/api/test'

    export default {
        name: "Assessment",
        props: ['courseId'],
        created() {
            getRequirement(this.courseId).then(res => {
                this.requirement = res.data
            })
            getTest(this.courseId).then(res => {
                this.testList = res.data
            })

        },
        data() {
            return {
                show: false,
                requirement: '',
                testList: ''
            }
        },
        methods: {
            toQuestions(id) {
                this.$router.push('/test_questions/' + id)

            }
        }
    }
</script>

<style scoped lang="scss">
    .box {
        padding: 20px;
        /*width: 100%;*/
        /*height: 100px;*/
        background-color: #f4f4f4;
    }

    .box-item {
        background-color: white;
        /*height: 120px;*/
        line-height: 18px;
        border-radius: 8px;
        padding: 12px;
        margin-top: 10px;

        p {
            margin-top: 4px;;
            padding: 0;
        }
    }


</style>
