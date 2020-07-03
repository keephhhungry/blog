<template>
    <div>
        <el-form
                :rules="rules"
                :model="modifyForm"
                class="loginContainer"
                v-loading="loading"
                element-loading-text="正在注册"
                element-loading-spinner="el-icon-loading"
                element-loading-background="rgba(0,0,0,0.8)"
                ref="modifyForm">
            <el-form-item prop="username">
                <el-avatar shape="square"
                           :size=80
                           src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png">
                </el-avatar>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" size="mini" @click="submitLogin">点击上传头像</el-button>
            </el-form-item>
            <el-form-item prop="username">
                <el-input
                        type="text"
                        v-model="modifyForm.username"
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
                        v-model="modifyForm.name"
                        auto-complete="off"
                        maxlength="20"
                        show-word-limit
                        placeholder="请输入真实姓名">
                </el-input>
            </el-form-item>
            <el-form-item prop="email">
                <el-input
                        type="text"
                        v-model="modifyForm.email"
                        auto-complete="off"
                        placeholder="请输入email">
                </el-input>
            </el-form-item>
            <el-form-item prop="telephone">
                <el-input
                        type="text"
                        v-model="modifyForm.telephone"
                        auto-complete="off"
                        placeholder="请输入手机号">
                </el-input>
            </el-form-item>
            <div style="display: flex;justify-content: space-between">
                <el-form-item prop="sex">
                    <el-select v-model="modifyForm.sex" placeholder="性别" style="width: 167px">
                        <el-option
                                v-for="item in sexOptions"
                                :key="item.value"
                                :label="item.label"
                                :value="item.value">
                        </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item prop="age">
                    <el-select v-model="modifyForm.age" placeholder="年龄" style="width: 166px">
                        <el-option
                                v-for="index in (1,120)"
                                :key="index"
                                :label="index"
                                :value="index">
                        </el-option>
                    </el-select>
                </el-form-item>
            </div>
            <el-button type="primary" style="width: 48%" @click="submitLogin">还原</el-button>
            <el-button type="primary" style="width: 48%" @click="submitLogin">提交修改</el-button>
        </el-form>
    </div>
</template>

<script>
    export default {
        name: "UserInfo",
        data() {
            return {
                modifyForm: {
                    username: '',
                    name: '',
                    email: '',
                    telephone: '',
                    age: '',
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
                ],
                user:'',
            }
        },
        mounted() {
            this.init();
        },
        methods: {
            //提交表单数据
            submitLogin() {
                this.$refs.modifyForm.validate((valid) => {
                    if (valid) {
                        this.loading = true;
                        this.postRequest("/user/register", this.modifyForm).then(resp => {
                            this.loading = false;
                            if (resp) {
                                if (resp.obj == 1) {
                                    //点击确认，跳转登录页
                                    this.$alert('恭喜你，注册成功，账号【' + this.modifyForm.username + '】，点击确定前往登录页', '注册成功', {
                                        confirmButtonText: '确定',
                                        callback: action => {
                                            this.login();
                                        }
                                    });
                                } else if (resp.obj == -1) {
                                    this.$message.error("用户名重复，请重新输入");
                                } else {
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
            //还原
            reduction(){

            },
            //改变头像
            changeImage(){

            },
            //初始化
            init() {
                let user = window.sessionStorage.getItem("user");
                this.user = eval('(' + user + ')');
            },
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
