<template>
  <div class="app-container">
    <el-row :gutter="20">
      <el-col :span="12">
        <el-card class="box-card">
          <el-form ref="infoForm" :model="infoForm" label-width="120px">
            <h3 style="margin-left: 60px">基本信息</h3>
            <el-form-item label="用户名" :rules="{
      required: true, message: '用户名不能为空', trigger: 'blur'
    }" prop="userName">
              <el-input v-model="infoForm.userName"/>
            </el-form-item>
            <el-form-item label="头像" :rules="{
      required: true, message: '头像不能为空', trigger: 'blur'
    }" prop="portrait">
              <el-upload
                class="avatar-uploader"
                action
                :show-file-list="true"
                :http-request="upImg"
                :before-upload="beforeAvatarUpload">
                <img v-if="infoForm.portrait" :src="infoForm.portrait" class="avatar">
                <i v-else class="el-icon-plus avatar-uploader-icon"/>
              </el-upload>
            </el-form-item>
            <el-form-item label="性别" required>
              <el-radio-group v-model="infoForm.sex">
                <el-radio label="男">男</el-radio>
                <el-radio label="女">女</el-radio>
                <el-radio label="保密">保密</el-radio>
              </el-radio-group>
            </el-form-item>
            <el-form-item label="生日" :rules="{
      required: true, message: '生日不能为空', trigger: 'blur'
    }" prop="birthday">
              <el-date-picker
                v-model="infoForm.birthday"
                type="date"
                placeholder="选择日期">
              </el-date-picker>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="onSubmitInfo">修改</el-button>
            </el-form-item>
          </el-form>
        </el-card>
      </el-col>
      <el-col :span="10">
        <el-card>
          <el-form ref="passwordForm" :model="passwordForm" label-width="120px" :rules="passwordRule">
            <h3 style="margin-left: 60px">修改密码</h3>
            <el-form-item label="验证码" prop="code">
              <el-input v-model="passwordForm.code">
                <template slot="append">
                  <el-button @click="getPasswordVerification">获取</el-button>
                </template>
              </el-input>
            </el-form-item>
            <el-form-item label="密码" prop="password">
              <el-input type="password" v-model="passwordForm.password"/>
            </el-form-item>
            <el-form-item label="重复密码" prop="rePassword">
              <el-input type="password" v-model="passwordForm.rePassword"/>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="onSubmitPassword">修改</el-button>
            </el-form-item>
          </el-form>
        </el-card>
        <el-card style="margin-top: 10px">
          <el-form ref="mobileForm" :model="mobileForm" label-width="120px" :rules="mobileRule">
            <h3 style="margin-left: 60px">修改手机号</h3>
            <el-form-item label="手机号" prop="mobile">
              <el-input v-model="mobileForm.mobile"/>
            </el-form-item>
            <el-form-item label="验证码" prop="code">
              <el-input v-model="mobileForm.code">
                <template slot="append">
                  <el-button @click="getMobileVerification">获取</el-button>
                </template>
              </el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="onSubmitMobile">修改</el-button>
            </el-form-item>
          </el-form>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
  import {
    getInfo,
    putHead,
    putInfo,
    getPasswordVerification,
    putPassword,
    getMobileVerification,
    putMobile
  } from '@/api/user'
  import {setHead} from '@/utils/setCookies'

  export default {
    created() {
      getInfo().then(res => {
        this.infoForm = res.data
        this.mobileForm.mobile = res.data.mobile.replace(res.data.mobile.slice(3, 7), "****")
        delete this.infoForm.mobile
      })
    },
    data() {
      // 密码验证规则
      const validatePassword = (rule, value, callback) => {
        if (value.length < 6) {
          callback(new Error('密码至少6位'))
        } else {
          callback()
        }
      }
      const validateRePassword = (rule, value, callback) => {
        if (this.passwordForm.password !== value) {
          callback(new Error('重复密码错误'))
        } else {
          callback()
        }
      }

      const validateCode = (rule, value, callback) => {
        if (value.length < 6) {
          callback(new Error('请输入6位验证码'))
        } else {
          callback()
        }
      }
      const validateMobile = (rule, value, callback) => {
        if (!/^1[3456789]\d{9}$/.test(value)) {
          callback(new Error('请输入正确的手机号'))
        } else {
          callback()
        }
      }
      return {
        infoForm: {
          userName: null,
          portrait: null,
          sex: '男',
          birthday: '2000-10-1'
        },
        passwordForm: {
          password: '',
          rePassword: '',
          code: ''
        },
        passwordRule: {
          password: [{required: true, trigger: 'blur', validator: validatePassword}],
          rePassword: [{required: true, trigger: 'blur', validator: validateRePassword}],
          code: [{required: true, trigger: 'blur', validator: validateCode}],
        },

        mobileRule: {
          mobile: [{required: true, trigger: 'blur', validator: validateMobile}],
          code: [{required: true, trigger: 'blur', validator: validateCode}],
        },
        mobileForm: {
          mobile: '',
          code: ''
        },
      }
    },
    methods: {
      onSubmitInfo() {
        this.$refs.infoForm.validate(valid => {
          if (valid) {
            putInfo(this.infoForm).then(res => {
              this.$message({
                message: res.data,
                type: 'success'
              })
              this.$store.commit('user/SET_IMG', this.infoForm.portrait)
              setHead(this.infoForm.portrait)
            })
          }
        })
      },
      onSubmitPassword() {
        this.$refs.passwordForm.validate(valid => {
          if (valid) {
            putPassword(this.passwordForm).then(res => {
              this.$message({
                message: res.data,
                type: 'success'
              })
              this.$refs.passwordForm.resetFields()

            })
          }
        })
      },
      onSubmitMobile() {
        this.$refs.mobileForm.validate(valid => {
            if (valid) {
              putMobile(this.mobileForm).then(res => {
                this.$message({
                  message: res.data,
                  type: 'success'
                })
                this.mobileForm.code=''
                this.mobileForm.mobile =  this.mobileForm.mobile.replace( this.mobileForm.mobile.slice(3, 7), "****")

                }
              )
            }
          }
        )
      },
      getPasswordVerification() {
        getPasswordVerification().then(res => {
          this.$message({
            message: res.data,
            type: 'success'
          })
        })
      }
      ,
      getMobileVerification() {
        getMobileVerification(this.mobileForm.mobile).then(res => {
          this.$message({
            message: res.data,
            type: "success"
          })
        })
      }
      ,
      onCancel() {
        this.$message({
          message: 'cancel!',
          type: 'warning'
        })
      }
      ,
      upImg(file) {
        let f = new FormData();
        f.append("portrait", file.file)
        putHead(f).then(res => {
          this.infoForm.portrait = res.data

        })
      }
      ,
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

