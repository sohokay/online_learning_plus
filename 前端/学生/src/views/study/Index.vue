<template>
    <div>
        <van-image
                width="100%"
                height="200"
                src="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1583667598439&di=151e31183628c729fd44e137143e08f2&imgtype=0&src=http%3A%2F%2Fhiphotos.baidu.com%2F%25D3%25D3%25CA%25D6%25D0%25B4%25B6%25DF%25C0%25B2%2Fpic%2Fitem%2F593ca7fa7586ec23d8f9fd16.jpg"
        />
        <span class="iconfont icon-icon-" style="position:absolute;top:10px;left: 15px;font-size: 24px;color: white;"
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
            <van-tab title="讨论">
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

    export default {
        name: "Index",
        props:['courseId'],
        components: {Notice, Courseware, Assessment, Discuss},
        data() {
            return {
                active: 0
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
