<template>
    <div>
        <van-divider>我的课程</van-divider>
        <div class="my-class">
            <van-card
                currency=""
                :price="course.studentNum+'人正在学习'"
                :title="course.courseName"
                :thumb="course.courseImg"
                v-for="course in courseList" :key="course.courseId"
        >
            <div slot="footer">
                <van-button size="mini" @click="study(course.courseId,course.courseImg)">学习</van-button>
                <van-button size="mini" @click="analysis">智能分析</van-button>
            </div>
        </van-card>
        </div>
        <van-popup
                v-model="show"
                round
                position="bottom"
                style=" height: 30%;text-align: center;background-color: #f7f8fa"
        >
            <van-circle
                    v-model="currentRate"
                    :rate="30"
                    :speed="100"
                    color="red"
                    text="正在开发中"
                    style="top: 10px"
            />
        </van-popup>
    </div>
</template>

<script>
    import {courseSelectionList} from '@/api/homePage'

    export default {
        name: "MyClass",
        data() {
            return {
                show: false,
                currentRate: 0,
                courseList: []
            }
        },
        created() {
            courseSelectionList().then(res => {
                this.courseList = res.data
            })
        },
        methods: {
            study(courseId,courseImg) {
                this.$router.push({path:'/study/'+courseId,query:{
                    courseImg
                    }})
            },
            analysis() {
                this.show = true
            },
            text() {
                return this.currentRate.toFixed(0) + '%'
            }
        }
    }
</script>

<style scoped>
    .learning-time {
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
    .my-class {
        margin: 10px;
        background-color: white;
        border-radius: 5px;
    }

</style>
