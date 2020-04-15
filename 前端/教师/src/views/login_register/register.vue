<template>
  <div class="login-container">
    <!--:rules定义验证规则-->
    <el-form ref="registerForm" :model="registerForm" :rules="registerRules" class="login-form" auto-complete="on"
             label-position="left">
      <div class="title-container">
        <h3 class="title">随心在线学习系统——管理员注册</h3>
      </div>
      <el-form-item prop="userName">
        <span class="svg-container">
          <svg-icon icon-class="user"/>
        </span>
        <el-input
          ref="userName"
          v-model="registerForm.userName"
          placeholder="用户名"
          name="userName"
          type="text"
          tabindex="1"
          auto-complete="on"
        />
      </el-form-item>
      <el-form-item prop="mobile">
        <span class="svg-container">
          <svg-icon icon-class="手机"/>
        </span>
        <el-input
          ref="username"
          v-model="registerForm.mobile"
          placeholder="手机号"
          name="username"
          type="text"
          tabindex="1"
          auto-complete="on"
        />
      </el-form-item>
      <!--      prop定义具体验证规则-->
      <el-form-item prop="verification">
        <span class="svg-container">
          <i class="el-icon-more-outline"/>
        </span>
        <el-input
          :key="verificationCodeType"
          ref="verification"
          v-model="registerForm.verification"
          placeholder="验证码"
          name="verification"
          type="text"
          auto-complete="on"
          @keyup.enter.native=" handleRegister"
        />
        <span class="show-img">
           <el-button round @click="getCode" :disabled="isGet">获取{{time}}</el-button>
        </span>
      </el-form-item>

      <el-form-item prop="password">
        <span class="svg-container">
          <svg-icon icon-class="password"/>
        </span>
        <el-input
          :key="passwordType"
          ref="password"
          v-model="registerForm.password"
          :type="passwordType"
          placeholder="密码"
          name="password"
          tabindex="2"
          auto-complete="on"
          @keyup.enter.native=" handleRegister"
        />
        <span class="show-pwd" @click="showPwd">
          <svg-icon :icon-class="passwordType === 'password' ? 'eye' : 'eye-open'"/>
        </span>
      </el-form-item>
      <el-form-item prop="rePassword">
        <span class="svg-container">
          <svg-icon icon-class="password"/>
        </span>
        <el-input
          :key="passwordType"
          ref="rePassword"
          v-model="registerForm.rePassword"
          :type="passwordType"
          placeholder="重复密码"
          name="rePassword"
          tabindex="2"
          auto-complete="on"
          @keyup.enter.native="handleRegister"
        />
        <span class="show-pwd" @click="showPwd">
          <svg-icon :icon-class="passwordType === 'password' ? 'eye' : 'eye-open'"/>
        </span>
      </el-form-item>
      <el-button :loading="loading" type="primary" style="width:100%;margin-bottom:30px;"
                 @click.native.prevent="handleRegister">注册
      </el-button>
      <el-button  type="success" style="width:100%;margin-bottom:30px;margin-left: 0px"
                @click="toLogin" >已有账户？登录
      </el-button>
    </el-form>

  </div>
</template>

