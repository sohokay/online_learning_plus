<template>
  <div class="app-container">
    <el-collapse v-model="activeNames" @change="handleChange">
      <el-collapse-item title="课程名称" name="1">
        <div v-html="course.courseName"/>
      </el-collapse-item>
      <el-collapse-item title="课程类型" name="2">
        <div v-html="course.courseType"/>
      </el-collapse-item>
      <el-collapse-item title="课程开始时间————课程结束时" name="3">
        <div>{{courseInfo.startTime}}————{{courseInfo.endTime}}</div>
      </el-collapse-item>
      <el-collapse-item title="课程简介" name="4">
        <div v-html="courseInfo.briefIntroduction"/>
      </el-collapse-item>
      <el-collapse-item title="课程概述" name="5">
        <div v-if="courseInfo.summary" v-html="courseInfo.summary"/>
        <div v-else>无</div>
      </el-collapse-item>
      <el-collapse-item title="授课目标" name="6">
        <div v-if="courseInfo.target" v-html="courseInfo.target"/>
        <div v-else>无</div>
      </el-collapse-item>
      <el-collapse-item title="课程要求" name="7">
        <div v-if="courseInfo.requirements" v-html="courseInfo.requirements"/>
        <div v-else>无</div>
      </el-collapse-item>
      <el-collapse-item title="预备知识" name="8">
        <div v-if="courseInfo.preparatoryKnowledge" v-html="courseInfo.preparatoryKnowledge"/>
        <div v-else>无</div>
      </el-collapse-item>
      <el-collapse-item title="课程大纲" name="9">
        <div v-if="courseInfo.outline" v-html="courseInfo.outline"/>
        <div v-else>无</div>
      </el-collapse-item>
      <el-collapse-item title="参考资料" name="10">
        <div v-if="courseInfo.referenceMaterial" v-html="courseInfo.referenceMaterial"/>
        <div v-else>无</div>
      </el-collapse-item>
      <el-collapse-item title="常见问题" name="11">
        <div v-if="courseInfo.commonProblem" v-html="courseInfo.commonProblem"/>
        <div v-else>无</div>
      </el-collapse-item>
    </el-collapse>
  </div>
</template>

<script>
  import {getCourseInfo} from '@/api/courseDetail.js'

  export default {
    name: "course_info",
    data() {
      return {
        activeNames: ['1'],
        course: {},
        courseInfo: {}
      }
    },
    props: ["courseId"],
    created() {
      getCourseInfo(this.courseId).then(res => {
        this.course = res.data.course
        this.courseInfo = res.data.courseInfo
      })
    }
  }
</script>

<style scoped>

</style>
