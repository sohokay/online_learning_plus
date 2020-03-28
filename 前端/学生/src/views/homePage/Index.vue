<template>
    <div>
            <section>
                <router-view/>
            </section>
            <van-tabbar route>
                <van-tabbar-item replace icon="home-o" to="/course">首页</van-tabbar-item>
                <van-tabbar-item replace icon="todo-list-o" to="/myCourse">我的学习</van-tabbar-item>
                <van-tabbar-item replace icon="friends-o" to="/account">账号</van-tabbar-item>
            </van-tabbar>
    </div>
</template>

<script>
    export default {
        name: "Index",
        data() {
            return {
            }
        },
        methods:{

        }
    }
</script>

<style scoped>
    section {
        overflow: auto;
        margin-bottom: 50px;
    }
</style>
