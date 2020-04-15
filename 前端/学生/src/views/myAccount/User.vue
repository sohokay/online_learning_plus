<template>
    <div class="box">
        <header>
            <span class="iconfont icon-icon-" style="position: fixed;left: 6px;font-size: 20px;top: 8px"
                  @click="back"/>
            <div class="title">
                我的资料
            </div>
            <span class="iconfont icon-gengxin" style="position: fixed;right: 6px;font-size: 20px;top: 8px"
                  @click="Submission"/>
        </header>
        <section class="item-box" v-show="isShowInfo">
            <div class="head">
                <div>
                    头像
                </div>
                <div class="value">
                    <input type="file" accept="image/*" id="file_head" class="file-head" @change="tirggerFile($event)">
                    <img :src="this.form.portrait" width="50px" height="50px"
                         class="head-icon">
                </div>
                <span class="iconfont icon-qianjin"/>
            </div>
            <van-divider/>
            <div class="common">
                <div style="width: 80px">
                    用户名
                </div>
                <div class="value">
                    <van-field v-model="form.userName" placeholder="请输入昵称"/>
                </div>
                <span class="iconfont icon-qianjin"/>
            </div>
            <van-divider/>
            <div class="sex-common">
                <div style="width: 100px">
                    性别
                </div>
                <div class="sex-value">
                    <van-field name="form.sex" style="width: 200px">
                        <template #input>
                            <van-radio-group v-model="form.sex" direction="horizontal">
                                <van-radio name="男">男</van-radio>
                                <van-radio name="女">女</van-radio>
                                <van-radio name="保密">保密</van-radio>
                            </van-radio-group>
                        </template>
                    </van-field>
                </div>
                <span class="iconfont icon-qianjin"/>
            </div>
            <van-divider/>
            <div class="common" @click="showCalendar=true">
                <div>
                    出生日期
                </div>
                <div class="value">
                    {{form.birthday}}
                </div>
                <span class="iconfont icon-qianjin"/>
            </div>
            <van-divider/>
            <div class="common">
                <div style="width: 60px">
                    手机号
                </div>
                <div class="value">
                    <van-field @click="showMobile=true" v-model="form.mobile" disabled/>
                </div>
                <span class="iconfont icon-qianjin"/>
            </div>
            <van-divider/>
            <div class="common">
                <div style="width: 60px">
                    密码
                </div>
                <div class="value">
                    <van-field @click="showPassword=true" v-model="form.password" disabled/>
                </div>
                <span class="iconfont icon-qianjin"/>
            </div>
        </section>
        <!--        日期显示-->
        <van-popup v-model="showCalendar" position="bottom" :style="{ height: '50%' }">
            <van-datetime-picker
                    type="date"
                    style="height: 100px"
                    :min-date="minDate"
                    :max-date="maxDate"
                    @confirm="onConfirm" v-model="currentDate" :formatter="formatter"/>
        </van-popup>
        <!--        修改注册手机号-->
        <van-popup v-model="showMobile" position="bottom"
                   :style="{ height: '50%' }">
            <van-row style="margin-top:10px   ; color: #1989fa;
    font-size: 14px;
    background-color: transparent;
    border: none;
    cursor: pointer;">
                <van-col span="2" offset="1" @click="cancel()">取消</van-col>
                <van-col span="2" offset="18" @click="phoneSubmission()">确定</van-col>
            </van-row>
            <div style="padding: 10px;margin-top: 20px;">
                <van-form ref="phoneForm" @submit="changePhone()">
                    <van-field
                            v-model=" mobileForm.mobile"
                            center
                            clearable
                            label="新手机号"
                            placeholder="请输入新手机号"
                            :rules="[{validator:phonePattern, message: '请输入正确手机号 '}]"

                    />
                    <van-divider/>
                    <van-field
                            v-model=" mobileForm.code"
                            center
                            clearable
                            label="验证码"
                            placeholder="请输入短信验证码"
                            :rules="[{ required:true, message: '请输入验证码' }]"

                    >
                        <div slot="button">
                            <van-button native-type="button" size="small" type="primary" @click=" sendCodePhone()"
                                        :disabled="isAblePhone">
                                发送验证码{{time}}
                            </van-button>
                        </div>
                    </van-field>
                </van-form>
            </div>
        </van-popup>
        <!--        修改密码-->

        <van-popup v-model="showPassword" position="bottom"
                   :style="{ height: '50%' }">
            <van-row style="margin-top:10px   ; color: #1989fa;
    font-size: 14px;
    background-color: transparent;
    border: none;
    cursor: pointer;">
                <van-col span="2" offset="1" @click="cancel()">取消</van-col>
                <van-col span="2" offset="18" @click="passwordSubmission()">确定</van-col>
            </van-row>
            <div style="padding: 10px;margin-top: 20px;">
                <van-form ref="passwordForm" @submit="changePassword()">
                    <van-field
                            v-model=" passwordForm.code"
                            center
                            clearable
                            label="验证码"
                            placeholder="请输入短信验证码"
                            :rules="[{ required:true, message: '请输入验证码' }]"

                    >
                        <div slot="button">
                            <van-button native-type="button" size="small" type="primary" @click="sendCodePassword()"
                                        :disabled=" isAblePassword">
                                发送验证码{{time}}
                            </van-button>
                        </div>
                    </van-field>
                    <van-divider/>
                    <van-field
                            v-model="passwordForm.password"
                            type="password"
                            name="密码"
                            label="密码"
                            :rules="[{ validator:passwordPattern, message: '密码为英文，数字下划线组合，6-14位' }]"
                    />
                    <van-divider/>
                    <van-field
                            v-model="passwordForm.rePassword"
                            type="password"
                            name="重复密码"
                            label="重复密码"
                            :rules="[{ validator:rePasswordPattern, message: '两次输入的密码不一致' }]"
                    />
                </van-form>
            </div>
        </van-popup>
    </div>
