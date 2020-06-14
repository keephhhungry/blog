<template>
    <div>
        <el-container style="width: 100%">
            <!--中间-->
            <el-main
                    v-loading="loading"
                    element-loading-text="正在拼命加载中..."
                    element-loading-spinner="el-icon-loading"
                    element-loading-background="rgba(0,0,0,0.1)"
                    style="margin-bottom: 30px">
                <el-row :gutter="30">
                    <!--左侧-->
                    <el-col :span="5">
                        <!--个人信息-->
                        <el-card class="box-card">
                            <img src="../assets/images/public.jpg" height="180" width="180" title="扫码关注公众号">
                            <div>
                                <span>程序员小黄</span>
                            </div>
                            <div>
                                <i class="el-icon-position"></i>
                                <span>China ShangHai</span>
                            </div>
                            <div style="display: flex;justify-content: space-between ; margin-top: 10px">
                                <div style="margin-left: 40px">
                                    文章
                                </div>
                                <div style="margin-right: 40px">
                                    分类
                                </div>
                            </div>
                            <div style="display: flex;justify-content: space-between">
                                <div style="margin-left: 40px">
                                    <span style="padding-left: 6px">{{articleNum}}</span>
                                </div>
                                <div style="margin-right: 40px">
                                    <span style="padding-right: 12px">{{articleTypes.length}}</span>
                                </div>
                            </div>
                            <div style="margin-top: 5px">
                                <el-button type="primary" size="mini" style="width: 100%" round>扫码关注公众号</el-button>
                            </div>
                            <div>
                                <el-button type="text" style="margin-right: 40px"><i class="el-icon-position"></i>
                                </el-button>
                                <el-button type="text" style="margin-right: 40px"><i class="el-icon-position"></i>
                                </el-button>
                                <el-button type="text"><i class="el-icon-position"></i></el-button>
                            </div>
                        </el-card>
                        <!--热门文章-->
                        <el-card class="box-card" style="margin-top: 20px">
                            <span style="font-size: 15px">热门文章</span>
                            <el-divider></el-divider>
                            <el-menu
                                    class="el-menu-vertical-demo">
                                <el-menu-item :index="index+''" style="height: 30px;line-height:30px;text-align: left"
                                              v-for="(item,index) in hotArticles" :key="index">
                                    <span>{{item.articleTitle}}</span>
                                </el-menu-item>
                            </el-menu>
                        </el-card>
                        <!--友情链接-->
                        <el-card class="box-card" style="margin-top: 20px">
                            <span style="font-size: 15px">友链</span>
                            <el-divider></el-divider>
                            <el-menu
                                    class="el-menu-vertical-demo">
                                <el-menu-item
                                        :index="index+''"
                                        class="friendLinkMenuItem"
                                        v-for="(item,index) in friendLinks"
                                        :key="index"
                                        @click="openFriendLink(item.linkUrl)">
                                    <span>{{item.linkName}}</span>
                                </el-menu-item>
                            </el-menu>
                        </el-card>
                    </el-col>
                    <!--博客-->
                    <el-col :span="14" style="text-align: left">
                        <el-card class="box-card" style="margin-bottom: 20px" v-for="(item,index) in articles"
                                 :key="index">
                            <div>
                                <span style="color: darkgrey;font-size: 10px">{{item.gmtCreate}}   {{item.articleType.typeName}}</span>
                            </div>
                            <div style="margin-top: 5px">
                                <el-link href="http://www.baidu.com"
                                         target="_blank"
                                         type="info"
                                         :underline="false"
                                         style="font-size: 30px">{{item.articleTitle}}
                                </el-link>
                            </div>
                            <div style="margin-top: 5px">
                                <span>{{item.articleIntroduction}}</span>
                            </div>
                            <el-button type="info" size="mini" style="margin-top: 5px;padding: 5px 7px">
                                阅读更多
                            </el-button>
                        </el-card>
                        <el-link href="http://www.baidu.com"
                                 target="_blank"
                                 type="info"
                                 :underline="false">想看更多，点我前往博客专区<i class="el-icon-right"></i></el-link>
                    </el-col>
                    <!--右侧-->
                    <el-col :span="5">
                        <!--分类-->
                        <el-card class="box-card">
                            <span style="font-size: 15px">分类</span>
                            <el-divider></el-divider>
                            <el-menu class="el-menu-vertical-demo">
                                <el-menu-item
                                        index=0
                                        class="articleTypeMenuItem"
                                        @click="initArticle()">
                                    <span>全部</span>
                                    <el-tag size="mini" style="margin: 5px 0px">{{articleNum}}</el-tag>
                                </el-menu-item>
                                <el-menu-item
                                        :index="index+1+''"
                                        class="articleTypeMenuItem"
                                        v-for="(item,index) in articleTypes"
                                        :key="index"
                                        @click="initArticle(item.iarticleType)">
                                    <span>{{item.typeName}}</span>
                                    <el-tag size="mini" style="margin: 5px 0px">{{item.articleNum}}</el-tag>
                                </el-menu-item>
                            </el-menu>
                        </el-card>
                        <!--最近访客--功能待开发-->
                    </el-col>
                </el-row>
            </el-main>
            <!--尾部-->
            <el-footer
                    class="cardBackground"
                    style="height: 40px;text-align: center;text-align: inherit">
                <span style="font-size: 14px;line-height:40px">©2020程序员小黄 备案号:000000</span>
            </el-footer>
        </el-container>
    </div>
