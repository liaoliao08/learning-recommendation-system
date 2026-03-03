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
         <div class="button">
            <!-- Pagination controls -->
            <button @click="prevPage" :disabled="currentPage === 1">上一页</button>
            <span> {{ currentPage }} of {{ totalPages }}</span>
            <button @click="nextPage" :disabled="currentPage === totalPages">下一页</button>
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
   name: 'SearchshowSources',
   data () {
      return {
         paginatedSources: [],
         groupedResources: [],
         currentPage: 1, // 当前页码  
         totalPages: 0, // 总页数（由后端返回或前端计算）  
         totalCount: 0,
         name: ''
      };
   },
   watch: {
      '$route.query.name': 'fetchData' // 监听查询参数name的变化  
   },
   components: {
      CommonFooter,
      CommonHeader
   },
   methods: {
      fetchData () {
         this.name = this.$route.query.name; // 更新name的值  
         this.SourcesByName(this.name, 1); // 获取第一页的数据  
      },
      SourcesByName (name, currentPage) {
         axios({
            method: 'post',
            url: 'http://localhost:9090/sources/getsourcebyname',
            data: {
               name: name,
               currentPage: currentPage
            },
            withCredentials: true // 如果需要发送跨域请求的凭据  
         })
            .then(response => {
               if (response.data.totalCount == 0) {
                  alert("没有查找到对应的资源");
               }
               else {
                  this.paginatedSources = response.data.sources;
                  this.currentPage = response.data.currentPage;
                  this.totalCount = response.data.totalCount;
                  this.totalPages = response.data.totalPages; // 计算总页数 
                  this.groupResources();
               }

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
      prevPage () {
         if (this.currentPage > 1) {
            this.currentPage--;
            this.SourcesByName(this.name, this.currentPage);
         }
      },
      nextPage () {
         if (this.currentPage < this.totalPages) {
            this.currentPage++;
            this.SourcesByName(this.name, this.currentPage);
         }
      },
      goToVideo (videoPath) {
         if (videoPath) { // 添加对videoPath的检查以避免空值或未定义  
            window.open(videoPath, '_blank');
         } else {
            // 处理videoPath不存在或为空的情况  
            console.error('Video path is not provided or is empty.');
         }
      },
   },
   created () {
      this.fetchData();
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

      .source-row {
         height: 32%;
         display: flex;
         flex-direction: row;
         margin-bottom: 10px;
      }

      .item {
         width: 25%;
         /* 使得每个资源项在行内等宽 */
         display: flex;
         flex-direction: column;
         align-items: center;
         justify-content: space-between;
         text-align: center;
         /* 文字居中 */
      }

      img {
         max-width: 60%;
         /* 图片最大宽度为容器宽度 */
         height: auto;
         /* 保持图片的原始纵横比 */
      }

      .button {
         position: absolute;
         left: 45%;
         top: 87%;
         color: aquamarine;
         display: flex;
         flex-direction: row;
         align-items: center;
         justify-content: space-between;
         text-align: center;
      }


   }



}
</style>