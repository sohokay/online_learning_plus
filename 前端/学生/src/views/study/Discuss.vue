<template>
    <div>
        <van-panel v-for="discuss in discussList" :title="discuss.title" :desc="discuss.time"
                   :status="isMy(discuss.userId,discuss.isTeacher)"
                   @click="toDetail(discuss.discussId)"/>
        <span ref="in" @click="input" class="iconfont icon-icon"
              style="position:fixed;bottom: 30px;right:40px;font-size: 50px;color: #55a532;"/>

        <van-popup
                v-model="inputShow"
                round
                position="top"
                :style="{ height: '70%' }"
        >
            <div style="text-align: center">
                <p>发发布帖子</p>
                <van-form @submit="onSubmit">
                    <van-field
                            v-model="form.title"
                            name="标题"
                            label="标题"
                            placeholder="标题"
                            :rules="[{ required: true, message: '请填写标题' }]"
                    />
                    <editor style="padding: 10px" ref="editor"/>
                    <div style="margin: 16px;">
                        <van-button round block type="info" native-type="submit">
                            提交
                        </van-button>
                    </div>
                </van-form>
            </div>
        </van-popup>

    </div>
</template>

<script>
    import Editor from "@/components/wangEditor";
    import {getDiscuss, postDiscuss} from '@/api/courseDetails'
    import {Notify} from "vant";
    import {addPostNum} from "@/api/learningBehavior";

    export default {
        name: "Discuss",
        components: {Editor},
        created() {
            getDiscuss(this.courseId).then(res => {
                this.discussList = res.data.list
                this.studentId = res.data.studentId
            })
        },
        methods: {
            input() {
                this.inputShow = true
                console.log(this.$refs.in.style)
            },
            toDetail(discussId) {
                this.$router.push("/discuss_details/" + discussId)
            },
            onSubmit() {
                this.form.content = this.$refs.editor.editorContent
                this.form.courseId = this.courseId
                if (this.form.title.trim() !== '' && this.form.content.trim() !== '') {
                    postDiscuss(this.form).then(res => {
                        this.form.title = ''
                        getDiscuss(this.courseId).then(res => {
                            this.discussList = res.data.list
                            this.inputShow = false
                            Notify({type: 'success', message: '发布成功'});
                        })
                        addPostNum()
                    })
                } else {
                    Notify({type: 'warning', message: '请填写内容'});
                }
            },
            isMy(uid, isT) {
                if (uid === this.studentId) {
                    return "我发布的"
                }
                if (isT == 1) {
                    return "老师发布"
                }
            }
        },
        props: ['courseId'],
        data() {
            return {
                studentId: 0,
                form: {
                    title: '',
                    content: '',
                    courseId: ''
                },
                discussList: [],
                inputShow: false
            }
        }
    }
</script>

<style scoped>
    div {
        height: 100%;
    }

</style>
