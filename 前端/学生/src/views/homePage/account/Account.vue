<template>
    <div class="center-view">
        <div class="avatar">
            <img src="https://gss0.bdstatic.com/-4o3dSag_xI4khGkpoWK1HF6hhy/baike/w%3D268%3Bg%3D0/sign=49ed3e6c3b01213fcf3349da6cdc51ec/8b82b9014a90f603b77096f23b12b31bb151ed47.jpg"
                 class="avator-icon">
            <div class="nick-name">慕随心</div>
        </div>
        <ul class="my-learn-tab">
            <li>
                <p>0</p>
                我的评论
            </li>
            <li>
                <p>0</p>
                总学时长
            </li>
        </ul>
        <div class="margin-set " @click="toLocation('/user')">
            <img src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACgAAAAoBAMAAAB+0KVeAAAAIVBMVEVHcEz/iGT/hWL/hGL/hWL/hGL/hGL/hGP/hWP/jGn/hGISk7EWAAAACnRSTlMAHOamtu21p7QdUXNikQAAAFtJREFUKM9jYKAFENdahQIWGTIwMEatQgNLBRiYlwig6mT0MmBgWohu3iwFBq4F6IJAEZoIrsIAuATp5qTBI8i2atUybIIraGU7ZmKQUsCebLAmMMykWEiTFA8AIQBrdTyC6ekAAAAASUVORK5CYII="
                 width="20px" height="20px" alt=""> <span class="label">个人信息</span> <img
                src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABUAAAAkCAMAAABR74GsAAAAhFBMVEVHcEy+wMa+wcW9wMXQ0NDFxcW9wMW///+9wMW9wMW+wMW+wse+wMXBwca/wsW9wMW+wMa9wcW9wcW+wMa9wse9wMW9wcbAwMa/xsa9wMXAwMe9wMW9wcW/wca+wca9wMW9wsa+wMa9wMW9wMW9wcW/1NS+wMXHx8e/wcbCws6+wcW9wMXhD+L5AAAAK3RSTlMAgrXLCxblBPz1ZjfcNkzdnraBg2XPyk0k6iXrjXCd5HGLnLeADMwXZBVL0OsnaQAAAJJJREFUKM+t0EcSgzAMQFEDBmx6OiW9J//+98te8iqDlm80428ZM8ccslVATxDgC1AozWNwO8V2C75U/KlgGhX3A7QvxbWH50Px18EtV/wGOp19ByLN1xl+k1aw0doG1Cbga/naAlwjVztgLTEClhIL4CixcRDL65QeEitwnKBKA/VD/3/9PlRvskC9MecsMnPPD3MGEI/gd5ReAAAAAElFTkSuQmCC"
                alt="" class="arrow">
        </div>
        <div class="margin-set my-card">
            <img src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACgAAAAoBAMAAAB+0KVeAAAAIVBMVEVHcEz/iGT/hWL/hGL/hWL/hGL/hGL/hGP/hWP/jGn/hGISk7EWAAAACnRSTlMAHOamtu21p7QdUXNikQAAAFtJREFUKM9jYKAFENdahQIWGTIwMEatQgNLBRiYlwig6mT0MmBgWohu3iwFBq4F6IJAEZoIrsIAuATp5qTBI8i2atUybIIraGU7ZmKQUsCebLAmMMykWEiTFA8AIQBrdTyC6ekAAAAASUVORK5CYII="
                 width="20px" height="20px" alt=""> <span class="label">已获评价</span> <img
                src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABUAAAAkCAMAAABR74GsAAAAhFBMVEVHcEy+wMa+wcW9wMXQ0NDFxcW9wMW///+9wMW9wMW+wMW+wse+wMXBwca/wsW9wMW+wMa9wcW9wcW+wMa9wse9wMW9wcbAwMa/xsa9wMXAwMe9wMW9wcW/wca+wca9wMW9wsa+wMa9wMW9wMW9wcW/1NS+wMXHx8e/wcbCws6+wcW9wMXhD+L5AAAAK3RSTlMAgrXLCxblBPz1ZjfcNkzdnraBg2XPyk0k6iXrjXCd5HGLnLeADMwXZBVL0OsnaQAAAJJJREFUKM+t0EcSgzAMQFEDBmx6OiW9J//+98te8iqDlm80428ZM8ccslVATxDgC1AozWNwO8V2C75U/KlgGhX3A7QvxbWH50Px18EtV/wGOp19ByLN1xl+k1aw0doG1Cbga/naAlwjVztgLTEClhIL4CixcRDL65QeEitwnKBKA/VD/3/9PlRvskC9MecsMnPPD3MGEI/gd5ReAAAAAElFTkSuQmCC"
                alt="" class="arrow">
        </div>
        <div style="background-color: white;text-align: center;margin-top: 50px">
            <van-button type="danger" style="width: 300px" @click="sign_out">退出当前账户</van-button>
        </div>
    </div>
</template>

<script>
    import {toLocation} from "@/utils/page";
    import Vue from 'vue';
    import {Notify} from 'vant';
    import requset from '@/utils/request.js'
    import {removeToken} from '@/utils/auth'

    Vue.use(Notify);
    export default {
        name: "Account",
        methods: {
            sign_out() {
                removeToken();
                this.$router.push('/login')
                // Notify({ type: 'warning', message: '通知内容' });
            },
            toLocation(web) {
                toLocation(web, this)
            }
        }
    }
</script>

<style scoped lang="scss">
    .center-view {
        top: 0;
        right: 0;
        left: 0;
        font-size: 15px;
        background: #f4f4f4;
        margin-bottom: 59px;
    }

    .avatar {
        text-align: center;
        margin-top: -44px;
        height: 150px;
        padding-left: 22px;
        padding-top: 64px;
        background: url(https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1583335988118&di=d45ee8a2974ab2b4e6e1107b359021cf&imgtype=0&src=http%3A%2F%2Fimg1.sycdn.imooc.com%2Fclimg%2F58e726090001658206000338.jpg);
        background-size: cover;
        display: flex;
        align-items: center;
        color: #fff;
    }

    .avator-icon {
        width: 63px;
        height: 63px;
        border-radius: 35px;
        margin-right: 20px;
        border: 2px solid #fff;
    }

    .nick-name {
        font-size: 16px;
    }

    .my-learn-tab {
        width: 100%;
        margin: 0 auto;
        display: flex;
        text-align: center;
        font-size: 13px;
        padding: 0;
        height: 79px;
        background: #fff;
        margin-bottom: 10px;
        align-items: center;

        li {
            position: relative;
            flex: 1;
            list-style: none;
        }
    }

    body {
        height: 100%;
        background-color: #f4f4f4;
    }

    .margin-set {
        background: #fff;
        padding: 0 15px;
        height: 49px;
        display: flex;
        align-items: center;
    }

    .arrow {
        text-align: right;
        width: 6px;
        height: 10px;
    }

    .label {
        margin-left: 15px;
        color: #191a1b;
        flex: 1;
    }
</style>
