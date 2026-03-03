<template>
   <div class="bottom-container">
      <div class="l-content">
         <span class="text">Welcome to use Chinese Study Source Platform</span>
      </div>
      <div class="r-content">&nbsp;&nbsp;&nbsp;&nbsp;
         <i class="el-icon-user"></i>
         <span class="text">微信公众号</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
         <i class="el-icon-user"></i>
         <span class="text">QQ交流</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
         <i class="el-icon-user"></i>
         <span class="text">联系邮箱</span><br>
         <span class="text">搜索关注：xtu1958</span>&nbsp;&nbsp;&nbsp;&nbsp;
         <span class="text">952939865</span>&nbsp;&nbsp;&nbsp;
         <span class="text">952939865@qq.com</span>
      </div>
   </div>
</template>
<script>

export default {
   data () {
      return {}
   },
   methods: {
   }
}
</script>
<style lang="less" scoped>
.bottom-container {
   height: 60px;
   display: flex;
   justify-content: space-between;
   align-items: center;


   .l-content {
      padding-left: 10px;
      display: flex;
      padding-right: 20px;

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


      }
   }

   .r-content {
      background: linear-gradient(to right, #fbfdfc, #79f568);
      background-clip: text;
      -webkit-background-clip: text;
      /*将设置的背景颜色限制在文字中*/
      -webkit-text-fill-color: transparent;

   }
}
</style>