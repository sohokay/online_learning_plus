<template>
    <div>
        <van-tabs v-model="active" sticky>
            <van-tab title="我的课程"><MyClass/></van-tab>
            <van-tab title="学习分析"><StudyAnalysis/></van-tab>
        </van-tabs>
    </div>
</template>

<script>
    import MyClass from "@/views/homePage/myLearning/MyClass";
    import StudyAnalysis from "@/views/homePage/myLearning/StudyAnalysis";
    export default {
        name: "Index",
        components: {StudyAnalysis, MyClass},
        data(){
            return{
                active:0
            }
        }
    }
</script>

<style scoped>
    body{
        height: 100%;
    }
</style>
