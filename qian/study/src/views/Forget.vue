<template>
   <div class="login">
      <div class="myforget" align="center">
         <h2 style="margin-top: 5px;margin-bottom: 5px;">忘记密码</h2>
         <el-tabs :stretch="true">
            <el-tab-pane label="用户名修改">
               <el-form :model="form" ref="formref" :rules="formrule" label-width="0px">

                  <el-form-item label="" prop="name" style="margin-top: 15px">
                     <el-row>
                        <el-col :span="2">
                           <span class="el-icon-user"></span>
                        </el-col>
                        <el-col :span="22">
                           <el-input class="inps" placeholder="请输入用户名" v-model="form.name"></el-input>
                        </el-col>
                     </el-row>
                  </el-form-item>


                  <el-form-item label="" prop="password">
                     <el-row>
                        <el-col :span="2">
                           <span class="el-icon-location"></span>
                        </el-col>
                        <el-col :span="22">
                           <el-input class="inps" type="password" placeholder="请输入新密码" v-model="form.password"></el-input>
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

                  <el-form-item style="margin-top: 10px">
                     <el-button style="border-color: #10ad67; background-color: #10ad67;" type="primary" round
                        class="handleClick" @click="backpage">返回
                     </el-button>
                     <el-button style="border-color: #10ad67; background-color: #10ad67;" type="primary" round
                        class="handleClick" @click="forget">确认
                     </el-button>
                  </el-form-item>

               </el-form>
            </el-tab-pane>
            <el-tab-pane label="手机号修改">
               <el-form :model="sform" ref="sformref" :rules="sformrule" label-width="0px">

                  <el-form-item label="" prop="phone">
                     <el-row>
                        <el-col :span="2">
                           <span class="el-icon-user"></span>
                        </el-col>
                        <el-col :span="22">
                           <el-input class="inps" placeholder="请输入手机号" v-model="sform.phone"></el-input>
                        </el-col>
                     </el-row>
                  </el-form-item>


                  <el-form-item label="" prop="password">
                     <el-row>
                        <el-col :span="2">
                           <span class="el-icon-location"></span>
                        </el-col>
                        <el-col :span="22">
                           <el-input class="inps" type="password" placeholder="请输入新密码" v-model="sform.password"></el-input>
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
                              v-model="sform.confirmedPassword"></el-input>
                        </el-col>
                     </el-row>
                  </el-form-item>

                  <el-form-item style="margin-top: 10px">
                     <el-button style="border-color: #10ad67; background-color: #10ad67;" type="primary" round
                        class="handleClick" @click="backpage">返回
                     </el-button>
                     <el-button style="border-color: #10ad67; background-color: #10ad67;" type="primary" round
                        class="handleClick" @click="forgetc">确认
                     </el-button>
                  </el-form-item>
               </el-form>
            </el-tab-pane>
         </el-tabs>
      </div>
   </div>
</template>

<script>
import { reactive, ref } from "vue";
const sform = reactive({});
const sformref = ref(null);
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
   name: "Forget",
   data: function () {
      return {
         form: {
            name: "",
            password: "",
            confirmedPassword: "",
         },
         sform: {
            phone: "",
            password: "",
            confirmedPassword: "",
         },
         sformref,
         formref,
         formrule: {
            name: [{
               required: true,
               message: "用户名不能为空!"
            }],
            password: [{
               required: true,
               message: "密码不能为空!",
            }, {
               validator: validatePassword
            }],
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
         },
         sformrule: {
            phone: [{
               required: true,
               message: "手机号不能为空!",
            }, {
               validator: validatePhone
            }],
            password: [{
               required: true,
               message: "密码不能为空!",
            }, {
               validator: validatePassword
            }],
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
      }
   },

   methods: {
      backpage () {
         this.$router.go(-1)
      },
      forget () {
         this.$axios.post("http://localhost:9090/user/forget", {
            name: this.form.name,
            password: this.form.password,
         }, {
            headers: {
               'Content-Type': 'application/json'
            }
         }).then(response => {
            if (response.data != '0') {
               alert("修改成功");
               this.$router.push({
                  path: '/login'
               });
            } else {
               alert("修改失败");
            }
            console.log(response.data);
         }).catch(error => {
            console.log(error);
            alert("修改失败！");
         })
      },
      forgetc () {
         this.$axios.post("http://localhost:9090/user/forgetc", {
            phone: this.form.phone,
            password: this.form.password,
         }, {
            headers: {
               'Content-Type': 'application/json'
            }
         }).then(response => {
            if (response.data != '0') {
               alert("修改成功");
               this.$router.push({
                  path: '/login'
               });
            } else {
               alert("修改失败");
            }
            console.log(response.data);
         }).catch(error => {
            console.log(error);
            alert("修改失败！");
         })
      }
   }
};
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

.myforget {
   width: 290px;
   height: 350px;
   position: absolute;
   top: 26%;
   left: 63.5%;
   margin: auto;
   padding: 15px 40px 50px 40px;
   box-shadow: 15px 15px 15px rgba(6, 17, 47, 0.7);
   opacity: 1;
   background: linear-gradient(230deg,
         rgba(53, 57, 74, 0) 0%,
         rgb(8, 51, 40) 100%);

   .el-tab-pane {
      height: 250px;
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
   font-size: 12px;
}

.submitBtn {
   background-color: transparent;
   color: #39f;
   width: 200px;
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