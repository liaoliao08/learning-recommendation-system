<template>
   <div class="login">
      <div style="position: absolute; width:350px;height:100px;left:64.5%;top:21%;margin-top:-50px;font-size: 43px;">
         <div class="ceshi">中&nbsp;文&nbsp;学&nbsp;习&nbsp;平&nbsp;台</div>
      </div>
      <div class="mylogin" align="center">
         <el-tabs :stretch="true">
            <el-tab-pane label="账号密码登录">
               <!-- 账号密码登录表单 -->
               <el-form :model="pwdform" ref="pwdformref" :rules="pwdformrule" label-width="0px">
                  <!-- 用户名 -->
                  <el-form-item label="" prop="name" style="margin-top: 15px">
                     <el-row>
                        <el-col :span="2">
                           <span class="el-icon-s-custom"></span>
                        </el-col>
                        <el-col :span="22">
                           <el-input class="inps" placeholder="请输入用户名" v-model="pwdform.name">
                           </el-input>
                        </el-col>
                     </el-row>
                  </el-form-item>
                  <!-- 密码 -->
                  <el-form-item prop="password" style="margin-top: 20px;margin-bottom: 10px;">
                     <el-row>
                        <el-col :span="2">
                           <span class="el-icon-lock"></span>
                        </el-col>
                        <el-col :span="22">
                           <el-input class="inps" type="password" placeholder="请输入密码"
                              v-model="pwdform.password"></el-input>
                        </el-col>
                     </el-row>
                  </el-form-item>
                  <el-form-item label="" prop="code" style="margin-top: 15px">
                     <el-row>
                        <el-col :span="2">
                           <span class="el-icon-question"></span>
                        </el-col>
                        <el-col :span="20" style="display: flex;flex-direction: row;">
                           <el-input class="inps" style="width: 70%;" placeholder="请输入验证码" v-model="pwdform.code"
                              maxlength="6" @keyup.enter.native="submitLogin" />
                           <el-image id="captcha-img" class="codeImg" style="width: 100px; height: 40px;"
                              :src="captchaImage" @click="refreshCaptcha"></el-image>
                        </el-col>
                     </el-row>
                  </el-form-item>
                  <!-- 按钮区域 -->
                  <el-form-item style="margin-top: 40px;margin-bottom: 15px;">
                     <el-button style=" border-color: #10ad67; background-color: #10ad67;" type="primary" round
                        class="handleClick" @click="login">登录
                     </el-button>
                  </el-form-item>

                  <div class="unlogin">
                     <router-link :to="{ path: '/forget' }">
                        <a href="Forget.vue" target="_blank" align="right">忘记密码</a>
                     </router-link>
                     &nbsp;&nbsp;&nbsp;
                     <router-link :to="{ path: '/register' }">
                        <a href="Register.vue" target="_blank" align="right">注册账号</a>
                     </router-link>
                  </div>
               </el-form>
            </el-tab-pane>


            <el-tab-pane label="手机号登录">
               <el-form :model="form" ref="formref" :rules="formrule" label-width="0px">
                  <!-- 手机号 -->
                  <el-form-item label="" prop="phone" style="margin-top: 15px">
                     <el-row>
                        <el-col :span="2">
                           <span class="el-icon-question"></span>
                        </el-col>
                        <el-col :span="22">
                           <el-input class="inps" placeholder="请输入手机号" v-model="form.phone" />
                        </el-col>
                     </el-row>
                  </el-form-item>
                  <el-form-item prop="password" style="margin-top: 20px;margin-bottom: 10px;">
                     <el-row>
                        <el-col :span="2">
                           <span class="el-icon-lock"></span>
                        </el-col>
                        <el-col :span="22">
                           <el-input class="inps" type="password" placeholder="请输入密码" v-model="form.password"></el-input>
                        </el-col>
                     </el-row>
                  </el-form-item>
                  <el-form-item label="" prop="code" style="margin-top: 15px">
                     <el-row>
                        <el-col :span="2">
                           <span class="el-icon-question"></span>
                        </el-col>
                        <el-col :span="20" style="display: flex;flex-direction: row;">
                           <el-input class="inps" style="width: 70%;" placeholder="请输入验证码" v-model="form.code"
                              maxlength="6" @keyup.enter.native="submitLogin" />
                           <el-image id="captcha-img" class="codeImg" style="width: 100px; height: 40px;"
                              :src="captchaImage" @click="refreshCaptcha"></el-image>
                        </el-col>
                     </el-row>
                  </el-form-item>
                  <!-- 按钮区域 -->
                  <el-form-item style="margin-top: 40px;margin-bottom: 15px;">
                     <el-button style="border-color: #10ad67; background-color: #10ad67;" type="primary" round
                        class="handleClick1" @click="loginc">登录
                     </el-button>
                  </el-form-item>
                  <div class="unlogin">
                     <router-link :to="{ path: '/register' }">
                        <a href="register.vue" target="_blank" align="right">注册账号</a>
                     </router-link>
                  </div>
               </el-form>
            </el-tab-pane>
         </el-tabs>
      </div>
   </div>
