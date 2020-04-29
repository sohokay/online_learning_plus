<template>
  <div>
    <el-row :gutter="20">
      <el-col :span="6" v-for="( discuss,index) in  discussList" :key="index">
        <el-card style="margin-bottom: 4px">
          <div slot="header" class="clearfix">
            <span>{{ discuss.title}}</span>
            <el-button style="float: right; padding: 3px 5px" type="text" @click="seeDiscuss(discuss.discussId)">查看
            </el-button>
            <el-popconfirm confirmButtonText='确定'
                           cancelButtonText='取消'
                           icon="el-icon-info"
                           iconColor="red"
                           title="确定删除此讨论帖子吗？" @onConfirm="deleted(discuss.discussId,index)">
              <el-button style="float: right; padding: 3px 5px;color: red" type="text" slot="reference">
                删除
              </el-button>
            </el-popconfirm>
          </div>
          <i class="el-icon-time" style="margin-right: 6px"/>{{ discuss.time}}
        </el-card>
      </el-col>
    </el-row>
    <el-row style="margin-top: 8px">
      <el-col :offset="22">
        <el-button type="primary" @click="dialogFormVisible = true">发布</el-button>
      </el-col>
    </el-row>

    <!-- 发布新帖子 -->
    <el-dialog title="新讨论帖子发布" :visible.sync="dialogFormVisible">
      <el-form :model="form">
        <el-form-item label="帖子标题">
          <el-input v-model="form.title" type="text" maxlength="32" show-word-limit/>
        </el-form-item>
        <aside style="margin-bottom: 4px;font-weight: bold">
          帖子内容
        </aside>
        <tinymce ref="content" :is-up-load="true" v-model="form.content" :height="300"/>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="release" :loading="loading">确 定</el-button>
      </div>
    </el-dialog>
    <!--展示讨论内容-->
    <el-dialog
      :title="discuss.title"
      :visible.sync="drawer"
      width="40%"
      style="padding: 10px">
      <div>
        <el-row>
          <el-col :span="2">
            <el-avatar :src="discuss.userInfo.portrait"/>
          </el-col>
          <el-col :span="4">
            <div style="margin-top: 2px">{{discuss.users.userName}}</div>
            <div style="margin-top: 2px">{{discuss.time}}</div>
          </el-col>
          <el-col v-html="discuss.content"/>
        </el-row>
        <el-divider/>
        <el-row style="font-size: 16px;color: #7d7d7d;margin-bottom: 6px">
          全部评论
        </el-row>
        <el-row style="font-size: 16px;color: #7d7d7d" v-if="discuss.discussReplies.length===0">
          暂无
        </el-row>
        <el-row v-else v-for=" replie in discuss.discussReplies">
          <el-col :span="2">
            <el-avatar :src="replie.portrait"/>
          </el-col>
          <el-col :span="4">
            <div style="margin-top: 2px">{{replie.userName}}</div>
            <div style="margin-top: 2px">{{replie.time}}</div>
          </el-col>
          <el-col :offset="2" v-html="replie.content"/>
          <el-col>
            <el-divider/>
          </el-col>
        </el-row>
        <el-row style="margin-top: 20px">
          <el-col style="margin-bottom: 10px">
            发布评论
          </el-col>
          <el-col :span="18">
            <el-input v-model="replyForm.content" type="textarea"/>
          </el-col>
          <el-col :span="4" :offset="2">
            <el-button :loading="loadingReply" type="primary" style="bottom: 0px;position: absolute"
                       @click="releaseReply(discuss.discussId)">
              发布
            </el-button>
          </el-col>
        </el-row>

      </div>
      <span slot="footer" class="dialog-footer">
    <el-button @click="drawer = false" type="info">关 闭</el-button></span>
    </el-dialog>
  </div>
</template>
<script>
  import Tinymce from '@/components/Tinymce'
  import {getDiscuss, deleteDiscuss, postDiscuss, getDiscussByID, postReply} from '@/api/discuss.js'
  import {Notification} from "element-ui";


  export default {
    name: "Discuss",
    props: ['courseId'],
    components: {Tinymce},
    created() {
      getDiscuss(this.courseId).then(res => {
        this.discussList = res.data
      })
    },
    data() {
      return {
        discuss: {

          userInfo: {
            portrait: ''
          },
          users: {
            userName: ''
          },
          discussReplies: []
        },
        replyForm: {
          content: '',
          discussId: ''
        },
        drawer: false,
        //展示讨论内容
        dialogFormVisible: false,
        loading: false,
        loadingReply: false,
        discussList: [],
        form: {
          title: '',
          content: '',
          courseId: ''

        }
      }
    },
    methods: {
      /**
       * 查看具体帖子
       * @param id
       */
      seeDiscuss(id) {
        this.drawer = true
        getDiscussByID(id).then(res => {
          this.discuss = res.data
        })
      },

      deleted(discussId, index) {
        deleteDiscuss(discussId).then(res => {
          Notification.success({
            title: '成功',
            message: res.data || '成功'
          });
          this.discussList.splice(index, 1)
        })
      },
      release() {
        this.loading = true;
        this.form.courseId = this.courseId
        if (this.form.title.trim() !== '' && this.form.content.trim() !== '') {
          postDiscuss(this.form).then(res => {
            Notification.success({
              title: '成功',
              message: '发布成功'
            });
            getDiscuss(this.courseId).then(res => {
              this.discussList = res.data
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
      /**
       *
       * @param discussId
       */
      releaseReply(discussId) {
        if (this.replyForm.content.trim() !== '') {
          this.loadingReply = true;
          this.replyForm.discussId = discussId
          postReply(this.replyForm).then(res => {
            Notification.success({
              title: '成功',
              message: '发布成功'
            });
            this.replyForm.content = ''
            getDiscussByID(discussId).then(res => {
              this.discuss = res.data
              this.loadingReply = false;
            })
          })
        }
      }
    }
  }
</script>

<style scoped>

</style>
