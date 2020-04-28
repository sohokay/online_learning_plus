<template>
  <div class="app-container">
    <div>
      <aside>
        课程名称
      </aside>
      <el-input type="text" maxlength="16" show-word-limit v-model="course.courseName"/>
    </div>
    <div>
      <aside>
        课程类型
      </aside>
      <el-select v-model="course.courseType" placeholder="请选择">
        <el-option label="计算机" value="计算机"/>
        <el-option label="外语" value="外语"/>
        <el-option label="理学" value="理学"/>
        <el-option label="工学" value="工学"/>
        <el-option label="文史哲" value="文史哲"/>
        <el-option label="其他" value="其他"/>
      </el-select>
    </div>
    <div>
      <aside>
        课程封面
      </aside>
      <el-upload
        class="avatar-uploader"
        action
        :show-file-list="true"
        :http-request="upImg"
        :before-upload="beforeAvatarUpload">
        <img v-if="course.courseImg" :src="course.courseImg" class="avatar">
        <i v-else class="el-icon-plus avatar-uploader-icon"/>
      </el-upload>
    </div>
    <div>
      <el-row>
        <el-col :span="4">
          <aside>
            课程开始时间
          </aside>
          <el-date-picker
            v-model="course.startTime"
            type="date"
            placeholder="选择日期">
          </el-date-picker>
        </el-col>
        <el-col :span="4">
          <aside>
            课程结束时间
          </aside>
          <el-date-picker
            v-model="course.endTime"
            type="date"
            placeholder="选择日期">
          </el-date-picker>
        </el-col>
      </el-row>
    </div>
    <div>
      <aside>
        课程简介
      </aside>
      <tinymce v-model="course.briefIntroduction" :height="300"/>
    </div>
    <div>
      <aside>
        课程概述(可选)
      </aside>
      <tinymce v-model="course.summary" :height="300"/>
    </div>
    <div>
      <aside>
        授课目标(可选)
      </aside>
      <tinymce v-model="course.target" :height="300"/>
    </div>
    <div>
      <aside>
        课程要求(可选)
      </aside>
      <tinymce v-model="course.requirements" :height="300"/>
    </div>
    <div>
      <aside>
        预备知识(可选)
      </aside>
      <tinymce v-model="course.preparatoryKnowledge" :height="300"/>
    </div>
    <div>
      <aside>
        课程大纲(可选)
      </aside>
      <tinymce v-model="course.outline" :height="300"/>
    </div>
    <div>
      <aside>
        参考资料(可选)
      </aside>
      <tinymce v-model="course.referenceMaterial" :height="300"/>
    </div>
    <div>
      <aside>
        常见问题(可选)
      </aside>
      <tinymce v-model="course.commonProblem" :height="300"/>
    </div>
    <el-button style="margin: 20px 0px 30px 0px;" type="success" @click="postCourse">发布</el-button>
  </div>
</template>
<script>
  import Tinymce from '@/components/Tinymce'
  import {postCourse} from '@/api/course'
  import {putHead} from "@/api/user";

  export default {
    name: "add_course",
    components: {Tinymce},
    data() {
      return {
        course: {
          courseName: '',
          courseType: '',
          courseImg: '',
          briefIntroduction: '',
          summary: '',
          target: '',
          startTime: '',
          endTime: '',
          requirements: '',
          preparatoryKnowledge: '',
          outline: '',
          referenceMaterial: '',
          commonProblem: '',
        }
      }
    },
    methods: {
      getHtml() {
        this.html = this.$refs.markdownEditor.getHtml()
        console.log(this.html)
      },
      postCourse() {
        postCourse(this.course).then(res=>{
          this.$notify.success({
            title: '成功',
            message: res.data,
            showClose: false
          });
          this.$router.go(0)
        })
      },
      upImg(file) {
        let f = new FormData();
        f.append("portrait", file.file)
        putHead(f).then(res => {
          this.course.courseImg= res.data
        })
      }
    }
  }
</script>

<style scoped>
  .avatar-uploader {
    width: 178px;
    height: 178px;
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }

  .avatar-uploader:hover {
    border-color: #409EFF;
  }

  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    line-height: 178px;
    text-align: center;
  }

  .avatar {
    width: 178px;
    height: 178px;
    display: block;
  }


  aside {
    font-size: 22px;
    font-family: Helvetica Neue, Helvetica, PingFang SC, Hiragino Sans GB, Microsoft YaHei, Arial, sans-serif;
    margin-bottom: 6px;
    margin-top: 6px;
    color: #8492A6;
  }
</style>