</template>

<script>
import axios from "axios";
import { reactive, ref } from "vue";

const pwdformref = ref(null);
const pwdform = reactive({});
const formref = ref(null);
const form = reactive({});

const validatePassword = (rule, value, callback) => {
   if (value.length < 6) {
      callback(new Error('密码不能小于6位'))
   } else {
      callback()
   }
};
const validatePhone = (rule, value, callback) => {
   const reg = /^[1][3-9][0-9]{9}$/;
   if (value == '' || value == undefined || value == null) {
      callback(new Error('请输入手机号码'));
   } else {
      if ((!reg.test(value)) && value != '') {
         callback(new Error('请输入正确的手机号码'));
      } else {
         callback();
      }
   }
};
export default {
   name: "Login",
   data () {
      return {
         captchaImage: '',
         form: {
            phone: "",
            password: "",
            code: ""
         },
         pwdform: {
            name: "",
            password: "",
            code: ""
         },
         pwdformref,
         formref,
         formrule: {
            phone: [{
               required: true,
               message: "手机号不能为空!",
            }, {
               validator: validatePhone


            }],
            password: [{
               required: true,
               message: "密码不能为空!"
            }],
            code: [{
               required: true,
               message: "验证码不能为空！"
            }]
         },
         pwdformrule: {
            name: [{
               required: true,
               message: "用户名不能为空!"
            }],
            password: [{
               required: true,
               message: "密码不能为空!"
            }, {
               validator: validatePassword
            }],
            code: [{
               required: true,
               message: "验证码不能为空！"
            }]
         },
      };

   },
   methods: {
      async fetchCaptcha () {
         try {
            const response = await axios.get('http://localhost:9090/user/getcode', {
               responseType: 'blob', // 告诉axios我们期望返回的是Blob对象  
               withCredentials: true
            });

            // 创建一个Blob URL  
            this.captchaImage = URL.createObjectURL(new Blob([response.data]));

         } catch (error) {
            console.error('Error fetching captcha:', error);
         }
      },
      refreshCaptcha () {
         this.fetchCaptcha();
      },
      //用户密码登录
      login () {
         this.$axios.post('http://localhost:9090/user/login', {
            name: this.pwdform.name,
            password: this.pwdform.password,
            code: this.pwdform.code
         }, {
            headers: {
               'Content-Type': 'application/json'
            },
            withCredentials: true // 允许跨域请求携带凭据  
         }).then(response => {
            // 假设后端在登录成功时返回一个User对象，否则返回一个错误消息  
            if (response.data && typeof response.data === 'object' && 'name' in response.data) {
               const user = response.data;
               alert("登陆成功");
               this.$store.commit('setUser', user);
               this.$router.push({
                  path: '/',
               });
            } else {
               // 这里可以处理后端返回的错误信息，或者简单地显示一个通用错误消息  
               // 确保response.data是一个字符串或对象，以便可以安全地显示给用户  
               let errorMessage = response.data ? (typeof response.data === 'string' ? response.data : '未知错误') : '未知错误';
               alert("登陆失败：" + errorMessage);
               this.refreshCaptcha();
            }
            console.log(response.data);
         }).catch(error => {
            // 在这里处理网络错误或请求失败的情况  
            // 检查错误类型，并可能从错误响应中提取更多信息  
            if (error.response) {
               // 请求已发出且服务器也响应了状态码不在2xx的响应  
               console.log(error.response.data); // 响应体  
               console.log(error.response.status); // 状态码  
               console.log(error.response.headers); // 响应头  

               // 提取并显示错误信息（如果后端提供了）  
               let errorMessage = error.response.data ? (typeof error.response.data === 'string' ? error.response.data : '服务器错误') : '服务器错误';
               alert("登陆失败！原因：" + errorMessage);
            } else if (error.request) {
               // 请求已发出，但没有收到响应  
               console.log(error.request);
               alert("登陆失败！请检查您的网络连接。");
            } else {
               // 发生了某些问题导致请求无法发送  
               console.log('Error', error.message);
               alert("登陆失败！请检查您的网络连接或输入信息。");
            }
            this.refreshCaptcha();
         });
      },

      //手机号登录
      loginc () {
         this.$axios.post('http://localhost:9090/user/loginc', {
            phone: this.form.phone,
            password: this.form.password,
            code: this.form.code
         }, {
            headers: {
               'Content-Type': 'application/json'
            },
            withCredentials: true // 允许跨域请求携带凭据  
         }).then(response => {
            // 假设后端在登录成功时返回一个User对象，否则返回一个错误消息  
            if (response.data && typeof response.data === 'object' && 'name' in response.data) {
               const user = response.data;
               alert("登陆成功");
               this.$store.commit('setUser', user);
               this.$router.push({
                  path: '/',
               });
            } else {
               // 这里可以处理后端返回的错误信息，或者简单地显示一个通用错误消息  
               // 确保response.data是一个字符串或对象，以便可以安全地显示给用户  
               let errorMessage = response.data ? (typeof response.data === 'string' ? response.data : '未知错误') : '未知错误';
               alert("登陆失败：" + errorMessage);
               this.refreshCaptcha();
            }
            console.log(response.data);
         }).catch(error => {
            // 在这里处理网络错误或请求失败的情况  
            // 检查错误类型，并可能从错误响应中提取更多信息  
            if (error.response) {
               // 请求已发出且服务器也响应了状态码不在2xx的响应  
               console.log(error.response.data); // 响应体  
               console.log(error.response.status); // 状态码  
               console.log(error.response.headers); // 响应头  

               // 提取并显示错误信息（如果后端提供了）  
               let errorMessage = error.response.data ? (typeof error.response.data === 'string' ? error.response.data : '服务器错误') : '服务器错误';
               alert("登陆失败！原因：" + errorMessage);
            } else if (error.request) {
               // 请求已发出，但没有收到响应  
               console.log(error.request);
               alert("登陆失败！请检查您的网络连接。");
            } else {
               // 发生了某些问题导致请求无法发送  
               console.log('Error', error.message);
               alert("登陆失败！请检查您的网络连接或输入信息。");
            }
            this.refreshCaptcha();
         });
      }
   },
   mounted () {
      this.fetchCaptcha();
   }
}
</script>


