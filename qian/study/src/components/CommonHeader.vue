<template>
   <div class="head">
      <div class="header-container">
         <div class="l-content">
            <img class="logo" src="../assets/logo.png" alt="">
            <div class="text">
               <span class="text1">中&nbsp;文&nbsp;学&nbsp;习&nbsp;平&nbsp;台</span>
               <span class="text2">Chinese Study Source Platform</span>
            </div>
         </div>
         <div class="search1">

            <span class="el-icon-search"
               style="background-color: rgb(255, 255, 255);width: 25px;height: 20px;padding-top: 5px;padding-bottom: 0px; "></span>
            <input type="text" placeholder="搜索" v-model="name"
               style="height: 25px; padding-right: 0px; padding-top: 0px; padding-bottom: 0px; padding-left: 5px; border-top-width: 0px; border-right-width: 0px; border-bottom-width: 0px; border-left-width: 0px; margin-bottom: 0px;">
            <el-button
               style="border-bottom-width: 0px; border-top-width: 0px; border-left-width: 0px; border-right-width: 0px; padding-right: 0px; padding-bottom: 0px; padding-top: 0px; padding-left: 0px; height: 25px; width: 40px; margin-left: 5px;font-size: 13px;"
               type="button" class="handleClick" @click="search">搜索</el-button>
         </div>
         <div class="r-content">
            <div class="avatar-container" @click="handleAvatarClick">
               <img v-if="!user" class="el-icon-edit" :src="require('@/assets/user.png')" />
               <img v-else :src="user.imagepath" alt="User Avatar" class="user-avatar" />
            </div>
            <button style="width: 50px;height: 20px;margin-top: 5px;border: 0;padding: 0;cursor: pointer;"
               @click="Logout">退出</button>

         </div>
      </div>
      <el-menu default-active="1-4-1" class="el-menu-horizontal-demo" @open="handleOpen" @close="handleClose"
         background-color="#545c64" text-color="#fff" active-text-color="#ffd04b">

         <el-menu-item style="background: linear-gradient(to right, #fbfdfc, #79f568);
         background-clip: text;
         -webkit-background-clip: text;
         -webkit-text-fill-color: transparent;margin-left: 30px;width: 160px;" @click="clickMenu(item)"
            v-for="item in noChildren" :key="item.name" :index="item.name">
            <span style="font-size: 16px;" slot="title">{{ item.label }}</span>
         </el-menu-item>
      </el-menu>

   </div>
</template>

<script>
export default {
   data () {
      return {
         name: '',
         menuData: [
            {
               path: '/',
               name: 'center',
               label: '首页'
            },
            {
               path: '/test',
               name: 'test',
               label: '阶段测试',
            },
            {
               path: '/learningstarts',
               name: 'learningstarts',
               label: '学习起步'
            },
            {
               path: '/dailylife',
               name: 'dailylife',
               label: '日常生活'
            },
            {
               path: '/ancientliterature',
               name: 'ancientliterature',
               label: '古代文学'
            },

            {
               path: '/businesscommuting',
               name: 'businesscommuting',
               label: '商务通勤'
            },
            {
               path: '/filmmodule',
               name: 'filmmodule',
               label: '影视模块'
            }
         ],

      }
   },

   methods: {
      search () {
         this.$router.push({ path: '/searchshow', query: { name: this.name } })
      },
      handleAvatarClick () {
         if (!this.user) {
            this.$router.push("/login");
         }
         else {
            this.$router.push("/usercenter");
         }
      },
      Logout () {
         if (this.user) {
            this.$store.commit('setUser', null);
            this.$router.push("/login");
         }
      },
      handleOpen (key, keyPath) {
         console.log(key, keyPath);
      },
      handleClose (key, keyPath) {
         console.log(key, keyPath);
      },
      //点击菜单
      clickMenu (item) {
         //console.log(item);
         //当页面的路由与跳转的路由不一致才允许跳转
         if (this.$route.path !== item.path && !(this.$route === '/center' && (item.path === '/'))) {
            this.$router.push(item.path)
         }
      }
   },
   computed: {
      //没有子菜单
      noChildren () {
         return this.menuData.filter(item => !item.children)
      },
      user () {
         return this.$store.state.user;
      }
   },
}
</script>
<style lang="less" scoped>
.head {


   .header-container {
      padding-top: 10px;
      height: 60px;
      display: flex;
      justify-content: space-between;
      align-items: center;
      position: relative;
      top: 10%;

      .l-content {
         padding-left: 10px;
         display: flex;
         padding-right: 20px;

         .logo {
            width: 60px;
            height: 60px;
         }

         .text {
            background: linear-gradient(to right, #fbfdfc, #79f568);
            background-clip: text;
            -webkit-background-clip: text;
            /*将设置的背景颜色限制在文字中*/
            -webkit-text-fill-color: transparent;
            display: flex;
            justify-content: center;
            height: 60px;
            flex-direction: column;

            .text1 {
               padding-left: 16px;

               font-size: 30px;
            }

            .text2 {
               padding-left: 8px;
               background: linear-gradient(to right, #fbfdfc, #79f568);
               background-clip: text;
               -webkit-background-clip: text;
               /*将设置的背景颜色限制在文字中*/
               -webkit-text-fill-color: transparent;
               /*给文字设成透明*/
               font-size: 18px;
            }
         }
      }


      .search1 {
         font-size: 20px;
         float: right;
         height: 40px;
         padding-left: 750px;
         display: flex;
         text-align: center;
         justify-content: baseline;
      }

      .r-content {
         display: flex;
         flex-direction: column;
         margin-top: 10px;
         // background: linear-gradient(to right, #fbfdfc, #79f568);
         // background-clip: text;
         // -webkit-background-clip: text;
         // /*将设置的背景颜色限制在文字中*/
         // -webkit-text-fill-color: transparent;

         .avatar-container {
            /* 根据需要添加样式 */
            display: inline-block;
            width: 50px;
            /* 举例的头像尺寸 */
            height: 50px;
            cursor: pointer;
            margin-bottom: 10px;
         }

         .el-icon-edit {
            width: 100%;
            height: 100%;
         }

         .user-avatar {
            width: 100%;
            height: 100%;
            object-fit: cover;
            border-radius: 50%;
         }
      }


   }

   .el-menu-horizontal-demo:not(.el-menu--collapse) {
      display: flex;
      flex-direction: row;
      background-clip: text;
      /*将设置的背景颜色限制在文字中*/
      text-align: center;
      font-size: 25px;
      border-right: 0;
      width: 1350px;
   }

   .el-menu {
      display: flex;
      flex-direction: row;
      margin-left: 30px;
      margin-right: 0px;
      height: 42px;

   }
}
</style>