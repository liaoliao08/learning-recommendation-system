<template>
   <div class="box">
      <el-header>
         <common-header />
      </el-header>
      <div class="part3">
         <el-card style="margin-left: 150px;margin-right: 150px;height: 100%;">
            <div class="content">
               <span>接下来将会开始测试您的知识掌握水平，一共有30道题目，每道题目都有四个选项</span>
            </div>
            <button @click="click0" style="position: absolute;top :80%;left: 48%;">开始测试</button>

         </el-card>
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
   data () {
      return {

      }
   },
   components: {
      CommonFooter,
      CommonHeader
   },
   computed: {
      user () {
         return this.$store.state.user;
      }
   },
   methods: {
      click0 () {
         if (!this.user) {
            alert("请先登录")
         }
         else {
            this.$router.push("/testbegin");
         }
      }
   },
};  
</script>


<style>
.box {
   height: 100vh;


   .part3 {
      margin-top: 80px;
      height: 500px;
      margin-bottom: 30px;

      .content {
         display: flex;
         justify-content: center;
         text-align: center;
      }


   }



}
</style>