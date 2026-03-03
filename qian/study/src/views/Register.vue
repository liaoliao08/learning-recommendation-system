<template>
   <div class="login">
      <div class="myregister" align="center">
         <h2>注册</h2>
         <el-form :model="form" ref="formref" :rules="formrule" label-width="0px">
            <el-form-item label="" prop="name" style="margin-top: 2px">
               <el-row>
                  <el-col :span="2">
                     <span class="el-icon-s-custom"></span>
                  </el-col>
                  <el-col :span="22">
                     <el-input class="inps" placeholder="请输入用户名" v-model="form.name">
                     </el-input>
                  </el-col>
               </el-row>
            </el-form-item>
            <el-form-item label="" prop="phone" style="margin-top: 2px">
               <el-row>
                  <el-col :span="2">
                     <span class="el-icon-receiving"></span>
                  </el-col>
                  <el-col :span="22" style="width: auto;">
                     <el-input class="inps" placeholder="请输入手机号" v-model="form.phone" />
                  </el-col>

               </el-row>
            </el-form-item>


            <el-form-item label="" prop="password">
               <el-row>
                  <el-col :span="2">
                     <span class="el-icon-lock"></span>
                  </el-col>
                  <el-col :span="22">
                     <el-input class="inps" type="password" placeholder="请输入密码" v-model="form.password"></el-input>
                  </el-col>
               </el-row>
            </el-form-item>

            <el-form-item label="" prop="confirmedPassword">
               <el-row>
                  <el-col :span="2">
                     <span class="el-icon-warning"></span>
                  </el-col>
                  <el-col :span="22">
                     <el-input class="inps" type="password" placeholder="请再次输入密码"
                        v-model="form.confirmedPassword"></el-input>
                  </el-col>
               </el-row>
            </el-form-item>
            <el-form-item label="" prop="code">
               <el-row>
                  <el-col :span="2">
                     <span class="el-icon-question"></span>
                  </el-col>
                  <el-col :span="20" style="display: flex;flex-direction: row;">
                     <el-input class="inps" style="width: 70%;" placeholder="请输入验证码" v-model="form.code" maxlength="6"
                        @keyup.enter.native="submitLogin" />
                     <el-image id="captcha-img" class="codeImg" style="width: 100px; height: 40px;" :src="captchaImage"
                        @click="refreshCaptcha"></el-image>
                  </el-col>
               </el-row>
            </el-form-item>
            <el-form-item style="margin-top: 10px">
               <el-button style="border-color: #10ad67; background-color: #10ad67;" type="primary" round
                  class="handleClick" @click="backpage">返回</el-button>
               <el-button style="border-color: #10ad67; background-color: #10ad67;" type="primary" round
                  class="handleClick" @click="register">注册</el-button>
            </el-form-item>

         </el-form>
      </div>
   </div>
</template>

<script>

import router from "@/router";
import axios from "axios";
import { reactive, ref } from "vue";

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
   const reg = /^[1][3-9][0-9]\d{9}$/;
   if (value === '' || value === undefined || value === null) {
      callback(new Error('请输入手机号码!!'));
   } else {
      if ((!reg.test(String(value)))) {
         callback(new Error('请输入正确的手机号码'));
      } else {
         callback();
      }
   }
};
export default {
   name: "Register",
   data () {
      return {
         captchaImage: '',
         form: {
            name: '',
            phone: '',
            password: '',
            code: '',
            confirmedPassword: '',
         },
         formrule: {
            name: [{
               required: true,
               message: "用户名不能为空!"
            }],
            phone: [
               { required: true, message: '请输入手机号' },
               { validator: validatePhone },
            ],
            code: [{
               required: true,
               message: '请输入验证码',

            }],
            password: [
               { required: true, message: '请输入密码' },
               { validator: validatePassword }],
            confirmedPassword: [{
               required: true,
               validator: (rule, value, callback) => {
                  if (value === '') {
                     callback(new Error('请再次输入密码'))
                  } else if (value !== this.form.password) {
                     callback(new Error('两次输入密码不一致'))
                  } else {
                     callback()
                  }
               },
            }],
         }

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
      backpage () {
         this.$router.go(-1);
      },
      register () {
         axios({
            method: 'post',
            url: 'http://localhost:9090/user/register',
            data: {
               name: this.form.name,
               phone: this.form.phone,
               password: this.form.password,
               code: this.form.code
            },
            withCredentials: true // 如果需要发送跨域请求的凭据  
         }).then(response => {
            console.log(response.data);
            if (response.data === 1) {
               alert("注册成功!")
               this.$router.push("/login");
            }
            else if (response.data === 2) {
               alert("验证码输入错误！"),
                  this.refreshCaptcha();
            }
            else if (response.data === 3) {
               alert("用户名已存在！"),
                  this.refreshCaptcha();
            }
            else {
               alert("注册失败!")
            }
         })
            .catch(error => {
               console.error('Error!');
            });

      },
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
   font-size: 16px;
   background-position: left top;
   background: url('../assets/bei.png') no-repeat center center;
   background-size: cover;
   color: #fff;
   font-family: "Source Sans Pro";
   position: relative;
}

.myregister {
   width: 290px;
   height: 400px;
   position: absolute;
   top: 20%;
   left: 63.5%;
   margin: auto;
   padding: 15px 40px 50px 40px;
   box-shadow: 15px 15px 15px rgba(6, 17, 47, 0.7);
   opacity: 1;
   background: linear-gradient(230deg,
         rgba(53, 57, 74, 0) 0%,
         rgb(8, 51, 40) 100%);
}

.inps input {
   border: none;
   color: #fff;
   background-color: transparent;
   font-size: 12px;
}

.submitBtn {
   background-color: transparent;
   color: #39f;
   width: 200px;
}

.codeImg {
   float: right;
}

.router-link-active {
   text-decoration: none;
   color: #b9b1b1;
}

a {
   text-decoration: none;
   color: #c0a8a8;
}
</style>