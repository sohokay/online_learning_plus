import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({

    state: {
        //定义全局变量
        token: '',
        userName: ''
    },
    mutations: {
        //在此定义对全局变量的操作方法
        setToken(state, token) {
            state.token=token
        },
        setUserName(state, userName) {
            state.userName = userName
        }
    },


    getters: {
        token: state => {
            return state.token
        },
        userName: state => {
            return state.userName
        }
    }
})
