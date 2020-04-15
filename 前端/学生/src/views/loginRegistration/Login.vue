<template>
    <div class="bimg">
        <img src="../../static/OnlineLearning.png" style="height: 110px;width: 120px;margin-bottom: 8%">
        <van-form @submit="onSubmitLogin" validate-first class="lform">
            <van-field
                    v-model="form.mobile"
                    name="pattern"
                    label="手机号"
                    :rules="[{validator:phonePattern, message: '请输入正确手机号 '}]"
            />
            <van-field
                    v-model="form.password"
                    type="password"
                    name="密码"
                    label="密码"
                    :rules="[{ validator:rePasswordPattern, message: '密码为英文，数字下划线组合，6-14位' }]"
            />
            <div>
                <van-button round block type="primary" native-type="submit">
                    {{loginMsg}}
                </van-button>
            </div>
            <p style="padding-top: 5%;color: #42b983" @click="toRegister">未注册？点击这里手机号快速注册</p>
        </van-form>
        <van-divider style="padding: 10% 0 0 0">其他方式登录</van-divider>
        <van-button round plain @click="gitHub"><i class="iconfont icon-GitHub"/></van-button>
        <p style="color: #3C4A55;font-size: 12px; padding: 1%">GitHub</p>
    </div>
</template>
<script>
    import {setToken, getToken} from '@/utils/auth'
    import request from '@/utils/request'

    export default {
        name: 'Login',
        data() {
            return {
                loginMsg: "登录",
                form: {
                    mobile: '',
                    password: ''
                }
            }

        },
        created() {

        },
        methods: {
            rePasswordPattern(val) {
                return /^\w{6,14}$/.test(val)
            },
            phonePattern(val) {
                return /^1(3|4|5|7|8)\d{9}$/.test(val);
            },
            onSubmitLogin() {
                this.loginMsg = "正在登录中..."
                request.post('http://localhost:8000/v1/login', this.form).then(res => {
                    setToken(res.data.token)
                    this.$router.push("/")
                })
                this.loginMsg = "登录"

            },
            gitHub() {
                location.href = "https://github.com/login/oauth/authorize?client_id=e5885dde9fe33c6e3488";
            }, toRegister() {
                this.$router.push("/register")
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
