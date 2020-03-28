<template>
    <div>
        <van-swipe :autoplay="3000">
            <van-swipe-item v-for="(image, index) in images" :key="index">
                <van-image width="100%" height="180" :src="image"/>
            </van-swipe-item>
        </van-swipe>
        <van-divider>
            <svg class="icon" aria-hidden="true">
                <use xlink:href="#icon-jisuanji"/>
            </svg>
            计算机
        </van-divider>
        <van-card num="999" price="慕随心" title="计算机基础" thumb="https://img.yzcdn.cn/vant/ipad.jpeg" currency="教师：">
            <div slot="footer">
                <van-button size="mini" plain type="primary" @click="to_course_detail">详情</van-button>
            </div>
        </van-card>
        <van-divider>
            <svg class="icon" aria-hidden="true">
                <use xlink:href="#icon-yingyu"/>
            </svg>
            英语
        </van-divider>
        <van-card num="999" price="慕随心" title="大学英语" thumb="https://img.yzcdn.cn/vant/ipad.jpeg" currency="教师：">
            <div slot="footer">
                <van-button size="mini" plain type="primary">详情</van-button>
            </div>
        </van-card>
        <van-divider>
            <svg class="icon" aria-hidden="true">
                <use xlink:href="#icon-shuxue-"/>
            </svg>
            数学
        </van-divider>
        <van-card num="999" price="慕随心" title="高等数学" thumb="https://img.yzcdn.cn/vant/ipad.jpeg" currency="教师：">
            <div slot="footer">
                <van-button size="mini" plain type="primary">详情</van-button>
            </div>
        </van-card>
    </div>
</template>

<script>
    export default {
        name: "Index",
        data() {
            return {
                images: [
                    'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1582865552380&di=60f0044e1a068554bc525463394072fd&imgtype=0&src=http%3A%2F%2Ftu.ossfiles.cn%3A9186%2Fgroup2%2FM00%2F54%2FE7%2FrBgICV2uccaAMhNhAAB-0ZY0F8w923.jpg',
                    'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1582865578333&di=4a92df551df1f2f0573a780496d2e4dc&imgtype=0&src=http%3A%2F%2Fimg3.qianzhan.com%2Fnews%2F201511%2F04%2F20151104-aaae0871a879feb2_600x5000.jpg',
                    'https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3126090086,86324601&fm=26&gp=0.jpg'
                ]
            }
        },
		methods:{
			to_course_detail(){
				this.$router.push("/courseDetail/44")
			}
		}
    }
</script>

<style scoped>
    .my-swipe .van-swipe-item {
        color: #fff;
        font-size: 20px;
        line-height: 150px;
        text-align: center;
        background-color: #39a9ed;
    }

    .card {
        margin: 0;
    }
</style>