<script>
  import {getRegisterCode,register} from '../../api/user'
  import {Message} from "element-ui";
  export default {
    name: 'Register',
    data() {
      // 密码验证规则
      const validatePassword = (rule, value, callback) => {
        if (value.length < 6) {
          callback(new Error('密码至少6位'))
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
      const validateRePassword = (rule, value, callback) => {
        if (this.registerForm.password !== value) {
          callback(new Error('重复密码错误'))
        }else {
          callback()
        }
      }
      return {
        registerForm: {
          userName: '',
          rePassword: '',
          password: '',
          mobile: '',
          verification: ''
        },
        registerRules: {
          userName: [{required: true, trigger: 'blur', message: "请输入用户名"}],
          verification: [{required: true, trigger: 'blur', validator: validateCode}],
          password: [{required: true, trigger: 'blur', validator: validatePassword}],
          mobile: [{required: true, trigger: 'blur', validator: validateMobile}],
          rePassword: [{required: true, trigger: 'blur', validator: validateRePassword}]
        },
        time: '',
        isGet: false,
        loading: false,
        verificationCodeType: 'verificationCode',
        passwordType: 'password',
        redirect: undefined
      }
    },
    watch: {
      $route: {
        handler: function (route) {
          this.redirect = route.query && route.query.redirect
        },
        immediate: true
      }
    },
    methods: {
      getCode() {
        if (/^1[3456789]\d{9}$/.test(this.registerForm.mobile)) {
          getRegisterCode(this.registerForm.mobile).then(res => {
            this.time = 60
            this.isGet = true
            localStorage.setItem("codeTime", new Date().valueOf())
            let interval = setInterval(() => {
              this.time--
              if (this.time === 0) {
                clearInterval(interval)
                this.isGet = false
                this.time = ''
              };
            }, 1000)
            // this.isAllow = false
          });
        } else {
          this.$message.error('请输入正确的手机号');
        }
      },
      showPwd() {
        if (this.passwordType === 'password') {
          this.passwordType = ''
        } else {
          this.passwordType = 'password'
        }
        this.$nextTick(() => {
          this.$refs.password.focus()
        })
      },
      // 注册操作
      handleRegister() {
        this.$refs.registerForm.validate((valid) => {
          if (valid) {
            register(this.registerForm).then(res=>{
              Message({
                message: res.data ,
                type: 'success',
                duration: 2 * 1000
              })
              this.$router.push('/login')
            })
          }
        })
      },
      toLogin(){
        this.$router.push("/login")
      }
    }
  }
</script>

<style lang="scss">
  /* 修复input 背景不协调 和光标变色 */
  /* Detail see https://github.com/PanJiaChen/vue-element-admin/pull/927 */

  $bg: #283443;
  $light_gray: #fff;
  $cursor: #fff;

  @supports (-webkit-mask: none) and (not (cater-color: $cursor)) {
    .login-container .el-input input {
      color: $cursor;
    }
  }

  /* reset element-ui css */
  .login-container {
    .el-input {
      display: inline-block;
      height: 47px;
      width: 85%;

      input {
        background: transparent;
        border: 0px;
        -webkit-appearance: none;
        border-radius: 0px;
        padding: 12px 5px 12px 15px;
        color: $light_gray;
        height: 47px;
        caret-color: $cursor;

        &:-webkit-autofill {
          box-shadow: 0 0 0px 1000px $bg inset !important;
          -webkit-text-fill-color: $cursor !important;
        }
      }
    }

    .el-form-item {
      border: 1px solid rgba(255, 255, 255, 0.1);
      background: rgba(0, 0, 0, 0.1);
      border-radius: 5px;
      color: #454545;
    }
  }
</style>

<style lang="scss" scoped>
  $bg: #2d3a4b;
  $dark_gray: #889aa4;
  $light_gray: #eee;

  .login-container {
    min-height: 100%;
    width: 100%;
    background: url("http://img.musuixin.top/admin-register.jpg") no-repeat;
    background-size: 100% 100%;
    overflow: hidden;

    .login-form {
      position: relative;
      width: 520px;
      max-width: 100%;
      padding: 100px 35px 0;
      margin: 0 auto;
      overflow: hidden;
    }

    .tips {
      font-size: 14px;
      color: #fff;
      margin-bottom: 10px;

      span {
        &:first-of-type {
          margin-right: 16px;
        }
      }
    }

    .svg-container {
      padding: 6px 5px 6px 15px;
      color: $dark_gray;
      vertical-align: middle;
      width: 30px;
      display: inline-block;
    }

    .title-container {
      position: relative;

      .title {
        font-size: 26px;
        color: $light_gray;
        margin: 0px auto 40px auto;
        text-align: center;
        font-weight: bold;
      }
    }

    .show-pwd {
      position: absolute;
      right: 10px;
      top: 7px;
      font-size: 16px;
      color: $dark_gray;
      cursor: pointer;
      user-select: none;
    }

    .show-img {
      position: absolute;
      right: 10px;
      top: 7px;
      font-size: 16px;
      color: $dark_gray;
      cursor: pointer;
      user-select: none;
    }
  }
</style>
