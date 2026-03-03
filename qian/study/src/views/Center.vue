<template>
   <div class="box">
      <el-header>
         <common-header />
      </el-header>
      <div class="part3">
         <div class="source-row" v-for="(row, rowIndex) in groupedResources" :key="rowIndex">
            <div class="item" v-for="(source, index) in row" :key="index">
               <el-card class="source-card" style="height: 77%;background-clip: text;border: 0;"
                  :body-style="{ padding: '0' }">
                  <img style="cursor: pointer;" @click="goToVideo(source.videoPath)" :src="source.imagePath" alt=""
                     class="source-image" />
               </el-card>
               <el-card
                  style="height: 30%;background: #bde6b8; color: rgb(255, 255, 255);font-size: 13px;background-clip: text;-webkit-background-clip: text;-webkit-text-fill-color: transparent;border: 0;"
                  :body-style="{ height: '100%', padding: '0' }">
                  <p style="margin: 0;">{{ source.name }}</p>
               </el-card>
            </div>
         </div>
      </div>
      <el-footer>
         <common-footer />
      </el-footer>
   </div>
</template>

<script>
import { TimeSelect } from 'element-ui';
import CommonFooter from '../components/CommonFooter.vue';
import CommonHeader from '../components/CommonHeader.vue';
import axios from 'axios';
export default {
   name: 'CenterSources',
   data () {

      return {
         paginatedSources: [],
         groupedResources: [],
         currentPage: 1
      }
   },
   computed: {
      user () {
         return this.$store.state.user;
      },
      belong: {
         get () {
            return this.user ? this.user.studyshort : '学习起步';
         }
      }
   },

   components: {
      CommonFooter,
      CommonHeader
   },

   mounted () {
      this.SourcesByBelong(this.currentPage); // 假设这是你的图片分类
   },
   methods: {
      SourcesByBelong (page) {
         axios.post('http://localhost:9090/sources/getsource', {
            withCredentials: true,
            belong: this.belong,
            page: page
         })
            .then(response => {
               this.paginatedSources = response.data.sources;
               this.groupResources();
            })
            .catch(error => {
               console.error('Error posting sources:', error);
            });
      },
      groupResources () {
         this.groupedResources = [];
         for (let i = 0; i < this.paginatedSources.length; i += 4) {
            this.groupedResources.push(this.paginatedSources.slice(i, i + 4));
         }
      },
      goToVideo (videoPath) {
         this.$router.push({ path: '/videoplay', query: { videoPath: videoPath } });
      },
   },

}
</script>


<style>
.box {
   padding: 0;
   margin: 0;
   background-position: left top;
   background: url('../assets/bei.png') no-repeat center center;
   background-size: cover;
   position: relative;

   .part3 {
      height: 67%;
      padding-left: 60px;
      padding-right: 60px;

      .row {
         height: 32%;
         display: flex;
         flex-direction: row;
         padding-top: 10px;

         .each {
            width: 20%;
            padding-left: 50px;

            .el-card {
               border: 1px solid transparent;
               height: 100%;

               .el-card__body {
                  padding: 1px;
                  display: flex;
                  justify-content: center;
                  align-items: center;

               }

               .p1 {
                  text-align: center;
                  font-size: 16px;
               }

            }
         }
      }
   }

   .el-card.is-always-shadow,
   .el-card.is-hover-shadow:focus,
   .el-card.is-hover-shadow:hover {
      box-shadow: none;
   }
}
</style>