<template>
  <div class="login-container">
    <!--:rules定义验证规则-->
    <el-form ref="phoneLoginForm" :model="phoneLoginForm" :rules="loginRules" class="login-form" auto-complete="on"
             label-position="left">
      <div class="title-container">
        <h3 class="title">随心在线学习系统——教师手机登录</h3>
      </div>
      <el-form-item prop="mobile">
        <span class="svg-container">
          <svg-icon icon-class="手机"/>
        </span>
        <el-input
          ref="mobile "
          v-model="phoneLoginForm.mobile "
          placeholder="手机号"
          name="mobile"
          type="text"
          tabindex="1"
          auto-complete="on"
        />
      </el-form-item>
      <el-form-item prop="code">
        <span class="svg-container">
          <svg-icon icon-class="password"/>
        </span>
        <el-input
          ref="code"
          v-model="phoneLoginForm.code"
          placeholder="验证码"
          name="code"
          tabindex="2"
          auto-complete="on"
          @keyup.enter.native="handleLogin"
        />
        <span class="show-img">
           <el-button round @click="getCode" :disabled="isGet">获取{{time}}</el-button>
        </span>
      </el-form-item>
      <el-button :loading="loading" type="primary" style="width:100%;margin-bottom:30px;"
                 @click.native.prevent="handleLogin">登录
      </el-button>
      <el-button type="success" style="width:100%;margin-bottom:30px;margin-left: 0px"
                 @click="toRegister">注册
      </el-button>
      <el-button type="warning" style="width:100%;margin-bottom:30px;margin-left: 0px"
                 @click="toLogin">账户密码登录
      </el-button>
    </el-form>
  </div>
</template>

<script>

  import {getLoginCode} from "@/api/user";

  export default {
    name: 'Login',
    data() {
      // 密码验证规则
      const validateCode = (rule, value, callback) => {
        if (value.length !== 6) {
          callback(new Error('请输入六位验证码'))
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
        phoneLoginForm: {
          mobile: '',
          code: '',
        },
        loginRules: {
          mobile: [{required: true, trigger: 'blur', validator: validateMobile}],
          code: [{required: true, trigger: 'blur', validator: validateCode}]
        },
        loading: false,
        passwordType: 'password',
        redirect: undefined,
        time: '',
        isGet: false,
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
      // 登录操作
      handleLogin() {
        this.$refs.phoneLoginForm.validate(valid => {
          if (valid) {
            this.loading = true
            this.$store.dispatch('user/phoneLogin', this.phoneLoginForm).then(() => {
              this.$router.push({path: this.redirect || '/'})
              this.loading = false
            }).catch(() => {
              this.loading = false
            })
          }
        })
      },
      toRegister() {
        this.$router.push("/register")
      },
      toLogin() {
        this.$router.push("/login")
      },
      getCode() {
        if (/^1[3456789]\d{9}$/.test(this.phoneLoginForm.mobile)) {
          getLoginCode(this.phoneLoginForm.mobile).then(res => {
            this.time = 60
            this.isGet = true
            localStorage.setItem("codeTime", new Date().valueOf())
            let interval = setInterval(() => {
              this.time--
              if (this.time === 0) {
                clearInterval(interval)
                this.isGet = false
                this.time = ''
              }
              ;
            }, 1000)
            this.$message.success(res.data)
            // this.isAllow = false
          });
        } else {
          this.$message.error('请输入正确的手机号');
        }
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
    background: url("http://img.musuixin.top/login-phone-bj.jpg") no-repeat;
    background-size: 100% 100%;
    overflow: hidden;

    .login-form {
      position: relative;
      width: 520px;
      max-width: 100%;
      padding: 160px 35px 0;
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
