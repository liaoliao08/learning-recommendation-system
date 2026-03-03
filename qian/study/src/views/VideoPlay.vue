<template>
   <div class="box">
      <el-header>
         <common-header />
      </el-header>
      <div class="part3">
         <video :src="videoSrc" controls style="height: 100%;margin-left: 180px;"></video>
         <button style="margin-left:10px; cursor: pointer;position: absolute;border: 3px;" @click="exit">返回</button>
      </div>
      <el-footer>
         <common-footer />
      </el-footer>
   </div>
</template>
<script>
import CommonFooter from '@/components/CommonFooter.vue';
import CommonHeader from '@/components/CommonHeader.vue';

export default {
   data () {
      return {
         videoSrc: ''
      }
   },

   mounted () {
      this.videoSrc = this.$route.query.videoPath;
   },
   components: {
      CommonFooter,
      CommonHeader
   },
   methods: {
      exit () {
         this.$router.go(-1);
      }
   }
}
</script>