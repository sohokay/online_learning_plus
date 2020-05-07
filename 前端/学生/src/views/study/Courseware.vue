<template>
    <div>
        <van-collapse v-model="activeName">
            <van-collapse-item v-for="courseware in coursewareList" :title="courseware.title" name="1">
                <div style="cursor: pointer;border-bottom: dashed 1px;font-size: 14px" @click="toDetail(content.url)" v-for="content in courseware.contents">
                    <span class="iconfont icon-pdf"></span>
                    {{content.name}}</div>
            </van-collapse-item>
        </van-collapse>
    </div>
</template>

<script>
    import {getCourseware} from '@/api/study'

    export default {
        name: "Courseware",
        props: ['courseId'],
        created() {
            getCourseware(this.courseId).then(res => {
                this.coursewareList = res.data
            })
        },

        data() {
            return {
                coursewareList: [],
                activeName: ['0']
            }
        },
        methods:
            {
                toDetail(url) {
                    this.$router.push("/courseware_details/"+url.split('/')[3])
                }

            }
    }
</script>
<style scoped>
    button {
        padding: 0px;
        text-align: left;
        width: 100%;
        background-color: white;
        /*border: 0px;*/
    }
    .icon {
        width: 1em;
        height: 1em;
        vertical-align: -0.15em;
        fill: currentColor;
        overflow: hidden;
    }
</style>
