<template>
    <div>
        <div style="height: 40px;padding-left: 10px;">
            <div>
                <span class="iconfont icon-cha" style="margin-right: 10px;font-size: 14px;font-weight: bold" @click="back"/>
                <p style="font-size: 18px;line-height: 40px;font-weight: bold">公告详情</p>
            </div>
        </div>
        <div class="title">
        <p style="font-size: 20px">是放假都是分开的付款付款是的客服库 </p>
        <p>2020-12-12</p>
        </div>
        <div>
        </div>
    </div>
</template>

<script>
    export default {
        name: "NoticeDetails",
        methods:{
            back(){
                this.$router.go(-1)
            }
        },
        props:['noticeId']
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
