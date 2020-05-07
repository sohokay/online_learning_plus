<template>
    <div>
        <div style="height: 40px;padding-left: 10px;border-bottom: 1px solid #d7d7d7;">
            <div>
                <span class="iconfont icon-cha" style="margin-right: 10px;font-size: 14px;font-weight: bold"
                      @click="back"/>
                <p style="font-size: 18px;line-height: 40px;font-weight: bold">讨论详情</p>
            </div>
        </div>
        <div class="app-content">
            <div class="text">
                <h1 style="font-size: 22px">{{list.title}}</h1>
                <van-row>
                    <van-col span="3">
                        <van-image
                                round
                                width="2rem"
                                height="2rem"
                                :src="list.userInfo.portrait"
                        />
                    </van-col>
                    <van-col span="21">
                        <div class="author-name">
                            {{list.users.userName}}
                        </div>
                        <div class="author-info-bottom-text">
                            {{list.time}}
                        </div>
                    </van-col>
                    <van-col v-html="list.content"/>
                </van-row>
            </div>
            <h2 style="font-size: 16px;color: #7d7d7d" v-if="list.discussReplies.length===0">暂无评论</h2>
            <h2 style="font-size: 16px;color: #7d7d7d" v-else>全部评论</h2>
            <div class="text" v-for="replie in list.discussReplies">
                <van-row>
                    <van-col span="3">
                        <van-image
                                round
                                width="2rem"
                                height="2rem"
                                :src="replie.portrait"
                        />
                    </van-col>
                    <van-col span="21">
                        <div class="author-name">
                            {{replie.userName}}
                        </div>
                        <div class="author-info-bottom-text">
                            {{replie.time}}
                        </div>
                    </van-col>
                    <van-col offset="3" v-html="replie.content"/>
                </van-row>
            </div>

            <span ref="in" @click="inputShow=true" class="iconfont icon-icon"
                  style="position:fixed;bottom: 30px;right:40px;font-size: 50px;color: #55a532;"/>

            <van-popup
                    v-model="inputShow"
                    round
                    position="top"
                    :style="{ height: '70%' }"
            >
                <div style="text-align: center">
                    <p>发表评论</p>
                    <van-form @submit="onSubmit">
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
    </div>
</template>

<script>
    import {getDisDetail, putReply} from '@/api/courseDetails.js'
    import Editor from "@/components/wangEditor";
    import {Notify} from "vant";
    import {addDiscussNum} from '@/api/learningBehavior.js'

    export default {
        name: "DiscussDetails",
        props: ['discussId'],
        components: {Editor},
        created() {
            getDisDetail(this.discussId).then(res => {
                this.list = res.data
                console.log(this.list.discussReplies)
            })
        },
        data() {
            return {
                list: {},
                form: {
                    discussId: 0,
                    content: '',
                },
                inputShow: false

            }
        },
        methods: {
            back() {
                this.$router.go(-1)
            },
            onSubmit() {
                this.form.content = this.$refs.editor.editorContent
                if (this.form.content.trim() !== '' && this.form.content !== "<p><br></p>") {
                    this.form.content = this.$refs.editor.editorContent
                    this.form.discussId = this.discussId
                    putReply(this.form).then(res => {
                        getDisDetail(this.discussId).then(res => {
                            this.list = res.data
                            this.inputShow = false
                            Notify({type: "success", message: "发布成功"})
                        })
                        addDiscussNum()
                    })
                }
            }
        }
    }
</script>

<style scoped lang="scss">
    p {
        font-family: 楷体;
        display: inline;
    }

    .app-content {
        padding: 14px;

        .author-name {
            opacity: .9;
            font-weight: 700;
            font-size: 3.51852vw;
            color: #000;
        }

        .author-info-bottom-text {
            display: inline-block;
            vertical-align: top;
            overflow: hidden;
            white-space: nowrap;
            text-overflow: ellipsis;
            max-width: 71.2963vw;
            opacity: .5;
            font-size: 10px;
        }

        .text {
            border-bottom: 1px #dadada solid;
            padding-bottom: 20px;
            margin-bottom: 20px;
        }
    }


</style>
