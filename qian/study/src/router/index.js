import Vue from 'vue'
import VueRouter from 'vue-router'
import Test from '../views/Test.vue'
import Learningstarts from '../views/Learningstarts.vue'
import Dailylife from '../views/Dailylife.vue'
import Ancientliterature from '../views/Ancientliterature.vue'
import Businesscommuting from '../views/Businesscommuting.vue'
import Filmmodule from '../views/Filmmodule.vue'
import Center from '../views/Center.vue'
import Searchshow from '../views/Searchshow.vue'
import VideoPlay from '../views/VideoPlay.vue'
// import { component } from 'vue/types/umd'
Vue.use(VueRouter)

//1.创建路由组件
//2.将路由与组件进行映射
//3.创建router实例

const routes = [
   //登陆页面

   {
      path: '/login',
      component: () => import('../views/Login.vue'),
   },
   {
      path: '/forget',
      component: () => import('../views/Forget.vue')
   },
   {
      path: '/register',
      component: () => import('../views/Register.vue')
   },
   {
      path: '/usercenter',
      component: () => import('../views/Usercenter.vue')
   },
   {
      path: '/testbegin',
      component: () => import('../views/Testbegin.vue')
   },
   { path: '/', component: Center },
   { path: '/test', component: Test },//用户管理
   { path: '/learningstarts', component: Learningstarts },//管理中心
   { path: '/dailylife', component: Dailylife },
   { path: '/ancientliterature', component: Ancientliterature },
   { path: '/businesscommuting', component: Businesscommuting },
   { path: '/filmmodule', component: Filmmodule },
   { path: '/searchshow', component: Searchshow },
   { path: '/videoplay', component: VideoPlay }


]



const router = new VueRouter({
   mode: 'history',
   routes
})

export default router

