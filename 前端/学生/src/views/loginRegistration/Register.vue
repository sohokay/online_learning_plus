<template>
    <div class="bimg">
        <img src="../../static/OnlineLearning.png" style="height: 110px;width: 120px;margin-bottom: 8%">
        <van-form @submit="onSubmitRegister" class="lform">
            <van-field
                    v-model="form.studentname"
                    name="用户名"
                    label="用户名"
                    placeholder="用户名"
                    :rules="[{ required: true, message: '请填写用户名' }]"
            />
            <van-field
                    v-model="form.studentphone"
                    name="手机号"
                    label="手机号"
                    placeholder="手机号"
                    :rules="[{ required: true, message: '请填写手机号' }]"
            >
                <van-button :disabled="isAble" slot="button" size="small" type="primary" @click="sendCode">发送验证码{{time}}</van-button>
            </van-field>
            <van-field
                    v-model="form.studentphone"
                    name="验证码"
                    label="验证码"
                    placeholder="验证码"
                    :rules="[{ required: true, message: '请填写验证码' }]"
            >
            </van-field>
            <van-field
                    v-model="form.password"
                    type="password"
                    name="密码"
                    label="密码"
                    placeholder="密码"
                    :rules="[{ required: true, message: '请填写密码' }]"
            />
            <van-field
                    v-model="form.repassword"
                    type="password"
                    name="重复密码"
                    label="重复密码"
                    placeholder="重复密码"
                    :rules="[{ required: true, message: '请填写密码' }]"
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
    import { Notify } from 'vant';

    Vue.use(Notify);
    export default {
        name: 'Register',
        data() {
            return {
                time:'',
                isAble:false,
                form: {
                    studentname: '',
                    studentphone: '',
                    Verification: '',
                    password: '',
                    repassword: ''
                }
            }

        },
        created() {
            console.log(new Date().valueOf()-localStorage.getItem("codeTime"))
            var jgtime=parseInt((new Date().valueOf()-localStorage.getItem("codeTime"))/1000)
            console.log(jgtime)
            if(jgtime<60){
                this.time=60-jgtime;
                this.isAble=true
                let interval = setInterval(()=>{
                    this.time--
                    if(this.time===0){
                        clearInterval(interval)
                        this.isAble=false
                        this.time=''
                    }
                },1000);
            }
        },
        methods: {
            onSubmitRegister() {
                alert("hello")
            }, toLogin() {
                this.$router.push("/login")
            }, sendCode() {
                this.time=60
                this.isAble=true
                localStorage.setItem("codeTime",new Date().valueOf())
                let interval = setInterval(()=>{
                    this.time--
                    if(this.time===0){
                        clearInterval(interval)
                        this.isAble=false
                        this.time=''
                    }
                },1000);
                Notify({ type: 'success', message: '通知内容' });
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
