import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

/* Layout */
import Layout from '@/layout'


/**
 * Note: sub-menu only appear when route children.length >= 1
 * Detail see: https://panjiachen.github.io/vue-element-admin-site/guide/essentials/router-and-nav.html
 *
 * hidden: true                   if set true, item will not show in the sidebar(default is false)
 * alwaysShow: true               if set true, will always show the root menu
 *                                if not set alwaysShow, when item has more than one children route,
 *                                it will becomes nested mode, otherwise not show the root menu
 * redirect: noRedirect           if set noRedirect will no redirect in the breadcrumb
 * name:'router-name'             the name is used by <keep-alive> (must set!!!)
 * meta : {
    roles: ['admin','editor']    control the page roles (you can set multiple roles)
    title: 'title'               the name show in sidebar and breadcrumb (recommend set)
    icon: 'svg-name'             the icon show in the sidebar
    breadcrumb: false            if set false, the item will hidden in breadcrumb(default is true)
    activeMenu: '/example/list'  if set path, the sidebar will highlight the path you set
  }
 */

/**
 * constantRoutes
 * a base page that does not have permission requirements
 * all roles can be accessed
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
    path: '/course',
    component: Layout,
    meta: {title: '课程', icon: '课程'},
    redirect: '/course/now_course',
    children: [
      {
        path: 'now_course',
        name: 'NowCourse',
        component: () => import('@/views/course/now_course'),
        meta: {title: '现有课程', icon: '课程 学业'}
      },
      {
        path: 'add_course',
        name: 'AddCourse',
        component: () => import('@/views/course/add_course'),
        meta: {title: '添加课程', icon: '添加'}
      }
    ]
  },

  {
    path: '/student',
    component: Layout,
    redirect: '/student/table',
    name: 'student',
    meta: {title: '学生', icon: '学生'},
    children: [
      {
        path: 'table',
        name: 'Table',
        component: () => import('@/views/student/Index'),
        meta: {title: '学生列表', icon: 'table'}
      }, {
        path: 'analysis',
        name: 'Analysis',
        component: () => import('@/views/student/StudyAnalysis'),
        hidden: true,
        meta: {title: '学习分析', icon: 'table'}
      },
    ]
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
    path: '/course_info',
    component: Layout,
    redirect: '/course_info/:courseId',
    hidden: true,
    children: [
      {
        path: ':courseId',
        name: 'CourseInfo',
        component: () => import('@/views/course/course_info'),
        meta: {title: '课程信息'},
        props: true
      }
    ]
  },

  /**
   *课程详情页
   */

  {
    path: '/course_details',
    component: Layout,
    redirect: '/course_details/info/:courseId',
    hidden: true,
    children: [
      {
        path: 'info/:courseId',
        name: 'Info',
        component: () => import('@/views/course_details/index'),
        meta: {title: '课程详情'},
        props: true
      }, {
        path: 'test/:testId',
        name: 'Test',
        component: () => import('@/views/course_details/TestDetail'),
        meta: {title: '测验详情'},
        props: true

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
  mode: 'history', // require service support
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
