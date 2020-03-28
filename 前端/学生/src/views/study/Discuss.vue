<template>
    <div>
        <span ref="in" @click="input" class="iconfont icon-icon"
              style="position:fixed;bottom: 30px;right:40px;font-size: 50px;color: #55a532"/>

        <van-panel title="标题" desc="121212" status="我发布的" @click="toDetail('11')"/>
        <van-panel title="标题" desc="描述信息" status="老师参与"/>
        <van-panel title="标题" desc="描述信息" status="状态"/>


        <van-popup
                v-model="inputShow"
                round
                position="top"
                :style="{ height: '70%' }"
        >
            <div style="text-align: center">
                <p>发表评论</p>
                <van-form @submit="onSubmit">
                    <van-field
                            v-model="form.title"
                            name="标题"
                            label="标题"
                            placeholder="标题"
                            :rules="[{ required: true, message: '请填写标题' }]"
                    />
                    <van-field
                            v-model="form.content"
                            type="textarea"
                            rows="10"
                            autosize
                            name="内容"
                            label="内容"
                            placeholder="内容"
                            :rules="[{ required: true, message: '请填写内容' }]"
                    />
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
    export default {
        name: "Discuss",
        methods: {
            input() {
                this.inputShow = true
                console.log(this.$refs.in.style)
            },
            toDetail(discussId) {
                this.$router.push("/discussDetails/" + discussId)
            }
        },
        props: ['courseId'],
        data() {
            return {
                form: {
                    title: '',
                    content: ''
                },
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
