<template>
    <div>
        <van-panel title="标题" desc="2018-09-09" @click="toNotice('55')"/>
        <van-panel title="标题" desc="描述信息"/>
        <van-panel title="标题" desc="描述信息"/>
        {{courseId}}
    </div>
</template>

<script>
    export default {
        name: "Notice",
        props:['courseId'],
        methods:{
            toNotice(noticeId){
                this.$router.push('/noticeDetails/'+noticeId)
            }
        }
    }
</script>

<style scoped>

</style>