</template>

<script>
    import Index from "@/views/study/Index";
    import request from '@/utils/request.js'
    import {Notify} from "vant";

    export default {
        name: "user",
        components: {Index},
        created() {
            request.get("http://localhost:8000/v1/user-info").then(res => {
                this.form = res.data
                this.form.password = "*************"
                this.currentDate = new Date(res.data.birthday)
                this.isShowInfo = true
            })
        },
        data() {
            return {
                isFirs:true,
                isShowInfo: false,
                showMobile: false,
                showPassword: false,
                minDate: new Date(1950, 0, 1),
                maxDate: new Date(2030, 0, 1),
                currentDate: new Date(1970, 0, 1),
                originalForm: null,
                form: {
                    userName: null,
                    portrait: 'http://img.musuixin.top/%E9%BB%98%E8%AE%A4%E5%A4%B4%E5%83%8F.jpg',
                    phone: null,
                    sex: null,
                    birthday: null,
                    mobile: null,
                    password: "************"
                },
                mobileForm: {
                    mobile: null,
                    code: null
                },
                passwordForm: {
                    code: null,
                    password: null,
                    rePassword: null
                },
                isAblePhone: false,
                isAblePassword: false,
                time: null,
                showCalendar: false,
                uploader: [{url: 'https://img.yzcdn.cn/vant/leaf.jpg'}]
            }
        },
        methods: {
            passwordPattern(val) {
                return /^\w{6,14}$/.test(val)
            },
            /**
             * 重复密码是否一致验证
             * @param val
             * @returns {boolean}
             */
            rePasswordPattern(val) {
                return val === this.passwordForm.password
            },
            formatter(type, val) {
                // console.log(val)
                if (type === 'year') {
                    return `${val}年`;
                } else if (type === 'month') {
                    return `${val}月`
                } else if (type === 'day') {
                    return `${val}日`
                }
                return val;
            },
            onConfirm(date) {
                this.form.birthday = `${date.getFullYear()}-${date.getMonth() + 1}-${date.getDate()}`;
                this.showCalendar = false;
            },
            tirggerFile(event) {
                //上传文件对象 上传头像
                var form = new FormData();
                if (undefined !== event.target.files[0]) {
                    form.append("portrait", event.target.files[0])
                    request.put("http://localhost:8000/v1/portrait", form).then(res => {
                        this.form.portrait = res.data
                    })
                }
            },
            /**
             * 手机号验证
             * @param val
             * @returns {boolean}
             */
            phonePattern(val) {
                return /^1(3|4|5|7|8)\d{9}$/.test(val);
            },
            Submission() {
                request.put("http://localhost:8000/v1/user-info", this.form).then(res => {
                    Notify({type: 'success', message: res.data});
                })
            },
            back(){
                this.$router.go(-1)

            },
            /*
            * 以下为修改手机号的操作
            * */
            sendCodePhone() {
                if (this.phonePattern(this.mobileForm.mobile)) {
                    request.get("http://localhost:8000/v1/phone-verification", {
                        params: {
                            mobile: this.mobileForm.mobile
                        }
                    }).then(res => {
                        this.time = 60
                        this.isAblePhone = true
                        localStorage.setItem("codeTime", new Date().valueOf())
                        let interval = setInterval(() => {
                            this.time--
                            if (this.time === 0) {
                                clearInterval(interval)
                                this.isAblePhone = false
                                this.time = ''
                            }
                        }, 1000);
                    });
                } else {
                    Notify({type: 'warning', message: '请输入正确的手机号'});
                }
            },
            phoneSubmission() {
                this.$refs.phoneForm.submit()
            },
            changePhone() {
                request.put("http://localhost:8000/v1/mobile", this.mobileForm).then(res => {
                    Notify({type: 'success', message: res.data});
                    this.showMobile = false;
                    this.form.mobile = this.mobileForm.mobile
                })
            },
            /**
             * 修改密码与后台交互
             */
            changePassword() {
                request.put("http://localhost:8000/v1/password", this.passwordForm).then(res => {
                    Notify({type: 'success', message: res.data});
                    this.passwordForm.code = null
                    this.passwordForm.password = null
                    this.passwordForm.rePassword = null
                    this.showPassword = false;

                });
            },
            /*
            *
            * 以下为修改密码操作
            *
            * */
            sendCodePassword() {
                request.get("http://localhost:8000/v1/password-verification").then(res => {
                    this.time = 60
                    this.isAblePassword = true
                    localStorage.setItem("codeTime", new Date().valueOf())
                    let interval = setInterval(() => {
                        this.time--
                        if (this.time === 0) {
                            clearInterval(interval)
                            this.isAblePassword = false
                            this.time = ''
                        }
                    }, 1000);
                });
            },
            /**
             * 调用表单事件
             */
            passwordSubmission() {
                this.$refs.passwordForm.submit()
            },
            /**
             * 修改密码与后台交互
             */
            changePassword() {
                request.put("http://localhost:8000/v1/password", this.passwordForm).then(res => {
                    Notify({type: 'success', message: res.data});

                    this.passwordForm.code = null
                    this.passwordForm.password = null
                    this.passwordForm.rePassword = null
                    this.showPassword = false;
                });
            },
            cancel() {
                this.showMobile = false;
                this.showPassword = false;
            }
        }
    }
