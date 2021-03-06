import Vue from 'vue'
import VueRouter from 'vue-router'
import Index from "@/views/homePage/course/Course";

Vue.use(VueRouter)

const routes = [
    //三个主页面
    {
        path: '/',
        name: 'Index',
        redirect: '/course',
        component: () => import('../views/homePage/Index.vue'),
        children: [
            {
                path: 'course',
                name: 'Course',
                component: () => import('../views/homePage/course/Course.vue'),
            },
            {
                path: 'myCourse',
                name: 'MyCourse',
                // route level code-splitting
                // this generates a separate chunk (about.[hash].js) for this route
                // which is lazy-loaded when the route is visited.
                component: () => import(/* webpackChunkName: "about" */ '../views/homePage/myLearning/Index.vue')
            },
            {
                path: 'account',
                name: 'Account',
                // route level code-splitting
                // this generates a separate chunk (about.[hash].js) for this route
                // which is lazy-loaded when the route is visited.
                component: () => import(/* webpackChunkName: "about" */ '../views/homePage/account/Account.vue')
            }
        ]
    },
    //登录页面
    {

        path: '/login',
        name: 'Login',
        component: () => import('../views/loginRegistration/Login.vue')
    }, {

        path: '/register',
        name: 'Register',
        component: () => import('../views/loginRegistration/Register.vue')
    }, {

        path: '/github_login',
        name: 'GitHubLogin',
        component: () => import('../views/loginRegistration/GitHubLogin')
    },
    //详情页面
    {
        path: '/course_detail/:courseId',
        name: 'CourseDetail',
        component: () => import('../views/courseDetails/Index'),
        props: true
    },
    //学习页面
    {
        path: '/study/:courseId',
        name: 'Study',
        component: () => import('@/views/study/Index'),
        props: true
    },
    //用户资料
    {
        path: '/user',
        name: 'User',
        component: () => import('@/views/myAccount/User'),

    },
    /**
     *
     * 详细的学习、讨论页面
     *
     */
    //详细的学习公告页面路由
    {
        path: '/notice_details/:noticeId',
        name: 'NoticeDetails',
        component: () => import('@/views/studyDetails/NoticeDetails'),
        props: true

    },
    //详细的讨论页面
    {
        path: '/discuss_details/:discussId',
        name: 'DiscussDetails',
        component: () => import('@/views/studyDetails/DiscussDetails'),
        props: true

    },
    //课件详情页
    {
        path: '/courseware_details/:url',
        name: 'Courseware',
        component: () => import('@/views/studyDetails/pdf'),
        props: true

    },
    /**
     * 更多课程列表
     *
     */
    {
        path: '/more_course/:courseType',
        name: 'MoreCourse',
        component: () => import('@/views/moreCourse/index'),
        props: true
    }, {
        path: '/test_questions/:testId',
        name: 'TestQuestions',
        component: () => import('@/views/studyDetails/TestDetails'),
        props: true
    }, {
        path: '/student_valuate',
        name: 'StudentEvaluate',
        component: () => import('@/views/studyDetails/StudentEvaluate'),
        props: true
    }
]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})
export default router