<style>
.login {
   width: 100vw;
   padding: 0;
   margin: 0;
   height: 100vh;
   font-size: 18px;
   background-position: left top;
   background: url('../assets/bei.png') no-repeat center center;
   background-size: cover;
   color: #fff;
   font-family: "Source Sans Pro";
   position: relative;
}

.ceshi {
   background: linear-gradient(to right, #1dc76c, #0f8948);
   /*设置渐变的方向从左到右 颜色从ff0000到ffff00*/
   background-clip: text;
   -webkit-background-clip: text;
   /*将设置的背景颜色限制在文字中*/
   -webkit-text-fill-color: transparent;
   /*给文字设置成透明*/
}


.mylogin {
   width: 290px;
   height: 350px;
   position: absolute;
   top: 26%;
   left: 63.5%;
   margin: auto;
   padding: 15px 40px 20px 40px;
   box-shadow: 15px 15px 15px rgba(6, 17, 47, 0.7);
   opacity: 1;
   background: linear-gradient(230deg,
         rgba(53, 57, 74, 0) 0%,
         rgb(8, 51, 40) 100%);

   .el-tab-pane {
      height: 300px;
   }

   .el-tabs__header {
      height: 40px;
   }

   .el-tabs__header is-top {
      height: 40px;
   }

   .el-tabs__item:hover {
      color: #137e3c;
   }

   .el-tabs__item {
      color: #7dbdf8;
   }

   .el-tabs__active-bar {
      color: #1f960f;
      background-color: #1f960f;
      width: 127px;
   }

   .el-tabs__item.is-active {
      color: #0fad59;
   }

}

.inps input {
   border: none;
   color: #fff;
   background-color: transparent;
   font-size: 13px;
}

.submitBtn {
   background-color: transparent;
   color: rgb(237, 84, 33);
   width: 200px;
}

.router-link-active {
   text-decoration: none;
   color: #f00606;
}

a {
   text-decoration: none;
   color: #c0a8a8;
   font-size: 15px;
}
</style>