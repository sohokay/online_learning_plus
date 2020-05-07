<template>
  <div>

    <el-row :gutter="20">
      <el-col :span="6" v-for="(courseware,index) in coursewareList">
        <el-card style="height: 260px">
          <div slot="header">
            <span>{{courseware.title}}</span>
            <el-popconfirm confirmButtonText='确定'
                           cancelButtonText='取消'
                           icon="el-icon-info"
                           iconColor="red"
                           title="确定删除此课件嘛" @onConfirm="deleted(courseware.coursewareId,index)">
              <el-button style="float: right; padding: 3px 5px;color: red" type="text" slot="reference">
                删除
              </el-button>
            </el-popconfirm>
          </div>
          <div v-for="content in courseware.contents"
               style=" margin-bottom: 2px"> {{content.name}} <a :href="content.url" style="color: #0086b3">进入</a><br/></div>
        </el-card>
      </el-col>
      <el-col :span="2" :offset="22" style="margin-top: 8px">
        <el-button @click="dialogFormVisible=true">发布课件</el-button>
      </el-col>
    </el-row>
    <el-dialog title="发布课件" :visible.sync="dialogFormVisible">
      <el-form :model="courseware">
        <el-form-item label="章节标题">
          <el-input v-model="courseware.title" autocomplete="off"/>
        </el-form-item>
        <p>课件数</p>
        <el-input-number v-model="num" :min="1" :max="5"/>
        <el-divider/>
        <el-upload
          class="upload-demo"
          action="/"
          :on-remove="handleRemove"
          :before-upload="beforeAvatarUpload"
          :accept="'.pdf'"
          multiple
          :http-request="upFile"
          :limit="num"
          :on-exceed="handleExceed"
          :file-list="list">
          <el-button size="small" type="primary">点击上传</el-button>
          <div slot="tip" class="el-upload__tip">只能上传pdf文件</div>
        </el-upload>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="release">发 布</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import {upCourseware, postCourseware, getCourseware, deleteCourseware} from '@/api/courseDetail'
  import {Notification} from "element-ui";

  export default {
    name: "Courseware",
    created() {
      getCourseware(this.courseId).then(res => {
        this.coursewareList = res.data
      })
    },
    computed: {
      list: function () {
        return this.courseware.contents
      }
    },
    props: ['courseId'],
    data() {
      return {
        coursewareList: [],
        dialogFormVisible: false,
        num: 1,
        courseware: {
          title: '',
          courseId: '',
          contents: []
        }
      }
    },
    methods: {
      deleted(id, index) {
        deleteCourseware(id).then(res => {
          this.coursewareList.splice(index, 1)
          Notification.success({
            title: '成功',
            message: '删除成功'
          });

        })

      },
      upFile(file) {
        let formData = new FormData();
        formData.append("courseware", file.file)
        upCourseware(formData).then(res => {
          let content = {name: file.file.name, url: res.data}
          this.courseware.contents.push(content)
        });
      },
      handleRemove(file, fileList) {
        let list = this.courseware.contents
        for (let i = 0; i < list.length; i++) {
          if (list[i].uid === file.uid) {
            this.list.splice(i, 1)
          }
        }
      },
      handleExceed(files, fileList) {
        this.$message.warning(`当前限制选择 ${this.num} 个文件`);
      },
      beforeAvatarUpload(file) {
        const isPDF = file.type === 'application/pdf';
        if (!isPDF) {
          this.$message.error('上传课件只能是  PDF 格式!');
        }
        return isPDF;
      },
      release() {
        if (this.courseware.title.trim() !== '' && this.courseware.contents.length !== 0) {
          this.courseware.courseId = this.courseId
          postCourseware(this.courseware).then(res => {
            getCourseware(this.courseId).then(res => {
              this.coursewareList = res.data
            })
          })
          this.dialogFormVisible = false
        }
      }

    }
  }
</script>

<style scoped>

</style>
