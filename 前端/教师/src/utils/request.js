import axios from 'axios'
import {MessageBox, Message, Notification} from 'element-ui'
import store from '@/store'
import {getToken} from '@/utils/auth'

// 创建axios实例
const service = axios.create({
  baseURL: 'http://localhost:8020/v1', // url = 基本网址+请求网址
  timeout: 5000 // 设置超时时间
})
// request 拦截器
service.interceptors.request.use(
  config => {
    config.headers['identity'] = 'teacher'
    if (store.getters.token || getToken) {
      config.headers['token'] = getToken()
    }
    return config
  },
  error => {
    console.log(error) // for debug
    return Promise.reject(error)
  }
)

// response 拦截器
service.interceptors.response.use(
  /**
   *如果想获取http信息（例如标题或状态）可以返回response => response
   */
  response => {
    const res = response.data
    if (res.code !== 200 && res.code !== 201) {
      Notification.error({
        title: '错误',
        message: res.data || 'Error'
      });
      return Promise.reject(new Error(res.message || 'Error'))
    } else {
      return res
    }
  },
  error => {
    if (error.response === undefined) {
      Message({
        message: error.message || "系统错误",
        type: 'error',
        duration: 2 * 1000
      })
    }
    let code = error.response.status
    if (code === 403) {
      // 若是403 证明系统验证错误
      MessageBox.confirm('您已注销，可以取消停留在此页面上，或者再次登录', '确认退出', {
        confirmButtonText: '重新登录',
        cancelButtonText: '离开',
        type: 'warning'
      }).then(() => {
        store.dispatch('user/resetToken').then(() => {
          location.href = '/login'
        })
      })
    } else if (code === 400) {
      Message({
        message: error.message || "验证码错误",
        type: 'error',
        duration: 2 * 1000
      })
    } else {
      Message({
        message: error.message || "系统内部错误",
        type: 'error',
        duration: 2 * 1000
      })
    }

    return Promise.reject(error)
  }
)

export default service
