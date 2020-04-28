<template>
    <div v-show="isShow">
        <van-swipe :autoplay="3000">
            <van-swipe-item v-for="(image) in images" :key="images.broadcastId">
                <van-image width="100%" height="180" :src="image.url"/>
            </van-swipe-item>
        </van-swipe>
        <van-divider>
            <svg class="icon" aria-hidden="true">
                <use xlink:href="#icon-jisuanji"/>
            </svg>
            计算机
        </van-divider>
        <div class="more">
            <span @click="toMore('计算机')">更多 ></span>
        </div>
        <van-card v-for="(course,index) in courseList" v-if="course.courseType==='计算机'" :key="index"
                  :price="course.teacherName" :title="course.courseName" :thumb="course.courseImg" currency="教师：">
            <div slot="num">
                <svg class="icon" aria-hidden="true">
                    <use xlink:href="#icon-wo"/>
                </svg>
                {{course.studentNum}}
            </div>
            <div slot="footer">
                <van-button size="mini" plain type="primary" @click="to_course_detail(course.courseId)">详情</van-button>
            </div>
        </van-card>
        <van-divider>
            <svg class="icon" aria-hidden="true">
                <use xlink:href="#icon-yingyu"/>
            </svg>
            外语
        </van-divider>
        <div class="more">
            <span @click="toMore('外语')">更多 ></span>
        </div>
        <van-card v-for="(course,index) in courseList" v-if="course.courseType==='外语'" :key="index"
                  :price="course.teacherName" :title="course.courseName" :thumb="course.courseImg" currency="教师：">
            <div slot="num">
                <svg class="icon" aria-hidden="true">
                    <use xlink:href="#icon-wo"/>
                </svg>
                {{course.studentNum}}
            </div>
            <div slot="footer">
                <van-button size="mini" plain type="primary" @click="to_course_detail(course.courseId)">详情</van-button>
            </div>
        </van-card>
        <van-divider>
            <svg class="icon" aria-hidden="true">
                <use xlink:href="#icon-shuxue-"/>
            </svg>
            理学
        </van-divider>
        <div class="more">
            <span @click="toMore('理学')">更多 ></span>
        </div>
        <van-card v-for="(course,index) in courseList" v-if="course.courseType==='理学'" :key="index"
                  :price="course.teacherName" :title="course.courseName" :thumb="course.courseImg" currency="教师：">
            <div slot="num">
                <svg class="icon" aria-hidden="true">
                    <use xlink:href="#icon-wo"/>
                </svg>
                {{course.studentNum}}
            </div>
            <div slot="footer">
                <van-button size="mini" plain type="primary" @click="to_course_detail(course.courseId)">详情</van-button>
            </div>
        </van-card>
        <van-divider>
            <svg class="icon" aria-hidden="true">
                <use xlink:href="#icon-gongju"/>
            </svg>
            工学
        </van-divider>
        <div class="more">
            <span @click="toMore('工学')">更多 ></span>
        </div>
        <van-card v-for="(course,index) in courseList" v-if="course.courseType==='工学'" :key="index"
                  :price="course.teacherName" :title="course.courseName" :thumb="course.courseImg" currency="教师：">
            <div slot="num">
                <svg class="icon" aria-hidden="true">
                    <use xlink:href="#icon-wo"/>
                </svg>
                {{course.studentNum}}
            </div>
            <div slot="footer">
                <van-button size="mini" plain type="primary" @click="to_course_detail(course.courseId)">详情</van-button>
            </div>
        </van-card>
        <van-divider>
            <svg class="icon" aria-hidden="true">
                <use xlink:href="#icon-lishi"/>
            </svg>
            文史哲
        </van-divider>
        <div class="more">
            <span @click="toMore('文史哲')">更多 ></span>
        </div>
        <van-card v-for="(course,index) in courseList" v-if="course.courseType==='文史哲'" :key="index"
                  :price="course.teacherName" :title="course.courseName" :thumb="course.courseImg" currency="教师：">
            <div slot="num">
                <svg class="icon" aria-hidden="true">
                    <use xlink:href="#icon-wo"/>
                </svg>
                {{course.studentNum}}
            </div>
            <div slot="footer">
                <van-button size="mini" plain type="primary" @click="to_course_detail(course.courseId)">详情</van-button>
            </div>
        </van-card>
        <van-divider>
            <svg class="icon" aria-hidden="true">
                <use xlink:href="#icon-qita"/>
            </svg>
            其他
        </van-divider>
        <div class="more">
            <span @click="toMore('其他')">更多 ></span>
        </div>
        <van-card v-for="(course,index) in courseList" v-if="course.courseType==='其他'" :key="index"
                  :price="course.teacherName" :title="course.courseName" :thumb="course.courseImg" currency="教师：">
            <div slot="num">
                <svg class="icon" aria-hidden="true">
                    <use xlink:href="#icon-wo"/>
                </svg>
                {{course.studentNum}}
            </div>
            <div slot="footer">
                <van-button size="mini" plain type="primary" @click="to_course_detail(course.courseId)">详情</van-button>
            </div>
        </van-card>
    </div>
</template>

<script>
    import {getRotation, getRecommendCourse} from '@/api/homePage'

    export default {
        name: "Index",
        created() {
            getRotation().then(res => {
                this.images = res.data
                this.isShow = true
            })
            getRecommendCourse().then(res => {
                this.courseList = res.data
            })
        },
        data() {
            return {
                isShow: false,
                images: [
                    'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1582865552380&di=60f0044e1a068554bc525463394072fd&imgtype=0&src=http%3A%2F%2Ftu.ossfiles.cn%3A9186%2Fgroup2%2FM00%2F54%2FE7%2FrBgICV2uccaAMhNhAAB-0ZY0F8w923.jpg',
                    'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1582865578333&di=4a92df551df1f2f0573a780496d2e4dc&imgtype=0&src=http%3A%2F%2Fimg3.qianzhan.com%2Fnews%2F201511%2F04%2F20151104-aaae0871a879feb2_600x5000.jpg',
                    'https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3126090086,86324601&fm=26&gp=0.jpg'
                ],
                courseList: [
                    {
                        courseId: 1,
                        courseName: "计算机基础",
                        teacherName: "慕老",
                        courseType: "计算机",
                        courseImg: "http://img.musuixin.top/FgtdY_ECQ6kShWepfvYjG4s-seHS",
                        studentNum: "5"
                    }
                ]
            }
        },
        methods: {
            to_course_detail(courseId) {
                this.$router.push("/course_detail/" + courseId)
            },
            toMore(address) {
                this.$router.push("/more_course/" + address)
            }
        }
    }
</script>

<style scoped>
    .my-swipe .van-swipe-item {
        color: #fff;
        font-size: 20px;
        line-height: 150px;
        text-align: center;
        background-color: #39a9ed;
    }

    .more {
        text-align: right;
        margin-top: 5px;
        margin-right: 15px;
        font-size: 12px;
        color: #969799
    }

</style>
