<template>
    <div>
        <van-image
                width="100%"
                height="250"
                src="https://img.yzcdn.cn/vant/cat.jpeg"
        />


        <span class="iconfont icon-icon-" style="position:absolute;top:10px;left: 15px;font-size: 24px;color: white" @click="back"/>
        <div style="position: fixed;bottom: 0;left: 0;width: 100%;right: 0;z-index: 1;">
            <van-button style="width: 100%;height: 48px;
                  font-size: 16px;background-color: #ff7a3e">立即参与
            </van-button>
        </div>
        <van-tabs v-model="active" sticky>
            <van-tab title="详情">
                <Details :course-id='courseId'/>
            </van-tab>
            <van-tab title="目录">
                <catalog :course-id="courseId"/>
            </van-tab>
        </van-tabs>
    </div>
</template>
<script>
    import Catalog from "@/views/courseDetails/Catalog";
    import Details from "@/views/courseDetails/Details";

    export default {
        name: "Index",
        components: {
            Details,
            Catalog
        },
        props: ['courseId'],
        data() {
            return {
                active: 2
            }
        },
        created() {
            console.log(this.$route)
        },
        methods:{
            back(){
                this.$router.go(-1)
            }


        }

    }
</script>
<style scoped lang="scss">
    body{
        div{
            width: 100%;
        }
    }
    .icon {
        width: 1em;
        height: 1em;
        vertical-align: -0.15em;
        fill: currentColor;
        overflow: hidden;
    }
</style>
