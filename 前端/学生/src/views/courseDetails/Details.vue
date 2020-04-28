<template>
    <div>
        <div class="title">
            <p class="courseName" v-text="courseDetail.course.courseName"/>
            <van-row class="title-content">
                <van-col v-text="courseDetail.course.teacherName">

                </van-col>
                <van-col offset="16">
                    {{courseDetail.course.studentNum}}人已报名参加
                </van-col>
            </van-row>
        </div>
        <van-divider class="divider" content-position="left">简介</van-divider>
        <div class="txt" v-text="courseDetail.courseInfo.briefIntroduction||'无'">

        </div>
        <van-divider class="divider" content-position="left">课程概述</van-divider>
        <div class="txt" v-html="courseDetail.courseInfo.summary||'无'">

        </div>
        <van-divider  class="divider" content-position="left">开课时间</van-divider>
        <div class="txt">
            {{courseDetail.courseInfo.startTime}}——{{courseDetail.courseInfo.endTime}}
        </div>
        <van-divider  class="divider" content-position="left">授课目标</van-divider>
        <div class="txt" v-html="courseDetail.courseInfo.target||'无'">

        </div>
        <van-divider class="divider"  content-position="left">达标要求</van-divider>
        <div class="txt" v-html="courseDetail.courseInfo.requirements||'无'">

        </div>
        <van-divider  class="divider" content-position="left">预备知识</van-divider>
        <div class="txt" v-html="courseDetail.courseInfo.preparatoryKnowledge||'无'">

        </div>
        <van-divider  class="divider" content-position="left">授课大纲</van-divider>
        <div class="txt" v-html="courseDetail.courseInfo.outline||'无'">

        </div>
        <van-divider class="divider"  content-position="left">参考资料</van-divider>
        <div class="txt" v-html="courseDetail.courseInfo.referenceMaterial||'无'">

        </div>
        <van-divider class="divider"  content-position="left">常见问题</van-divider>
        <div class="txt" v-html="courseDetail.courseInfo.commonProblem||'无'">

        </div>

    </div>
</template>

<script>
    export default {
        name: "Details",
        props: ['courseId', 'courseDetail'],
        data() {
            return {}
        },
        created() {

        }
    }
</script>

<style scoped>
    .title {
        padding: 10px 0px 8px 15px;
        color: #111111;
        letter-spacing: 0;
        text-align: left;
    }

    .courseName {
        font-size: 20px;
    }

    p {
        margin: 3px;
    }

    .title-content {
        font-size: 12px;
        color: #999999
    }

    .txt {
        padding: 0px 10px 0px 10px;
        overflow: hidden;
        line-height: 1.5;
        font-size: 14px;
        font-family: PingFangSC-Regular;
        color: #666666;
        letter-spacing: 0;
        text-align: left;

    }
    .divider{
        font-size: 20px;
        font-family: 楷体;
        font-weight: bold;
    }
</style>