</template>

<script>

    export default {
        name: 'Home',
        data() {
            return {
                loading: false,
                articleTypes: [],
                friendLinks: [],
                articleNum: 0,
                articles: [],
                hotArticles: []
            };
        },
        mounted() {
            this.initArticleType();
            this.initFriendLink();
            this.initHomeArticleNum();
            this.initArticle();
            this.initHotArticle();
        },
        methods: {
            //初始化文章总数
            initHomeArticleNum() {
                this.getRequest("/article/getHomeArticleNum").then(resp => {
                    if (resp) {
                        this.articleNum = resp.obj;
                    }
                })
            },
            //初始化首页文章10篇
            initArticle(iarticleType) {
                this.loading = true;
                let url = "/article/getHomeArticle";
                if (iarticleType) {
                    url += "?iarticleType=" + iarticleType;
                }
                this.getRequest(url).then(resp => {
                    this.loading = false;
                    if (resp) {
                        this.articles = resp.obj;
                    }
                })
            },
            //初始化首页热门文章10篇
            initHotArticle(iarticleType) {
                this.getRequest("/article/getHotArticle").then(resp => {
                    if (resp) {
                        this.hotArticles = resp.obj;
                    }
                })
            },
            //初始化文章类型
            initArticleType() {
                this.getRequest("/articleType/").then(resp => {
                    if (resp) {
                        this.articleTypes = resp.obj;
                    }
                })
            },
            //初始化友情链接
            initFriendLink() {
                this.getRequest("/friendLink/").then(resp => {
                    if (resp) {
                        this.friendLinks = resp.obj;
                    }
                })
            },
            //友链跳转
            openFriendLink(url) {
                window.open(url, "_blank");
            },
        },
    }
</script>

<style scoped>
    .cardBackground {
        background-color: white;
    }

    /*菜单栏取消边框*/
    .el-menu {
        border-right: 0px;
    }

    /*减少卡片中横线的上下间距*/
    .el-divider--horizontal {
        margin: 10px auto;
    }

    /*友情链接的菜单*/
    .friendLinkMenuItem {
        height: 30px;
        line-height: 30px;
        text-align: left
    }

    /*文章类型的菜单*/
    .articleTypeMenuItem {
        height: 30px;
        line-height: 30px;
        display: flex;
        justify-content: space-between
    }

</style>
