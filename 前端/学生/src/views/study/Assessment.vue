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
            <p>Week Unit1</p>
            <div class="box-item">
                <p>Unit1 单元测试</p>
                <van-divider/>
                <p>提交截止时间</p>
                <van-divider/>
                <van-row>
                    <van-col span="4">
                        <p style="display: inline">总分</p>
                    </van-col>
                    <van-col span="6" offset="14">
                        <van-button type="mini"
                                    style="border-radius:10px;color:white;background-color: #55a532;border: 0px">进入测验
                        </van-button>
                    </van-col>
                </van-row>
            </div>
            <div class="box-item">
                <p>Unit1 单元测试</p>
                <van-divider/>
                <p>提交截止时间</p>
                <van-divider/>
                <van-row>
                    <van-col span="4">
                        <p style="display: inline">总分</p>
                    </van-col>
                    <van-col span="6" offset="14">
                        <van-button type="mini"
                                    style="border-radius:10px;color:white;background-color: #55a532;border: 0px">进入测验
                        </van-button>
                    </van-col>
                </van-row>
            </div>
            <div class="box-item">
                <p>Unit1 单元测试</p>
                <van-divider/>
                <p>提交截止时间</p>
                <van-divider/>
                <van-row>
                    <van-col span="4">
                        <p style="display: inline">总分</p>
                    </van-col>
                    <van-col span="6" offset="14">
                        <van-button type="mini"
                                    style="border-radius:10px;color:white;background-color: #55a532;border: 0px">进入测验
                        </van-button>
                    </van-col>
                </van-row>
            </div>
            <div class="box-item">
                <p>Unit1 单元测试</p>
                <van-divider/>
                <p>提交截止时间</p>
                <van-divider/>
                <van-row>
                    <van-col span="4">
                        <p style="display: inline">总分</p>
                    </van-col>
                    <van-col span="6" offset="14">
                        <van-button type="mini"
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
                <p style="text-align: center">评价标准</p>
            </div>
        </van-popup>
        <!--        <div style="text-align: center;padding-top: 40px;color:#babab6">-->
        <!--            <span class="iconfont icon-biaodan" style="font-size:62px;"/>-->
        <!--            <p>老师还没有发布考核内容，请耐心等待</p>-->
        <!--        </div>-->
    </div>
</template>
<script>
    export default {
        name: "Assessment",
        props: ['courseId'],
        data() {
            return {
                show: false
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
        height: 120px;
        line-height: 18px;
        border-radius: 8px;
        padding: 12px;
        margin-bottom: 10px;

        p {
            margin: 0;
        }
    }


</style>
