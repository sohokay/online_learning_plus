<template>
    <div class="bimg">
        <img src="../../static/OnlineLearning.png" style="height: 110px;width: 120px;margin-bottom: 8%">
        <van-form @submit="onSubmitRegister" class="lform">
            <van-field
                    v-model="form.userName"
                    name="用户名"
                    label="用户名"
                    :rules="[{validator:usernamePattern, message: '仅可包含汉字、数字、字母、_ 长度少于十位' }]"
            />
            <van-field
                    v-model="form.mobile"
                    name="手机号"
                    label="手机号"
                    :rules="[{validator:phonePattern, message: '请输入正确手机号 '}]"
            >
                <van-button :disabled="isAble" native-type="button" slot="button" size="small" type="primary" @click="sendCode">
                    发送验证码{{time}}
                </van-button>
            </van-field>
            <van-field
                    v-model="form.verification"
                    name="验证码"
                    label="验证码"
                    :rules="[{ required: true, message: '请填写验证码' }]"
            >
            </van-field>
            <van-field
                    v-model="form.password"
                    type="password"
                    name="密码"
                    label="密码"
                    :rules="[{ validator:passwordPattern, message: '密码为英文，数字下划线组合，6-14位' }]"
            />
            <van-field
                    v-model="form.rePassword"
                    type="password"
                    name="重复密码"
                    label="重复密码"
                    :rules="[{ validator:rePasswordPattern, message: '两次输入的密码不一致' }]"
            />
            <div>
                <van-button round block type="info" native-type="submit">
                    注册
                </van-button>
            </div>
            <p style="padding-top: 5%;color: #0086b3" @click="toLogin">已有账号？立即登录</p>
        </van-form>
    </div>
</template>
<script>
    import Vue from 'vue';
    import {Notify} from 'vant';
    import request from '@/utils/request'
    let url='http://login_register_api.musuixin.cn/v1'

    Vue.use(Notify);
    export default {
        name: 'Register',
        data() {
            return {
                time: '',
                isAble: false,
                form: {
                    userName: '',
                    mobile: '',
                    verification: '',
                    password: '',
                    rePassword: ''
                },
                isAllow: true
            }
        },
        created() {
            /**
             * 获取手机验证码是否在60s内重复
             */
            console.log(new Date().valueOf() - localStorage.getItem("codeTime"));
            var jgtime = parseInt((new Date().valueOf() - localStorage.getItem("codeTime")) / 1000);
            console.log(jgtime);
            if (jgtime < 60) {
                this.time = 60 - jgtime;
                this.isAble = true
                let interval = setInterval(() => {
                    this.time--
                    if (this.time === 0) {
                        clearInterval(interval)
                        this.isAble = false
                        this.time = ''
                    }
                }, 1000);
            }
        },
        methods: {
            /**
             * 手机号验证
             * @param val
             * @returns {boolean}
             */
            phonePattern(val) {
                return /^1(3|4|5|7|8)\d{9}$/.test(val);
            },
            /**
             * 用户名验证
             * @param val
             * @returns {boolean}
             */
            usernamePattern(val) {
                return /^[\u4e00-\u9fa5_a-zA-Z0-9]{1,10}$/.test(val)
            },

            passwordPattern(val) {
                return /^\w{6,14}$/.test(val)
            },
            /**
             * 重复密码是否一致验证
             * @param val
             * @returns {boolean}
             */
            rePasswordPattern(val) {
                return val === this.form.password

            },
            /**
             * 注册逻辑
             */
            onSubmitRegister() {
                request.post(url+"/register", this.form).then(res => {
                    Notify({type: 'success', message: '注册成功，跳转登录'});
                    this.$router.push('/login')
                })
            },
            /**
             * 跳转登录页面
             */
            toLogin() {
                this.$router.push("/login")
            },
            sendCode() {
                if (this.phonePattern(this.form.mobile)) {
                    request.get(url+"/verification", {
                        params: {
                            mobile: this.form.mobile
                        }
                    }).then(res => {
                        this.time = 60
                        this.isAble = true
                        localStorage.setItem("codeTime", new Date().valueOf())
                        let interval = setInterval(() => {
                            this.time--
                            if (this.time === 0) {
                                clearInterval(interval)
                                this.isAble = false
                                this.time = ''
                            }
                        }, 1000);
                        this.isAllow = false
                    });
                } else {
                    Notify({type: 'warning', message: '请输入正确的手机号'});
                }
            }
        }
    }
</script>

<style>
    .bimg {
        position: fixed;
        box-sizing: border-box;
        width: 100%;
        padding: 30% 5% 0;
        background-size: 100% 100%;
        bottom: 0;
        top: 0;
        text-align: center;
        background-image: url("http://edu-image.nosdn.127.net/7559dc1c-6795-473c-bdfc-27ef04a91257.png?imageView&thumbnail=750y1334&quality=100");
    }

</style>
