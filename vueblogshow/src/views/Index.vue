<template>
    <div>
        <!--头部菜单-->
        <el-menu
                 class="el-menu-demo"
                 mode="horizontal"
                 router>
            <!--                         @select="handleSelect">-->
            <el-menu-item index="/views/Home" style="margin-left: 50%">程序员小黄</el-menu-item>
            <el-menu-item index="/views/Blog">博客</el-menu-item>
            <el-submenu index="3">
                <template slot="title">系列</template>
                <el-menu-item index="3-1">Eclipse</el-menu-item>
                <el-menu-item index="3-2">Idea</el-menu-item>
                <el-menu-item index="3-3">tomcat</el-menu-item>
            </el-submenu>
            <el-menu-item index="/views/Diary">日记</el-menu-item>
            <el-menu-item index="/views/LeaveMessage">留言</el-menu-item>
            <el-menu-item index="/views/About">关于</el-menu-item>
            <el-menu-item index="7">
                <el-button type="text">登录/注册</el-button>
            </el-menu-item>
        </el-menu>
        <router-view/>
    </div>
</template>

<script>
    export default {
        name: "Index",
        mounted() {

        },
        methods:{
        }
    }
</script>

<style scoped>

</style>
