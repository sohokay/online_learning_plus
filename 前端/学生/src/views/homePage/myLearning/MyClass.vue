<template>
    <div>
        <div class="learning-time">
        <van-row style="padding-top: 3px;padding-left: 8px">
            <van-col span="12" style="line-height:70px;font-size: 24px;color: white ">今日学习时长:50</van-col>
            <van-col span="4" offset="6">
                <svg class="icon" aria-hidden="true" style="">
                    <use xlink:href="#icon-medal"></use>
                </svg>
            </van-col>
        </van-row>
        </div>
        <van-divider content-position="left">我的课程</van-divider>
        <div class="my-class">
            <van-card
                    num="2"
                    price="2.00"
                    desc="描述信息"
                    title="商品标题"
                    thumb="https://img.yzcdn.cn/vant/ipad.jpeg"
            >
                <div slot="tags">
                    <van-tag plain type="danger">标签</van-tag>
                    <van-tag plain type="danger">标签</van-tag>
                </div>
                <div slot="footer">
                    <van-button size="mini" @click="study">学习</van-button>
                    <van-button size="mini" @click="analysis">智能分析</van-button>
                </div>
            </van-card>
        </div>
        <van-popup
                v-model="show"
                round
                position="bottom"
                style=" height: 80%;text-align: center;background-color: #f7f8fa "
        >

            <van-circle
                    v-model="currentRate"
                    :rate="30"
                    :speed="100"
                    color="red"
                    text="差"
                    style="top: 10px"
            />
        </van-popup>
    </div>
</template>

<script>
    export default {
        name: "MyClass",
        data(){
           return {
               show:false,
               currentRate: 0
           }
        },
        methods:{
            study(){
                this.$router.push('/study/11')
            },
            analysis(){
                this.show=true
            },
            text() {
                return this.currentRate.toFixed(0) + '%'
            }
        }
    }
</script>

<style scoped>
    .learning-time{
       margin: 10px;
        height: 80px;
        background-image: linear-gradient(-90deg, #55a532 0%, #42b983 100%);
        border-radius: 5px;
    }
    .icon {
        width: 4em;
        height: 4em;
        vertical-align: -0.15em;
        fill: currentColor;
        overflow: hidden;
    }
    .my-class{
        margin: 10px;
        background-color: white;
        border-radius: 5px;
    }


</style>
