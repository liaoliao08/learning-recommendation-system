<template>
   <div class="box">
      <el-header>
         <common-header />
      </el-header>
      <div class="part3">
         <div class="inner">
            <img :src="user.imagepath" alt="" style="height: 100px;width: 100px; border-radius: 50%;"><br>
            <span style="color: aliceblue;"> 用户名称：{{ user.name }}</span>
            <span style="color: aliceblue;padding-left: 30px;"> 手机号：{{ user.phone }}</span><br><br>
            <span style="color: aliceblue;">目前较不熟练的知识方向：{{
               user.studyshort }}</span>
         </div>
      </div>
      <el-footer>
         <common-footer />
      </el-footer>
   </div>
</template>  
  
<script>
import CommonFooter from '../components/CommonFooter.vue';
import CommonHeader from '../components/CommonHeader.vue';
import axios from 'axios';

export default {
   name: 'UserCenter',
   data () {
      return {

      };
   },
   computed: {
      user () {
         return this.$store.state.user;
      }
   },
   components: {
      CommonFooter,
      CommonHeader
   },

   methods: {

   }

}
</script>

<style>
.box {
   height: 100vh;


   .part3 {
      margin-top: 80px;
      height: 500px;
      margin-bottom: 30px;

      .inner {
         padding-left: 80px;
         height: 100%;
      }
   }

}
</style>