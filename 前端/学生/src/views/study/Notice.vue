<template>
    <div>
        <van-panel :title="notice.title" :desc="notice.time" @click="toNotice(notice.noticeId)"
                   v-for="notice in noticeList"/>
    </div>
</template>

<script>
    import {getNoticeList} from '@/api/study.js'

    export default {
        name: "Notice",
        props: ['courseId'],
        created() {
            getNoticeList(this.courseId).then(res => {
                this.noticeList = res.data
            });

        },
        data() {
            return {
                noticeList: []
            }
        },
        methods: {
            toNotice(noticeId) {
                this.$router.push('/notice_details/' + noticeId)
            }
        }
    }
</script>

<style scoped>

</style>
