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
   name: 'BusinesscommutingSources',
   data () {
      return {
         paginatedSources: [],
         groupedResources: [],
         currentPage: 1, // 当前页码  
         totalPages: 0, // 总页数（由后端返回或前端计算）  
         totalCount: 0,
         belong: '商务通勤'
      };
   },
   components: {
      CommonFooter,
      CommonHeader
   },
   mounted () {
      this.SourcesByBelong(this.belong, this.currentPage); // 假设这是你的图片分类
   },
   methods: {
      SourcesByBelong (belong, page) {
         axios.post('http://localhost:9090/sources/getsource', {
            withCredentials: true,
            belong: belong,
            page: page
         })
            .then(response => {
               this.paginatedSources = response.data.sources;
               this.currentPage = response.data.currentPage;
               this.pageSize = response.data.pageSize;
               this.totalCount = response.data.totalCount;
               this.totalPages = response.data.totalPages; // 计算总页数 
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
      prevPage () {
         if (this.currentPage > 1) {
            this.currentPage--;
            this.SourcesByBelong(this.belong, this.currentPage);
         }
      },
      nextPage () {
         if (this.currentPage < this.totalPages) {
            this.currentPage++;
            this.SourcesByBelong(this.belong, this.currentPage);
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