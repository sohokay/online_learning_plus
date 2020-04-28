import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

/* Layout */
import Layout from '@/layout'


/**
 *注意：子菜单仅在路由children.length> = 1时出现
 *详细信息请参见：https://panjiachen.github.io/vue-element-admin-site/guide/essentials/router-and-nav.html
 *
 *隐藏：如果设置为true，则为true，则项目不会显示在边栏中（默认为false）
 * alwaysShow：如果设置为true，则将始终显示根菜单
 *如果未设置alwaysShow，则当项具有多个子路线时，
 *它将变为嵌套模式，否则不显示根菜单
 * redirect：noRedirect如果设置了noRedirect将不会在面包屑中重定向
 * name：“路由器名称”，该名称由<keep-alive>使用（必须设置！！！）
 * meta：{
    角色：['admin'，'editor']控制页面角色（您可以设置多个角色）
    title：标题显示在侧边栏和面包屑中的名称（推荐集）
    图标：'svg-name'图标显示在侧边栏中
    面包屑：如果设置为false，则该项将隐藏在面包屑中（默认为true）
    activeMenu：如果设置了路径，则为“ / example / list”，边栏将突出显示您设置的路径
  }
 * /

 / **
 * constantRoutes
 *没有权限要求的基本页面
 *可以访问所有角色
 */
export const constantRoutes = [
  {
    path: '/login',
    component: () => import('@/views/login_register/login'),
    hidden: true
  }, {
    path: '/register',
    component: () => import('@/views/login_register/register'),
    hidden: true
  }, {
    path: '/phone_login',
    component: () => import('@/views/login_register/phone_login'),
    hidden: true
  },
  {
    path: '/user',
    component: Layout,
    redirect: '/user/info',
    hidden: true,
    children: [
      {
        path: 'info',
        name: 'User',
        component: () => import('@/views/user/info'),
        meta: {title: '个人信息', icon: 'form'}
      }
    ]
  },
  {
    path: '/404',
    component: () => import('@/views/404'),
    hidden: true
  },

  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    children: [{
      path: 'dashboard',
      name: 'Dashboard',
      component: () => import('@/views/dashboard/index'),
      meta: {title: '主页', icon: 'dashboard'}
    }]
  },

  {
    path: '/student',
    component: Layout,
    redirect: '/table/city',
    name: 'table',
    meta: {title: '学生管理', icon: '学生'},
    children: [
      {
        path: 'rotation',
        name: 'Rotation',
        component: () => import('@/views/student/rotation/rotation'),
        meta: {title: '首页轮播设置', icon: '轮播图'}
      },
      {
        path: 'city',
        name: 'City',
        component: () => import('@/views/table/city'),
        meta: {title: '城市', icon: 'table'}
      },
      {
        path: 'tree',
        name: 'Tree',
        component: () => import('@/views/tree/index'),
        meta: {title: 'Tree', icon: 'tree'}
      }
    ]
  },

  {
    path: '/teacher',
    component: Layout,
    meta: {title: '教师管理', icon: '教师'},
    children: [
      {
        path: 'index',
        name: 'Form',
        component: () => import('@/views/form/index'),
        meta: {title: 'Form', icon: 'form'}
      }, {
        path: 'index',
        name: 'Form',
        component: () => import('@/views/form/index'),
        meta: {title: 'Form', icon: 'form'}
      }
    ]
  },

  {
    path: '/admin',
    component: Layout,
    redirect: '/nested/menu1',
    name: '系统管理',
    meta: {
      title: '系统管理',
      icon: '管理员'
    },
    children: [
      {
        path: 'menu1',
        name: 'Menu1',
        meta: {title: '云服务', icon: '服务器'},
        children: [
          {
            path: 'https://pts.aliyun.com/platinum/index.htm?spm=5176.12818093.0.dpts.488716d0h78fnW#/dashboard#/?tab=2.0',
            name: 'Menu1-1',
            meta: {title: '性能测试', icon: 'pts'}
          },
          {
            path: 'https://cloudmonitor.console.aliyun.com/?spm=5176.13329450.203.dcloudmonitor.16704df5y3k5l9#/home/ecs',
            name: 'Menu1-2',
            meta: {title: '云监控', icon: 'KHCFDC_监控'},
          },
          {
            path: 'https://dc.console.aliyun.com/next/index?spm=5176.12818093.0.ddomain.488716d0h78fnW#/domain/list/all-domain',
            name: 'Menu1-3',
            meta: {title: '域名', icon: '域名与网站'}
          },
          {
            path: 'https://console.cloud.tencent.com/smsv2',
            name: 'Menu1-4',
            meta: {title: '云短信', icon: '短信'}
          }, {
            path: 'https://portal.qiniu.com/kodo/bucket/overview?bucketName=musuixin-online-learning',
            name: 'Menu1-4',
            meta: {title: '云存储', icon: '存储'}
          }
        ]
      },
      {
        path: 'menu2',
        meta: {title: '微服务', icon: 'cloud'},
        children: [
          {
            path: 'http://47.104.253.73:8848/nacos/#/serviceManagement?dataId=&group=&appName=&namespace=',
            name: 'Menu2-1',
            meta: {title: 'Nacos', icon: 'nacos'}
          }, {
            path: 'http://47.104.253.73:8888/#/dashboard',
            name: 'Menu2-2',
            meta: {title: 'Sentinel', icon: 'sentinel'}
          }
        ]
      },
      {
        path: 'https://dms.console.aliyun.com/#/dms/login',
        meta: {title: '数据库', icon: 'database'}
      }
    ]
  },

  {
    path: 'external-link',
    component: Layout,
    children: [
      {
        path: 'https://github.com/musuixin',
        meta: {title: '开发者GitHub', icon: 'link'}
      }
    ]
  },

  // 404 page must be placed at the end !!!
  {path: '*', redirect: '/404', hidden: true}
]

const createRouter = () => new Router({
  // mode: 'history', // require service support
  scrollBehavior: () => ({y: 0}),
  routes: constantRoutes
})

const router = createRouter()

// Detail see: https://github.com/vuejs/vue-router/issues/1234#issuecomment-357941465
export function resetRouter() {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher // reset router
}

export default router
