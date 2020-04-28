<template>
  <div class="app-container">
    <el-row :gutter="30">
      <el-col :span="8" v-for="course in courseList" :key="course.courseId">
        <el-card shadow="always" style="margin-top: 10px">
          <div slot="header">
            <span>{{course.courseName}}</span>
            <el-button style="float: right" type="primary" size="mini" @click="toDetail(course.courseId)">进入详情
            </el-button>
            <el-button type="danger" style="float: right;margin-right: 10px" size="mini"
                       @click="deleteCourse(course.courseName,course.courseId)">删除
            </el-button>
          </div>
          <el-image
            style="width: 100px; height: 100px"
            :src="course.courseImg"
          />
          <div style="float: right;line-height: 30px;margin-right: 20px">
            <svg class="icon" aria-hidden="true">
              <use xlink:href="#icontype"/>
            </svg>
            {{course.courseType}}
            <br/>
            <svg class="icon" aria-hidden="true">
              <use xlink:href="#iconshijian1"/>
            </svg>
            {{course.creationTime}}
            <br/>
            <svg class="icon" aria-hidden="true">
              <use xlink:href="#iconren"/>
            </svg>
            {{course.studentNum}}人报名
          </div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
  import {getCourse, deleteCourse} from '@/api/course'
  import {MessageBox} from "element-ui";
  import store from "@/store";

  export default {
    created() {
      getCourse().then(res => {
        this.courseList = res.data
      })
    },
    data() {
      return {
        courseList: []
      }
    },
    methods: {
      deleteCourse(courseName, courseId) {
        MessageBox.confirm(`是否删除课程《${courseName}》？将会删除此课程所有信息。`, '确认删除', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          deleteCourse(courseId).then(res => {
            this.$notify.success({
              title: '成功',
              message: res.data,
              showClose: false
            });
            this.deleteCourseListById(courseId)
          })
        })
      },
      deleteCourseListById(courseId) {
        let i = 0;
        for (i; i < this.courseList.length; i++) {
          if (courseId === this.courseList[i].courseId) {
            console.log(i)
            break;
          }
        }
        this.courseList.splice(i, 1)
      },
      toDetail(courseId) {
        this.$router.push("/course_details/info/" + courseId)
      }
    }
  }
</script>
<style scoped>
  .icon {
    width: 1em;
    height: 1em;
    vertical-align: -0.15em;
    fill: currentColor;
    overflow: hidden;
  }

  .line {
    text-align: center;
  }
</style>

