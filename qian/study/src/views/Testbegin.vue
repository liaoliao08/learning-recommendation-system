<template>
   <div class="box">
      <el-header>
         <common-header />
      </el-header>
      <div class="part3">
         <el-card style="margin-left: 150px;margin-right: 150px;height: 100%;"
            :body-style="{ padding: '0', height: '90%' }">
            <div v-for="(question, index) in currentQuestions" :key="index" class="question">
               <h2>{{ currentQuestionIndex + 1 }}.{{ question.name }}</h2>
               <div class="qcontent">
                  <input type="radio" :value="question.contenta" v-model="selectedAnswers[currentQuestionIndex]" />
                  A. {{ question.contenta }}
               </div><br>
               <div class="qcontent">
                  <input type="radio" :value="question.contentb" v-model="selectedAnswers[currentQuestionIndex]" />
                  B. {{ question.contentb }}
               </div><br>
               <div class="qcontent">
                  <input type="radio" :value="question.contentc" v-model="selectedAnswers[currentQuestionIndex]" />
                  C. {{ question.contentc }}
               </div><br>
               <div class="qcontent">
                  <input type="radio" :value="question.contentd" v-model="selectedAnswers[currentQuestionIndex]" />
                  D. {{ question.contentd }}
               </div><br>
            </div>
            <div class="qbutton">
               <button v-if="currentQuestionIndex > 0" @click="goToPreviousQuestion" style="cursor: pointer;">上一题</button>
               <span>{{ currentQuestionIndex + 1 }} of {{ length }} 跳转至第<input type="text" style="width: 20px;"
                     v-model="page">题</span>&nbsp;
               <button @click="goToSelectQuestion" style="cursor: pointer;">跳转</button>&nbsp;&nbsp;
               <button v-if="currentQuestionIndex < 29" @click="goToNextQuestion" style="cursor: pointer;">下一题</button>
               <button @click="submitAnswers" v-if="currentQuestionIndex === 29" style="cursor: pointer;">提交</button>
            </div>
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
         titles: [], // 存储所有题目  
         pageSize: 1, // 每页题目数量，这里假设为1，因为我们要手动控制“下一题”  
         currentQuestions: [], // 当前页显示的题目  
         selectedAnswers: [], // 存储用户选择的答案  
         currentQuestionIndex: 0, // 当前题目的索引,也是当前页码
         currentPage: 1,
         length: 30,
         page: '',
         correctAnswers: []
      };
   },
   components: {
      CommonFooter,
      CommonHeader
   },
   created () {
      this.fetchQuestions();
   },

   methods: {
      async fetchQuestions () {
         try {
            const response = await axios.get('http://localhost:9090/titles/gettitle');
            this.titles = response.data;
            this.correctAnswers = this.titles.map(title => title.answer);
            this.currentQuestions = [this.titles[0]];
            this.selectedAnswers = Array(Math.ceil(this.length / this.pageSize)).fill(null);
         } catch (error) {
            console.error(error);
         }
      },
      goToPreviousQuestion () {
         if (this.currentQuestionIndex > 1) {
            this.currentQuestionIndex--;
            this.updateCurrentQuestions();
         }
      },
      goToSelectQuestion () {
         this.currentQuestionIndex = Math.ceil(this.page - 1);
         this.updateCurrentQuestions();

      },
      goToNextQuestion () {
         if (this.currentQuestionIndex < this.length) {
            this.currentQuestionIndex++;
            this.updateCurrentQuestions();
         }
      },
      updateCurrentQuestions () {
         this.currentQuestions = [this.titles[this.currentQuestionIndex]];
      },
      submitAnswers () {
         let flag = 1;
         for (let i = 0; i < this.length; i++) {
            if (this.selectedAnswers[i] === null) {
               flag = 0;
            }
         }
         if (flag === 0) {
            alert("还有未作答的题目!")
         }
         else {
            const errorCounts = {}; // 用于记录每个belong的错误次数  
            let maxErrors = 0; // 记录最多的错误次数  
            let mostErrorsBelong = null; // 记录第一个最多错误的belong  

            // 遍历当前所有题目的答案  
            for (let i = 0; i < this.length; i++) {
               // 确保selectedAnswers数组有足够的元素  
               if (i < this.selectedAnswers.length && this.selectedAnswers[i] !== null) {
                  const userAnswer = this.selectedAnswers[i];
                  const correctAnswer = this.correctAnswers[i];
                  if (userAnswer !== correctAnswer) {
                     // 如果答案错误，增加对应belong的错误次数  
                     const belong = this.titles[i].belong;
                     errorCounts[belong] = (errorCounts[belong] || 0) + 1;

                     // 检查是否是当前最多错误次数的belong  
                     if (errorCounts[belong] > maxErrors) {
                        maxErrors = errorCounts[belong];
                        mostErrorsBelong = belong;
                     }
                  }
               }
            }
            const name = this.$store.state.user.name;
            this.$store.dispatch('updateUserStudyShortAction', {
               user: { name: name }, // 只传递user的id，或者传递整个user对象，取决于您的需求  
               studyShort: mostErrorsBelong // 将最多错误的belong作为studyshort  
            });
            console.log('最多错误的belong：', mostErrorsBelong, '错误次数：', maxErrors);

            axios.post('http://localhost:9090/user/update-studyshort', {
               name: name,
               belong: mostErrorsBelong
            })
               .then(response => {
                  if (response.data != '0') {
                     this.$router.push("/");
                  } else {
                     alert("出现了一些失误");
                  }
                  console.log(response.data);
               })
               .catch(error => {
                  console.error('Error posting sources:', error);
               });
         }
      }
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

      .question {
         height: 80%;
         padding-left: 20px;

         .qcontent {

            margin-top: 10px;
            margin-bottom: 20px;
            font-size: 20px;
         }
      }

      .qbutton {
         position: absolute;
         top: 80%;
         left: 35%;
      }

   }



}
</style>