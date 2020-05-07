<template>
  <div>
    <el-row :gutter="20">
      <el-col :span="6" v-for="(test,index) in testList" :key="index">
        <el-card style="margin-bottom: 4px">
          <div slot="header" class="clearfix">
            <span>{{test.title}}</span>
            <el-button style="float: right; padding: 3px 5px" type="text" @click="seeNotice(test.testId)">查看
            </el-button>
          </div>
          <i class="el-icon-time" style="margin-right: 6px"/>{{test.creationTime}}
        </el-card>
      </el-col>
    </el-row>

    <el-row style="margin-top: 8px">
      <el-col :offset="22">
        <el-button type="primary" @click="dialogFormVisible = true">发布</el-button>
      </el-col>
    </el-row>

    <el-dialog title="新测试题发布" :visible.sync="dialogFormVisible">
      <h2 style="color: red">注意：为保证公平，发布后将不能删除、修改</h2>
      <el-form ref="testForm" :model="form" label-width="100px">
        <el-form-item label="测验标题" required prop="title">
          <el-input v-model="form.title" type="text" maxlength="32" show-word-limit/>
        </el-form-item>
        <el-form-item label="截止时间" required prop="deadline">
          <el-date-picker v-model="form.deadline" value-format="yyyy-MM-dd HH:mm:ss" type="datetime"
                          placeholder="选择日期时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="允许测验次数">
          <el-input-number v-model="form.allowNum" :min="1" :max="5"/>
        </el-form-item>
        <el-form-item label="限定时间">
          <el-input-number v-model="form.timeLimit" :min="0" :max="60"/>
          <span style="font-size: 14px">  min(0表示无限制)</span>
        </el-form-item>
        <el-form-item label="测验题数">
          <el-input-number v-model="num" @change="changeNum" :min="1" :max="30"/>
        </el-form-item>
        <h3>测试题</h3>
        <div v-for="(question,index) in form.questions" :key="index">
          <p>第{{index+1}}题</p>
          <el-form-item label="题名" required :prop="'questions.'+index+'.subjectName'">
            <el-input v-model="question.subjectName" type="text"/>
          </el-form-item>
          <el-form-item label="选项A" required :prop="'questions.'+index+'.optionA'">
            <el-input v-model="question.optionA" type="text"/>
          </el-form-item>
          <el-form-item label="选项B" required :prop="'questions.'+index+'.optionB'">
            <el-input v-model="question.optionB" type="text"/>
          </el-form-item>
          <el-form-item label="选项C" required :prop="'questions.'+index+'.optionC'">
            <el-input v-model="question.optionC" type="text"/>
          </el-form-item>
          <el-form-item label="选项D" required :prop="'questions.'+index+'.optionD'">
            <el-input v-model="question.optionD" type="text"/>
          </el-form-item>
          <el-form-item label="分数" required :prop="'questions.'+index+'.fraction'">
            <el-input-number v-model="question.fraction" :min="1" :max="100"/>
          </el-form-item>
          <el-form-item label="正确选项" required :prop="'questions.'+index+'.correct'">
            <el-checkbox-group v-model="question.correct">
              <el-checkbox-button label="A" name="correct"/>
              <el-checkbox-button label="B" name="correct"/>
              <el-checkbox-button label="C" name="correct"/>
              <el-checkbox-button label="D" name="correct"/>
            </el-checkbox-group>
          </el-form-item>
        </div>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="release" :loading="loading">确 定</el-button>
      </div>
    </el-dialog>


  </div>
</template>

<script>
  import {postTest, getTest} from '@/api/courseTest'
  import {Notification} from "element-ui";

  export default {
    name: "Test",
    props: ['courseId'],
    data() {
      return {
        dialogFormVisible: false,
        num: 1,
        loading: false,
        drawer: false,
        testList: [],
        form: {
          courseId: this.courseId,
          title: '',
          deadline: '',
          allowNum: 1,
          timeLimit: 0,
          questions: [
            {
              subjectName: '',
              optionA: '',
              optionB: '',
              optionC: '',
              optionD: '',
              fraction: '',
              correct: []
            }
          ]
        }
      }
    },
    created() {
      getTest(this.courseId).then(res => {
        this.testList = res.data
      })
    },
    methods: {
      seeNotice(id) {
        this.$router.push("/course_details/test/" + id)
      },
      release() {
        this.$refs["testForm"].validate((valid) => {

          if (valid) {
            postTest(this.form).then(res => {
              Notification.success({
                title: '发布成功',
                message: '测试发布成功'
              });
              this.dialogFormVisible = false
              getTest(this.courseId).then(res => {
                this.testList = res.data
              })
              this.$refs["testForm"].resetFields();
            })
          } else {
            return false;
          }
        });
      },
      changeNum(value, old) {
        let question = {
          subjectName: '',
          optionA: '',
          optionB: '',
          optionC: '',
          optionD: '',
          fraction: '',
          correct: []
        }
        if (value > old) {
          this.form.questions.push(question)
        }
        if (value < old) {
          this.form.questions.splice(value, 1)
        }
      }
    }
  }
</script>

<style scoped>

</style>
