<template>
    <div style="background-color: white">
        <el-form
                :rules="rules"
                :model="registerForm"
                class="loginContainer"
                v-loading="loading"
                element-loading-text="正在注册"
                element-loading-spinner="el-icon-loading"
                element-loading-background="rgba(0,0,0,0.8)"
                ref="registerForm">
            <h3 class="loginTitle">注 册</h3>
            <el-form-item prop="username">
                <el-input
                        type="text"
                        v-model="registerForm.username"
                        auto-complete="off"
                        maxlength="16"
                        minlength="2"
                        show-word-limit
                        placeholder="请输入用户名">
                </el-input>
            </el-form-item>
            <el-form-item prop="name">
                <el-input
                        type="text"
                        v-model="registerForm.name"
                        auto-complete="off"
                        maxlength="20"
                        show-word-limit
                        placeholder="请输入真实姓名">
                </el-input>
            </el-form-item>
            <el-form-item prop="password">
                <el-input
                        type="text"
                        v-model="registerForm.password"
                        auto-complete="off"
                        placeholder="请输入密码"
                        :show-password="true">
                </el-input>
            </el-form-item>
            <el-form-item prop="rePassword">
                <el-input
                        type="password"
                        v-model="registerForm.rePassword"
                        auto-complete="off"
                        placeholder="再次输入密码"
                        :show-password="true">
                </el-input>
            </el-form-item>
            <el-form-item prop="email">
                <el-input
                        type="text"
                        v-model="registerForm.email"
                        auto-complete="off"
                        placeholder="请输入email">
                </el-input>
            </el-form-item>
            <el-form-item prop="telephone">
                <el-input
                        type="text"
                        v-model="registerForm.telephone"
                        auto-complete="off"
                        placeholder="请输入手机号">
                </el-input>
            </el-form-item>
            <div style="display: flex;justify-content: space-between">
                <el-form-item prop="sex">
                    <el-select v-model="registerForm.sex" placeholder="性别" style="width: 167px">
                        <el-option
                                v-for="item in sexOptions"
                                :key="item.value"
                                :label="item.label"
                                :value="item.value">
                        </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item prop="age">
                    <el-select v-model="registerForm.age" placeholder="年龄" style="width: 166px">
                        <el-option
                                v-for="index in (1,120)"
                                :key="index"
                                :label="index"
                                :value="index">
                        </el-option>
                    </el-select>
                </el-form-item>
            </div>
            <el-button type="primary" style="width: 100%" @click="submitLogin">注册</el-button>
            <el-button type="text" @click="login">已有账号，前往登录</el-button>
        </el-form>
    </div>
</template>

<script>
    export default {
        name: "Register",
        data() {
            //判断重复密码
            var checkRePwd = (rule, value, callback) => {
                if (this.registerForm.password == this.registerForm.rePassword) {
                    this.$refs.registerForm.validateField('checkPass');
                } else {
                    callback(new Error('两次密码要输入一致'));
                    this.registerForm.rePassword = '';
                }
                callback();
            };
            return {
                registerForm: {
                    username: 'b123',
                    name: 'b123',
                    password: '12345678',
                    rePassword: '12345678',
                    email: '54006022@qq.com',
                    telephone: '13003891971',
                    age: '1',
                    sex: '',
                },
                rules: {
                    username: [
                        {required: true, message: '请输入用户名', trigger: 'blur'},
                        {min: 2, max: 16, message: '长度在 2 到 16 个字符', trigger: 'blur'},
                        {
                            required: true,
                            pattern: /^[\u4e00-\u9fa5_a-zA-Z0-9·-]+$/,
                            message: '用户名只能输入汉字，字母，数字及 _ . -',
                            trigger: 'blur'
                        }
                    ],
                    name: [
                        {required: true, message: '请输入姓名', trigger: 'blur'},
                        {min: 2, max: 20, message: '长度在 2 到 20 个字符', trigger: 'blur'},
                        {
                            required: true,
                            pattern: /^[\u4e00-\u9fa5_a-zA-Z0-9·-]+$/,
                            message: '用户名只能输入汉字，字母，数字及 _ . -',
                            trigger: 'blur'
                        }
                    ],
                    password: [
                        {required: true, message: '请输入密码', trigger: 'blur'},
                        {
                            required: true,
                            // pattern: /(?!^(\d+|[a-zA-Z]+|[~!@#$%^&*?]+)$)^[\w~!@#$%^&*?]{8,16}$/,
                            pattern: /^[\w~!@#$%^&*()_+{}|:"<>?.-]{8,16}$/,
                            message: '密码应是8-16位数字、字母或字符 ~!@#$%^&*()_+{}|:"<>?.-',
                            trigger: 'blur'
                        }
                    ],
                    rePassword: [
                        {required: true, message: '请再次输入密码', trigger: 'blur'},
                        {validator: checkRePwd, trigger: 'blur'}
                    ],
                    email: [
                        {required: true, message: '请输入邮箱', trigger: 'blur'},
                        {
                            required: true,
                            pattern: /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/,
                            message: '请输入正确的邮箱',
                            trigger: 'blur'
                        }
                    ],
                    telephone: [
                        {required: true, message: '请输入电话', trigger: 'blur'},
                        {
                            required: true,
                            pattern: /^1[3456789]\d{9}$/,
                            message: '请输入正确的手机号码',
                            trigger: 'blur'
                        }
                    ],
                    age: [{required: true, message: '请输入年龄', trigger: 'blur'}],
                    sex: [{required: true, message: '请输入性别', trigger: 'blur'}],
                },
                loading: false,
                sexOptions: [
                    {value: 1, label: '男'},
                    {value: 0, label: '女'}
                ]
            }
        },
        methods: {
            //提交表单数据
            submitLogin() {
                this.$refs.registerForm.validate((valid) => {
                    if (valid) {
                        this.loading = true;
                        this.postRequest("/user/register", this.registerForm).then(resp => {
                            this.loading = false;
                            if (resp) {
                                if(resp.obj==1){
                                    //点击确认，跳转登录页
                                    this.login();
                                }else if(resp.obj==-1){
                                    this.$message.error("用户名重复，请重新输入");
                                }else{
                                    this.$message.error("系统错误，请稍后再试");
                                }
                            }
                        })
                    } else {
                        this.$message.error("请输入所有字段");
                        return false;
                    }
                });
            },
            //跳转登录页
            login() {
                this.$router.push("/login")
            }
        }
    }
</script>

<style scoped>
    .loginContainer {
        border-radius: 15px;
        background-clip: padding-box;
        margin: 10px auto;
        width: 350px;
        padding: 35px 35px 15px 35px;
        background: #fff;
        border: 1px solid #eaeaea;
        box-shadow: 0 0 25px #cac6c6;
    }

    .loginTitle {
        margin: 0px auto 20px auto;
        text-align: center;
    }
</style>
