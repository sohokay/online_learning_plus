<template>
  <div class="app-container">
    <el-form ref="form" :model="form" label-width="120px">
      <el-form-item label="用户名">
        <el-input v-model="form.adminName"/>
      </el-form-item>
      <el-form-item label="头像">
        <el-upload
          class="avatar-uploader"
          action
          :show-file-list="true"
          :http-request="upImg"
          :on-success="handleAvatarSuccess"
          :before-upload="beforeAvatarUpload">
          <img v-if="form.imgUrl" :src="form.imgUrl" class="avatar">
          <i v-else class="el-icon-plus avatar-uploader-icon"></i>
        </el-upload>
      </el-form-item>
      <el-form-item label="密码">
        <el-input v-model="form.name"/>
      </el-form-item>
      <el-form-item label="重复密码">
        <el-input v-model="form.name"/>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit">修改</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
  import {getUserName} from '../../utils/setCookies'
  import request from '../../utils/request'

  export default {
    data() {
      return {
        form: {
          imgUrl: '',
          adminName: getUserName(),
          adminPassword: '',
          reAdminPassword: ''
        }
      }
    },
    methods: {
      onSubmit() {
        this.$message('submit!')
      },
      onCancel() {
        this.$message({
          message: 'cancel!',
          type: 'warning'
        })
      },
      upImg(file) {
        let f = new FormData();
        f.append("img", file.file)
        request({
          url: 'http://localhost:8080/upimg',
          method: 'post',
          data: f
        }).then(res => {
          this.form.imgUrl = 'http://q68t4j4m6.bkt.clouddn.com/' + res.msg
        })
      },
      handleAvatarSuccess(res, file) {
        this.imageUrl = URL.createObjectURL(file.raw);
      },
      beforeAvatarUpload(file) {
        const isJPG = file.type === 'image/jpeg';
        const isLt2M = file.size / 1024 / 1024 < 2;
        if (!isJPG) {
          this.$message.error('上传头像图片只能是 JPG 格式!');
        }
        if (!isLt2M) {
          this.$message.error('上传头像图片大小不能超过 2MB!');
        }
        return isJPG && isLt2M;
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
</style>

