import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import Vant from 'vant';
import 'vant/lib/index.css';
import '@vant/touch-emulator';
import VCharts from 'v-charts'
import axios from 'axios'
import '@/router/permission'

Vue.prototype.axios = axios
Vue.use(Vant);//手机端样式组件
Vue.use(VCharts)
Vue.config.productionTip = false


new Vue({
    router,
    store,
    render: h => h(App)
}).$mount('#app')
