<template>
<div>
    <van-collapse v-model="activeName">
        <van-collapse-item title="第一章" name="1">
            1.0
            <p>村上春树</p>
            <p>dd</p>
            <van-divider/>
            内容
            <p>dd</p>
            <p>dd</p>
            <van-divider/>
        </van-collapse-item>
    </van-collapse>
</div>
</template>

<script>
    export default {
        name: "Courseware",
        props:['courseId'],
        data(){
            return{
                activeName: ['0']
            }
        }
    }
</script>
<style scoped>
    button{
        padding: 0px;
        text-align: left;
        width: 100%;
        background-color: white;
        /*border: 0px;*/
    }
</style>
