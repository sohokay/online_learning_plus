import {login, logout, getInfo, phoneLogin} from '@/api/user'
import {getToken, setToken, removeToken} from '@/utils/auth'
import {resetRouter} from '@/router'
import {setUserName, setHead} from '@/utils/setCookies'

const getDefaultState = () => {
  return {
    token: getToken(),
    name: '',
    img: ''
  }
}

const state = getDefaultState()

const mutations = {
  RESET_STATE: (state) => {
    Object.assign(state, getDefaultState())
  },
  SET_TOKEN: (state, token) => {
    state.token = token
  },
  SET_IMG: (state, img) => {
    state.img = img
  }
}

const actions = {
  // user login_register
  login({commit}, userInfo) {
    return new Promise((resolve, reject) => {
      login(userInfo).then(response => {
        const {data} = response
        commit('SET_TOKEN', data.token)
        commit('SET_IMG', data.user)
        setToken(data.token)
        setHead(data.user)
        resolve()
      }).catch(error => {
        reject(error)
      })
    })
  },
  phoneLogin({commit}, userInfo) {
    return new Promise((resolve, reject) => {
      phoneLogin(userInfo).then(response => {
        const {data} = response
        commit('SET_TOKEN', data.token)
        commit('SET_IMG', data.user)
        setToken(data.token)
        setHead(data.user)
        resolve()
      }).catch(error => {
        reject(error)
      })
    })
  },
  // user logout
  logout({commit, state}) {
    return new Promise((resolve, reject) => {
      removeToken() // must remove  token  first
      resetRouter()
      commit('RESET_STATE')
      resolve()
    })
  },
  // remove token
  resetToken({commit}) {
    return new Promise(resolve => {
      removeToken() // must remove  token  first
      commit('RESET_STATE')
      resolve()
    })
  }
}
export default {
  namespaced: true,
  state,
  mutations,
  actions
}

