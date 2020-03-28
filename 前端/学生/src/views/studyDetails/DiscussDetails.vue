<template>
    <div>
        <div style="height: 40px;padding-left: 10px;border-bottom: 1px solid #d7d7d7;">
            <div>
                <span class="iconfont icon-cha" style="margin-right: 10px;font-size: 14px;font-weight: bold" @click="back"/>
                <p style="font-size: 18px;line-height: 40px;font-weight: bold">讨论详情</p>
            </div>
        </div>


    </div>
</template>

<script>
    export default {
        name: "DiscussDetails",
        props:['discussId'],
        methods:{
            back(){
                this.$router.go(-1)
            }
        }
    }
</script>

<style scoped>
    p {
        font-family: 楷体;
        display: inline;
    }

    .title {
        height: 70px;
        background-color: #f4f4f4;
        padding: 20px;
    }

</style>
