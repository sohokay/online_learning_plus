<template>
    <div class="box">
        <header>
            <span class="iconfont icon-icon-" style="position: fixed;left: 6px;font-size: 20px;top: 8px"
                  @click="Submission"/>
            <div class="title">
                我的资料
            </div>
        </header>
        <section class="item-box">
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
                <div>
                    昵称
                </div>
                <div class="value">
                    <van-field v-model="form.phone" placeholder="请输入昵称"/>
                </div>
                <span class="iconfont icon-qianjin"/>
            </div>
            <van-divider/>
            <div class="sex-common">
                <div>
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
            <div class="common">
                <div>
                    出生日期
                </div>
                <div class="value" @click="showCalendar=true">
                    {{form.birthday}}
                </div>
                <span class="iconfont icon-qianjin"/>
            </div>
        </section>
        <van-datetime-picker
                type="date"
                v-show="showCalendar"
                style="height: 100px"
                :min-date="minDate"
                :max-date="maxDate"
                @confirm="onConfirm" v-model="currentDate" :formatter="formatter"/>
    </div>
</template>

<script>
    import Index from "@/views/study/Index";
    import request from '@/utils/request.js'

    export default {
        name: "user",
        components: {Index},
        data() {
            return {
                minDate: new Date(1970, 0, 1),
                maxDate: new Date(2030, 0, 1),
                currentDate: new Date(1970, 0, 1),
                form: {
                    portrait: 'http://img.musuixin.top/%E9%BB%98%E8%AE%A4%E5%A4%B4%E5%83%8F.jpg',
                    phone: 121212121212,
                    sex: '女',
                    birthday: '2000-10-31'
                },
                showCalendar: false,
                uploader: [{url: 'https://img.yzcdn.cn/vant/leaf.jpg'}]
            }
        },
        methods: {
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
                console.log(event.target.files)
            },
            Submission() {
                request.get("/12", {params: this.form})
                this.$router.go(-1)
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
            margin-right: 14px;
            margin-left: 170px;
        }

        .sex-value {
            font-size: 13px;
            color: #797d82;
            flex: 1;
            text-align: right;
            margin-left: 120px;
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
