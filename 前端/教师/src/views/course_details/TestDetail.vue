<template>
  <div class="app-container">

    <h3>测试标题：{{questions.title}}</h3>
    <p>允许提交次数：{{ questions.allowNum}}</p>
    <p>发布时间：{{ questions.creationTime}}</p>
    <p>截止时间：{{ questions.deadline}}</p>
    <p>总分：{{ questions.totalScore}}</p>
    <el-collapse v-model="activeNames" @change="handleChange">
      <el-collapse-item v-for="(q ,index) in questions.questions" :title="'第'+(index+1)+'题:'+q.subjectName" name="1">
        <h4>选项A：{{q.optionA}}</h4>
        <h4>选项B：{{q.optionB}}</h4>
        <h4>选项C：{{q.optionC}}</h4>
        <h4>选项D：{{q.optionD}}</h4>
        <h4>正确选项：{{q.correct}}</h4>
      </el-collapse-item>
    </el-collapse>
  </div>
</template>

<script>
  import {getTestDetail} from '@/api/courseTest'

  export default {
    name: "TestDetail",
    props: ['testId'],
    created() {
      getTestDetail(this.testId).then(res => {
        this.questions = res.data
        this.questions.questions=JSON.parse(res.data.question)
      })
    },
    data() {
      return {
        questions: {}


      }
    },
    methods: {}
  }
</script>

<style scoped>

</style>
