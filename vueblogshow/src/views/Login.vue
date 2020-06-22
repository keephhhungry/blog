<template>
    <div style="background-color: white">
        <el-form
                :rules="rules"
                ref="loginForm"
                v-loading="loading"
                element-loading-text="正在登录..."
                element-loading-spinner="el-icon-loading"
                element-loading-background="rgba(0, 0, 0, 0.8)"
                :model="loginForm"
                class="loginContainer">
            <h3 class="loginTitle">系统登录</h3>
            <el-form-item prop="username">
                <el-input size="normal" type="text" v-model="loginForm.username" auto-complete="off"
                          placeholder="请输入用户名"></el-input>
            </el-form-item>
            <el-form-item prop="password">
                <el-input size="normal" type="password" v-model="loginForm.password" auto-complete="off"
                          placeholder="请输入密码"></el-input>
            </el-form-item>
            <el-form-item prop="code">
                <el-input size="normal" type="text" v-model="loginForm.code" auto-complete="off"
                          placeholder="点击图片更换验证码" @keydown.enter.native="submitLogin" style="width: 230px"></el-input>
                <img :src="vcUrl" @click="updateVerifyCode" alt="" style="cursor: pointer;margin-left: 10px">
            </el-form-item>
            <el-button size="normal" type="primary" style="width: 100%;" @click="submitLogin">登录</el-button>
            <el-button type="text" @click="toRegister">没有账号,点击注册</el-button>
        </el-form>
    </div>
</template>

<script>

    export default {
        name: "Login",
        data() {
            return {
                loading: false,
                vcUrl: '/verifyCode?time=' + new Date(),
                loginForm: {
                    username: 'a123',
                    password: '12345678',
                    code: ''
                },
                checked: true,
                rules: {
                    username: [{required: true, message: '请输入用户名', trigger: 'blur'}],
                    password: [{required: true, message: '请输入密码', trigger: 'blur'}],
                    code: [{required: true, message: '请输入验证码', trigger: 'blur'}]
                }
            }
        },
        methods: {
            //获取验证码
            updateVerifyCode() {
                this.vcUrl = '/verifyCode?time=' + new Date();
            },
            //登录
            submitLogin() {
                this.$refs.loginForm.validate((valid) => {
                    if (valid) {
                        this.loading = true;
                        this.postRequest("/doLogin?username=" + this.loginForm.username + "&password=" + this.loginForm.password + "&code=" + this.loginForm.code).then(resp => {
                            this.loading = false;
                            if (resp && resp.status == 200) {
                                window.sessionStorage.setItem("user", JSON.stringify(resp.obj));
                                this.toHome();
                            } else {
                                this.vcUrl = '/verifyCode?time=' + new Date();
                            }
                        })
                    } else {
                        this.$message.error("请输入所有字段");
                        return false;
                    }
                });
            },
            //前往主页
            toHome(){
                this.$router.push("/")
            },
            //前往注册页
            toRegister(){
                this.$router.push("/register")
            },
        }
    }
</script>

<style>
    .loginContainer {
        border-radius: 15px;
        background-clip: padding-box;
        margin: 180px auto;
        width: 350px;
        padding: 15px 35px 15px 35px;
        background: #fff;
        border: 1px solid #eaeaea;
        box-shadow: 0 0 25px #cac6c6;
    }

    .loginTitle {
        margin: 15px auto 20px auto;
        text-align: center;
        color: #505458;
    }

    .loginRemember {
        text-align: left;
        margin: 0px 0px 15px 0px;
    }

    .el-form-item__content {
        display: flex;
        align-items: center;
    }
</style>
