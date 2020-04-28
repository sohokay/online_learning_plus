<template>
    <div>
        <van-image
                width="100%"
                height="200"
                :src="courseImg"
        />
        <span class="iconfont icon-icon-" style="position:absolute;top:10px;left: 15px;font-size: 24px;color: back;"
              @click="back"/>
        <van-tabs v-model="active" sticky>
            <van-tab title="公告">
                <Notice :course-id="courseId"/>
            </van-tab>
            <van-tab title="课件">
                <Courseware :course-id="courseId"/>
            </van-tab>
            <van-tab title="考核">
                <Assessment :course-id="courseId"/>
            </van-tab>
            <van-tab title="帖子">
                <Discuss :course-id="courseId"/>
            </van-tab>
        </van-tabs>
    </div>

</template>

<script>
    import Discuss from "@/views/study/Discuss";
    import Assessment from "@/views/study/Assessment";
    import Courseware from "@/views/study/Courseware";
    import Notice from "@/views/study/Notice";
    import {isSelect} from '@/api/study'
    import {Dialog, Notify} from 'vant';
    import {mapGetters} from 'vuex'

    export default {
        name: "Index",
        props: ['courseId'],
        components: {Notice, Courseware, Assessment, Discuss},
        watch: {
            active(data) {
                this.$store.commit('setTabActivity', data)
            }
        },
        computed: {
            ...mapGetters([
                'tabActivity'
            ]),
        },
        created() {
            this.active=this.tabActivity
            isSelect(this.courseId).then(res => {
                if (res.code === 201) {
                    Dialog.alert({
                        title: '未选课',
                        message: '请先选课，再学习'
                    }).then(() => {
                        this.$router.push('/myCourse')
                    });
                }
            })
           this.courseImg =this.$route.query.courseImg
        },
        data() {
            return {
                active: 0,
                courseImg:''
            }
        },

        methods: {
            back() {
                this.$router.go(-1)
            }
        }
    }
</script>

<style scoped>
    * {
        font-family: 楷体;

    }
</style>
