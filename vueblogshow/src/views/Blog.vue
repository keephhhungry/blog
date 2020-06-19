<template>
    <div>
        <!--返回上方-->
        <div style="width: 100%;height: 100%;">
            <el-backtop :bottom="60"></el-backtop>
        </div>
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
                        <!--热门文章-->
                        <el-card class="box-card">
                            <span style="font-size: 15px">热门文章</span>
                            <el-divider></el-divider>
                            <el-menu
                                    class="el-menu-vertical-demo">
                                <el-menu-item :index="index+''"
                                              style="height: 30px;line-height:30px;text-align: left"
                                              v-for="(item,index) in hotArticles"
                                              :key="index"
                                              @click="forwardToBlog(item.iarticle)">
                                    <span>{{item.articleTitle}}</span>
                                </el-menu-item>
                            </el-menu>
                        </el-card>
                        <!--分类-->
                        <el-card class="box-card" style="margin-top: 20px">
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
                    </el-col>
                    <!--博客-->
                    <el-col :span="19" style="text-align: left">
                        <el-card class="box-card" style="margin-bottom: 20px"
                                 v-for="(item,index) in articles"
                                 :key="index">
                            <div>
                                <span style="color: darkgrey;font-size: 10px">{{item.gmtCreate}}   {{item.articleType.typeName}}</span>
                            </div>
                            <div style="margin-top: 5px">
                                <el-link
                                        type="info"
                                        @click="forwardToBlog(item.iarticle)"
                                        :underline="false"
                                        style="font-size: 30px">{{item.articleTitle}}
                                </el-link>
                            </div>
                            <div style="margin-top: 5px">
                                <span>{{item.articleIntroduction}}</span>
                            </div>
                            <div style="text-align: right">
                                <span style="margin-right: 10px"><i class="el-icon-star-off"></i>{{item.lookNum}}</span>
                                <span style="margin-right: 10px"><i
                                        class="el-icon-magic-stick"></i>{{item.likeNum}}</span>
                                <span style="margin-right: 30px"><i
                                        class="el-icon-s-comment"></i>{{item.commentNum}}</span>
                            </div>
                        </el-card>
                    </el-col>
                </el-row>
            </el-main>
        </el-container>
    </div>
</template>

<script>
    export default {
        name: "Blog",
        data() {
            return {
                loading: false,
                articleTypes: [],
                friendLinks: [],
                articleNum: 0,
                articles: [],
                hotArticles: [],
                article: null,
            };
        },
        route: {
            canReuse: false,
        },
        mounted() {
            this.initArticle();
            this.initHotArticle();
            this.initArticleType();
            this.initHomeArticleNum();
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
            //初始化文章10篇
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
            //转发到博客页
            forwardToBlog(iarticle) {
                if (iarticle) {
                    this.$router.push({
                        path: `/views/BlogDetail/${iarticle}`,
                    });
                }
            },
        }
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
