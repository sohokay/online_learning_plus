<template>
    <div>
        <van-collapse v-model="activeName">
            <van-collapse-item title="第一章" name="1">
                1.0
                <p>d村上春树</p>
                <p>dd</p>
                <van-divider />
                内容
                <p>dd</p>
                <p>dd</p>
                <van-divider />
            </van-collapse-item>
        </van-collapse>
    </div>
</template>

<script>
    export default {
        name: "Catalog",
        props: ['courseId'],
        data() {
            return {
                activeName: ['1']
            }
        }
    }
</script>

<style scoped>

</style>
