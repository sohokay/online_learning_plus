<template>
    <div>
        <van-image
                width="100%"
                height="250"
                :src="courseDetail.course.courseImg"
        />
        <span style="position:absolute;top:10px;left: 15px;font-size: 24px;" @click="back">
                     <svg class="icon" aria-hidden="true" style="font-size: 20px">
                        <use xlink:href="#icon-icon-"/>
                    </svg>
        </span>
        <div style="position: fixed;bottom: 0;left: 0;width: 100%;right: 0;z-index: 1;">
            <van-button style="width: 100%;height: 48px;
                  font-size: 16px;background-color: #ff7a3e" @click.native="CourseSelection">立即参与
            </van-button>
        </div>
        <van-tabs v-model="active" sticky style="margin-bottom: 50px">
            <van-tab title="详情">
                <Details :course-id='courseId' :course-detail="courseDetail"/>
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
    import {getCourseDetail,courseSelection} from "@/api/courseDetails";
    import {Dialog, Notify} from 'vant';
    export default {
        name: "Index",
        components: {
            Details,
            Catalog
        },
        props: ['courseId'],
        data() {
            return {
                active: 2,
                courseDetail: {}
            }
        },
        created() {
            getCourseDetail(this.courseId).then(res => {
                this.courseDetail = res.data
            })
        },
        methods: {
            back() {
                this.$router.go(-1)
            },
            CourseSelection(){
                Dialog.confirm({
                    title: '选课确认',
                    message: `确定选择课程《${this.courseDetail.course.courseName}》？`
                }).then(() => {
                    courseSelection(this.courseId).then(res=>{
                        Notify({type: 'success', message: res.data});
                    })
                }).catch(() => {

                });
            }
        }
    }
</script>
<style scoped lang="scss">
    body {
        div {
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
