<template>
  <div>
    <el-row :gutter="20">
      <el-col :span="6" v-for="(notice,index) in noticeList" :key="index">
        <el-card style="margin-bottom: 4px">
          <div slot="header" class="clearfix">
            <span>{{notice.title}}</span>
            <el-button style="float: right; padding: 3px 5px" type="text" @click="seeNotice(notice.noticeId)">查看
            </el-button>
            <el-popconfirm confirmButtonText='确定'
                           cancelButtonText='取消'
                           icon="el-icon-info"
                           iconColor="red"
                           title="确定删除此公告嘛" @onConfirm="deleted(notice.noticeId,index)">
              <el-button style="float: right; padding: 3px 5px;color: red" type="text" slot="reference">
                删除
              </el-button>
            </el-popconfirm>
          </div>
          <i class="el-icon-time" style="margin-right: 6px"/>{{notice.time}}
        </el-card>
      </el-col>
    </el-row>
    <el-row style="margin-top: 8px">
      <el-col :offset="22">
        <el-button type="primary" @click="dialogFormVisible = true">发布</el-button>
      </el-col>
    </el-row>
    <!--    展示公告内容-->
    <el-drawer
      :title="notice.title"
      :visible.sync="drawer"
      direction="rtl"
    >
      <div v-html="notice.content" style="padding: 6px;margin: 10px"></div>
    </el-drawer>
    <!--    发布公告内容-->
    <el-dialog title="新公告发布" :visible.sync="dialogFormVisible">
      <el-form :model="form">
        <el-form-item label="公告标题">
          <el-input v-model="form.title" type="text" maxlength="32" show-word-limit/>
        </el-form-item>
        <aside style="margin-bottom: 4px;font-weight: bold">
          公告内容
        </aside>
        <tinymce ref="content" :is-up-load="false" v-model="form.content" :height="300"/>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="release" :loading="loading">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import Tinymce from '@/components/Tinymce'
  import {getNotice, deleteNotice, getNoticeById, postNotice} from '@/api/courseDetail.js'
  import {Notification} from "element-ui";

  export default {
    name: "Notice",
    components: {Tinymce},
    created() {
      getNotice(this.courseId).then(res => {
        this.noticeList = res.data
      })

    },
    props: ["courseId"],
    data() {
      return {
        noticeList: [],
        drawer: false,
        dialogFormVisible: false,
        form: {
          title: '',
          content: '',
          courseId: ''
        },
        notice: {
          title: '',
          content: ''
        },
        loading: false
      }
    },
    methods: {
      deleted(noticeId, index) {
        deleteNotice(noticeId).then(res => {
          this.noticeList.splice(index, 1)
          Notification.success({
            title: '成功',
            message: res.data || '成功'
          });
        })
      },
      release() {
        this.loading = true;
        this.form.courseId = this.courseId
        if (this.form.title.trim() !== '' && this.form.content.trim() !== '') {
          postNotice(this.form).then(res => {
            Notification.success({
              title: '成功',
              message: '发布成功'
            });
            getNotice(this.courseId).then(res => {
              this.noticeList = res.data
              this.form.title = ''
              this.form.content = ''
              this.$refs.content.setContent("");
            })
            this.dialogFormVisible = false
          })
        } else {
          Notification.error({
            title: '错误',
            message: '请认真填写内容'
          });
          this.loading = false;
        }
      },
      seeNotice(id) {
        getNoticeById(id).then(res => {
          this.notice.title = res.data.title
          this.notice.content = res.data.content
        })
        this.drawer = true
      }
    }
  }
</script>

<style scoped>

</style>
