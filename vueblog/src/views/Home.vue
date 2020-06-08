<template>
    <div>
        <el-container>
            <el-header class="homeHeader">
                <div class="title">程序员小黄</div>
                <el-dropdown class="userInfo" @command="commandHandler">
                    <span class="el-dropdown-link">
                        {{user.name}}<i><img :src='user.userface' alt=""></i>
                    </span>
                    <el-dropdown-menu slot="dropdown">
                        <el-dropdown-item command="userinfo">个人中心</el-dropdown-item>
                        <el-dropdown-item command="setting">设置</el-dropdown-item>
                        <el-dropdown-item command="logout" divided>注销登录</el-dropdown-item>
                    </el-dropdown-menu>
                </el-dropdown>
            </el-header>
            <el-container>
                <el-aside width="200px">
                    <el-menu router>
                        <el-submenu :index="index+''" v-for="(item,index) in routes" v-if="!item.hidden" :key="index">
                            <template slot="title">
                                <i style="margin-right: 5px" :class="item.iconCls"></i>
                                <span>{{item.name}}</span>
                            </template>
                            <el-menu-item :index="child.path" v-for="(child,indexj) in item.children" :key="indexj">
                                {{child.name}}
                            </el-menu-item>
                        </el-submenu>
                    </el-menu>
                </el-aside>
                <el-main>
                    <el-breadcrumb separator-class="el-icon-arrow-right" v-if="this.$router.currentRoute.path!='/home'">
                        <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
                        <el-breadcrumb-item>{{this.$router.currentRoute.name}}</el-breadcrumb-item>
                    </el-breadcrumb>
                    <div style="text-align: center" v-if="this.$router.currentRoute.path=='/home'">
                        欢迎来到程序员小黄博客
                    </div>
                    <router-view class="homeRouterView"/>
                </el-main>
            </el-container>
        </el-container>
    </div>
</template>

<script>
    export default {
        name: "Home",
        data() {
            return {
                user: JSON.parse(window.sessionStorage.getItem('user')),
            }
        },
        computed: {
            routes() {
                return this.$store.state.routes;
            }
        },
        methods: {
            commandHandler(cmd) {
                if (cmd == 'logout') {
                    this.$confirm('请确认是否退出', '提示', {
                        confirmButtonText: '确定',
                        cancelButtonText: '取消',
                        type: 'warning'
                    }).then(() => {
                        this.getRequest("/logout");
                        window.sessionStorage.removeItem('user');
                        this.$store.commit('initRoutes', []);
                        this.$router.replace('/');
                    }).catch(() => {
                        this.$message({
                            type: 'info',
                            message: '已取消'
                        });
                    });
                } else if (cmd == 'userinfo') {

                } else if (cmd == 'setting') {

                }
            }
        }
    }
</script>

<style>
    .homeHeader {
        background-color: red;
        display: flex;
        align-items: center;
        justify-content: space-between;
        padding: 0px 15px;
        box-sizing: border-box;
    }

    .homeHeader .title {
        font-size: 30px;
        font-family: 宋体;
        color: white;
    }

    .userInfo {
        cursor: pointer;
    }

    .el-dropdown-link img {
        width: 48px;
        height: 48px;
        border-radius: 24px;
        margin-left: 5px;
    }

    .el-dropdown-link {
        display: flex;
        align-items: center;
    }

    .homeRouterView{
        margin-top: 15px;
    }
</style>
