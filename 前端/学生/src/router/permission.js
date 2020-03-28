import router from './index'
import store from '../store'
// import {getToken} from '@/utils/auth' // get token from cookie
// import getPageTitle from '@/utils/get-page-title'

const whiteList = ['/login','/register',"hwllo"] // no redirect whitelist

router.beforeEach(async (to, from, next) => {
    // set page title
    // document.title = getPageTitle(to.meta.title)
    // determine whether the user has logged in
    // const hasToken = getToken()
    const hasToken = false;
    if (hasToken) {
        if (to.path === '/login') {
            // if is logged in, redirect to the home page
            next({path: '/'})
        } else {
            const hasGetUserInfo = store.getters.name
            if (hasGetUserInfo) {
                next()
            } else {
                try {
                    // get user info
                    // await store.dispatch('user/getInfo')
                    next()
                } catch (error) {
                    // remove token and go to login page to re-login
                    await store.dispatch('user/resetToken')
                    next(`/login`)
                }
            }
        }
    } else {
        /* has no token*/
        if (whiteList.indexOf(to.path) !== -1) {
            // in the free login whitelist, go directly
            next()
        } else {
            // other pages that do not have permission to access are redirected to the login page.
            next(`/login`)
        }
    }
})
//
// router.afterEach(() => {
//     // finish progress bar
//     NProgress.done()
// })
