<template>
    <div>
        <!--头部菜单-->
        <el-menu
                class="el-menu-demo"
                mode="horizontal"
                :default-active=this.$store.state.defaultActive
                router
                @select="handleSelect">
            <el-menu-item index="/views/Home" style="margin-left: 30%">程序员小黄</el-menu-item>
            <el-menu-item index="/views/Blog">博客</el-menu-item>
<!--            <el-submenu index="3">-->
<!--                <template slot="title">系列</template>-->
<!--                <el-menu-item index="3-1">Eclipse</el-menu-item>-->
<!--                <el-menu-item index="3-2">Idea</el-menu-item>-->
<!--                <el-menu-item index="3-3">tomcat</el-menu-item>-->
<!--            </el-submenu>-->
            <el-menu-item index="/views/Diary">日记</el-menu-item>
            <el-menu-item index="/views/LeaveMessage">留言</el-menu-item>
            <el-menu-item index="/views/About" style="text-align: right">关于</el-menu-item>
            <div style="float:right;margin:10px  10px" v-if="user==null">
                <el-button type="text" @click="login">登录</el-button>
                <el-divider direction="vertical"></el-divider>
                <el-button type="text" @click="register">注册</el-button>
            </div>
            <div style="float:right;margin:20px 25px" v-else>
                <el-dropdown @command="handleClick">
                    <span class="el-dropdown-link">
                        {{user.username}}<i class="el-icon-arrow-down el-icon--right"></i>
                    </span>
                    <el-dropdown-menu slot="dropdown">
                        <el-dropdown-item command="toUserInfo">个人信息</el-dropdown-item>
                        <el-dropdown-item command="logout">退出</el-dropdown-item>
                    </el-dropdown-menu>
                </el-dropdown>
            </div>
        </el-menu>
        <router-view/>
        <!--尾部-->
        <el-footer
                class="cardBackground"
                style="height: 40px;text-align: center;text-align: inherit">
            <span style="font-size: 14px;line-height:40px">©2020程序员小黄 备案号:闽ICP备20012466号</span>
        </el-footer>
    </div>
</template>

<script>
    export default {
        name: "Index",
        data() {
            return {
                user: null,
            }
        },
        mounted() {
            this.init();
        },
        methods: {
            handleSelect(key, keyPath) {
                this.$store.state.defaultActive = key;
            },
            login() {
                this.$router.push("/login")
            },
            register() {
                this.$router.push("/register")
            },
            init() {
                let user = window.sessionStorage.getItem("user");
                this.user = eval('(' + user + ')');
            },
            handleClick(command) {
                if(command=='logout'){
                    this.logout();
                }else if(command=='toUserInfo'){
                    this.toUserInfo();
                }
            },
            logout(){
                this.$confirm('请确认是否退出', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    window.sessionStorage.setItem("user",null);
                    this.init();
                    this.$router.push("/")
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消'
                    });
                });

            },
            toUserInfo(){
                this.$router.push({
                    path: "/views/User",
                });
            }
        },
    }
</script>

<style scoped>
    .el-dropdown-link {
        cursor: pointer;
        color: #409EFF;
    }
</style>
