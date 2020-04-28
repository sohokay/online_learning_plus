<template>
    <div>
        <div style="height: 40px;padding-left: 10px;">
            <div>
                <span class="iconfont icon-cha" style="margin-right: 10px;font-size: 14px;font-weight: bold"
                      @click="back"/>
                <p style="font-size: 18px;line-height: 40px;font-weight: bold">公告详情</p>
            </div>
        </div>
        <div class="title">
            <p style="font-size: 20px;font-weight: bold;margin-bottom: 20px" v-text="notice.title"/><br/>
            <p v-text="notice.time"/>
        </div>
        <div style="padding: 20px" v-html="notice.content"/>
    </div>
</template>

<script>
    import {getNoticeById} from '@/api/study'

    export default {
        name: "NoticeDetails",
        created() {
            getNoticeById(this.noticeId).then(res => {
                this.notice = res.data
            })
        },
        methods: {
            back() {
                this.$router.go(-1)
            }
        },
        props: ['noticeId'],
        data() {
            return {
                notice: {

                }
            }
        }
    }
</script>

<style scoped>
    p {
        font-family: 楷体;
        display: inline;
    }

    .title {
        height: 70px;
        background-color: #f4f4f4;
        padding: 20px;
    }

</style>