</script>

<style scoped lang="scss">
    body {
        .box {
            /*height: 100%;*/
            background-color: #f4f4f4;
        }
    }

    header {
        height: 40px;
        background-color: white;
        text-align: center;
        font-size: 24px;

        .title {
            line-height: 40px;
            text-align: center;
            font-size: 17px;
            color: #191a1b;
            flex: 1;
            overflow: hidden;
            text-overflow: ellipsis;
            white-space: nowrap;
            height: 40px;
            background-color: white;
        }
    }

    .item-box {
        margin-top: 10px;
        height: 200px;
        background: #fff;

        .head {
            padding-right: 15px;
            margin-left: 15px;
            height: 70px;
            line-height: 70px;
            display: flex;

            .input {
                width: 10px;
                opacity: 0;
                height: 70px;
                position: absolute;
                right: 0;
                left: 0;
            }

            .file-head {
                opacity: 0;
                height: 70px;
                width: 100%;
                position: absolute;
                right: 0;
                left: 0;
            }

            img {
                vertical-align: middle;
            }
        }

        .common {
            height: 40px;
            line-height: 40px;
            padding-right: 14px;
            margin-left: 15px;
            display: flex;
            align-items: center;
        }

        .sex-common {
            height: 40px;
            line-height: 40px;
            padding-right: 14px;
            margin-left: 15px;
            display: flex;
            align-items: center;
        }

        .value {
            font-size: 13px;
            color: #797d82;
            flex: 1;
            text-align: right;
            margin-right: 10px;
            margin-left: 210px;
        }

        .sex-value {
            font-size: 13px;
            color: #797d82;
            flex: 1;
            text-align: right;
            margin-left: 130px;
        }
    }

    .van-cell {
        position: relative;
        display: -webkit-box;
        display: -webkit-flex;
        display: flex;
        box-sizing: border-box;
        width: 100%;
        padding: 0px;
        overflow: hidden;
        color: #323233;
        font-size: 14px;
        line-height: 24px;
        background-color: #fff;
    }
</style>
