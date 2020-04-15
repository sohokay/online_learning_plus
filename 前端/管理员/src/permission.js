import router from './router'
import store from './store'
import {Message} from 'element-ui'
import NProgress from 'nprogress' // progress bar
import 'nprogress/nprogress.css' // progress bar style
import {getToken} from '@/utils/auth' // get token from cookie
import getPageTitle from '@/utils/get-page-title'

NProgress.configure({showSpinner: false}) // NProgress 设置

const whiteList = ['/login', '/register', '/phone_login'] // 白名单
/**
 * 全局路由设置
 */
router.beforeEach(async (to, from, next) => {
  // 进度条
  NProgress.start()
  // 设置页面标题
  document.title = getPageTitle(to.meta.title)

  // 从cookie中获取token
  const hasToken = getToken()

  if (hasToken) {
    if (to.path === '/login') {
      // 如果已登录，请重定向到主页
      next({path: '/'})
      NProgress.done()
    } else {
      const hasGetUserInfo = store.getters.name
      if (hasGetUserInfo) {
        next()
      } else {
        try {
          //获取用户信息
          //等待store.dispatch（'user / getInfo'）
          next()
        } catch (error) {
          // 删除令牌并进入login_register页面重新登录
          await store.dispatch('user/resetToken')
          Message.error(error || 'Has Error')
          next(`/login_register?redirect=${to.path}`)
          NProgress.done()
        }
      }
    }
  } else {
    /*没有令牌*/

    if (whiteList.indexOf(to.path) !== -1) {
      // 在白名单中，直接进入
      next()
    } else {
      // 其他无权访问的页面将重定向到login_register页面。
      next(`/login?redirect=${to.path}`)
      NProgress.done()
    }
  }
})

router.afterEach(() => {
  // 完成进度栏
  NProgress.done()
})
