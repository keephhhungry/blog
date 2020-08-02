<template>
    <div>
        <!-- 首行功能按钮-->
        <div style="display: flex;justify-content: space-between">
            <div>
                <el-input
                        placeholder="请输入文章类型名称。。。"
                        prefix-icon="el-icon-search"
                        size="small"
                        style="width: 400px;margin-right: 10px"
                        v-model="keyword"
                        @keydown.enter.native="initArticles"
                        :disabled="showAdvanceSearchView"
                        :clearable="true">
                </el-input>
                <el-button size="small"
                           type="primary"
                           icon="el-icon-search"
                           :disabled="showAdvanceSearchView"
                           @click="initArticles">搜索
                </el-button>
                <el-button size="small"
                           type="primary"
                           :icon="showAdvanceSearchView?'el-icon-arrow-up':'el-icon-arrow-down'"
                           @click="showAdvanceSearchView = !showAdvanceSearchView">高级搜索
                </el-button>
            </div>
            <div>
                <el-button size="small"
                           type="success"
                           icon="el-icon-refresh-right"
                           @click="refresh">刷新
                </el-button>
                <el-button size="small"
                           type="primary"
                           icon="el-icon-circle-plus-outline"
                           @click="addArticle">添加文章
                </el-button>
            </div>
        </div>
        <!-- 高级搜索框-->
        <transition name="slide-fade">
            <div v-show="showAdvanceSearchView"
                 style="border: 1px solid #409eff;border-radius: 5px;box-sizing: border-box;padding: 5px;margin: 10px 0px;">
                <el-row>
                    <el-col :span="6">
                        <span class="showAdvanceSearchViewSpan">文章类型:</span>
                        <el-select v-model="searchValue.articleType"
                                   placeholder="文章类型"
                                   size="mini"
                                   :clearable="true"
                                   style="width: 200px;">
                            <el-option
                                    v-for="item in articleTypes"
                                    :key="item.iarticleType"
                                    :label="item.typeName"
                                    :value="item.iarticleType">
                            </el-option>
                        </el-select>
                    </el-col>
                    <el-col :span="6" style="margin-left: 20px">
                        <span class="showAdvanceSearchViewSpan">原创:</span>
                        <el-select v-model="searchValue.isOriginal"
                                   placeholder="是否原创"
                                   size="mini"
                                   :clearable="true"
                                   style="width: 200px;">
                            <el-option
                                    v-for="item in isReptileList"
                                    :key="item.value"
                                    :label="item.label"
                                    :value="item.value">
                            </el-option>
                        </el-select>
                    </el-col>
                    <el-col :span="6">
                        <span class="showAdvanceSearchViewSpan">爬虫:</span>
                        <el-select v-model="searchValue.isReptile"
                                   placeholder="是否爬虫"
                                   size="mini"
                                   :clearable="true"
                                   style="width: 200px;">
                            <el-option
                                    v-for="item in isOriginalList"
                                    :key="item.value"
                                    :label="item.label"
                                    :value="item.value">
                            </el-option>
                        </el-select>
                    </el-col>
                    <el-col :span="1">
                        <el-button size="mini" icon="el-icon-search" type="primary" @click="initArticles('advanced')">搜索
                        </el-button>
                    </el-col>
                </el-row>
                <el-row style="margin-top: 10px">
                    <el-col :span="9">
                        <span class="showAdvanceSearchViewSpan">创建时间:</span>
                        <el-date-picker
                                v-model="searchValue.createDateScope"
                                type="daterange"
                                size="mini"
                                :clearable="true"
                                value-format="yyyy-MM-dd HH:mm:ss"
                                range-separator="至"
                                start-placeholder="开始日期"
                                end-placeholder="结束日期">
                        </el-date-picker>
                    </el-col>
                </el-row>
            </div>
        </transition>
        <!-- 表格及分页-->
        <div style="margin-top: 10px">
            <el-table
                    :data="articles"
                    border
                    stripe
                    size="small"
                    align="center"
                    v-loading="loading"
                    element-loading-text="正在加载"
                    element-loading-spinner="el-icon-loading"
                    element-loading-background="rgba(0,0,0,0.1)"
                    style="width: 100%">
                <el-table-column
                        prop="articleAuthor"
                        label="作者"
                        align="center"
                        width="100">
                </el-table-column>
                <el-table-column
                        prop="articleTitle"
                        label="标题"
                        align="center"
                        width="180">
                </el-table-column>
                <el-table-column
                        prop="articleIntroduction"
                        label="简介"
                        align="center">
                </el-table-column>
                <el-table-column
                        prop="wordCount"
                        label="字数"
                        align="center"
                        width="65px">
                </el-table-column>
                <el-table-column
                        prop="articleType.typeName"
                        label="分类"
                        align="center"
                        width="100">
                </el-table-column>
                <el-table-column
                        prop="isTop"
                        label="置顶"
                        align="center"
                        width="65px">
                    <template slot-scope="scope">
                        <el-tag size="mini" type="success" v-if="scope.row.isTop">是</el-tag>
                        <el-tag size="mini" type="danger" v-else>否</el-tag>
                    </template>
                </el-table-column>
                <el-table-column
                        prop="isOriginal"
                        label="原创"
                        align="center"
                        width="65px">
                    <template slot-scope="scope">
                        <el-tag size="mini" type="success" v-if="scope.row.isOriginal ">是</el-tag>
                        <el-tag size="mini" type="danger" v-else>否</el-tag>
                    </template>
                </el-table-column>
                <el-table-column
                        prop="isReptile"
                        label="爬虫"
                        align="center"
                        width="65px">
                    <template slot-scope="scope">
                        <el-tag size="mini" type="success" v-if="scope.row.isReptile">是</el-tag>
                        <el-tag size="mini" type="danger" v-else>否</el-tag>
                    </template>
                </el-table-column>
                <el-table-column
                        prop="lookNum"
                        label="查看数"
                        align="center"
                        width="65px">
                </el-table-column>
                <el-table-column
                        prop="commentNum"
                        label="评论数"
                        align="center"
                        width="65px">
                </el-table-column>
                <el-table-column
                        prop="likeNum"
                        label="点赞数"
                        align="center"
                        width="65px">
                </el-table-column>
                <el-table-column
                        prop="gmtCreate"
                        align="center"
                        label="创建时间"
                        width="150px">
                </el-table-column>
                <el-table-column
                        label="操作"
                        align="center"
                        fixed="right"
                        width="220">
                    <template slot-scope="scope">
                        <el-button
                                size="mini"
                                type="success"
                                icon="el-icon-top"
                                class="tableButton"
                                @click="topArticle(scope.row)">置顶
                        </el-button>
                        <el-button
                                size="mini"
                                type="success"
                                icon="el-icon-edit"
                                class="tableButton"
                                @click="editArticle(scope.row)">编辑
                        </el-button>
                        <el-button
                                size="mini"
                                type="danger"
                                icon="el-icon-delete"
                                class="tableButton"
                                @click="deleteArticle(scope.row)">删除
                        </el-button>
                    </template>
                </el-table-column>
            </el-table>
            <!--分页-->
            <div style="display: flex;justify-content: flex-end;margin-top: 10px">
                <el-pagination
                        background
                        @current-change="currentChange"
                        @size-change="sizeChange"
                        layout="sizes, prev, pager, next, jumper, ->, total, slot"
                        :total="total">
                </el-pagination>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        name: "Article",
        data() {
            return {
                loading: false,
                showAdvanceSearchView: false,
                searchValue: {
                    isOriginal: null,
                    isReptile: null,
                    articleType: null,
                    createDateScope: null
                },
                articles: [],
                isOriginalList: [{
                    label: "是",
                    value: 1
                }, {
                    label: "否",
                    value: 0
                }],
                isReptileList: [{
                    label: "是",
                    value: 1
                }, {
                    label: "否",
                    value: 0
                }],
                articleTypes: [],
                keyword: '',
                page: 1,
                size: 10,
                total: 0,
            }
        },
        mounted() {
            this.initArticles();
            this.initArticleTypes();
        },
        methods: {
            //置顶文章
            topArticle(data){
                this.$confirm('此操作将置顶【' + data.articleTitle + '】文章, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    // this.putRequest("/article/article/top/" + data.iarticle).then(resp => {
                    //     if (resp) {
                    //         this.initArticles();
                    //     }
                    // })
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消置顶'
                    });
                });
            },
            //编辑文章
            editArticle(data){
                this.$store.state.article = data;
                this.addArticle();
            },
            //删除文章
            deleteArticle(data) {
                this.$confirm('此操作将永久删除【' + data.articleTitle + '】文章, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.deleteRequest("/article/article/" + data.iarticle).then(resp => {
                        if (resp) {
                            this.initArticles();
                        }
                    })
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消删除'
                    });
                });
            },
            // 获取文章
            initArticles(type) {
                this.loading = true;
                let url = '/article/article/?page=' + this.page + '&size=' + this.size;
                if (type && type == 'advanced') {
                    if (this.searchValue.articleType) {
                        url += '&iarticleType=' + this.searchValue.articleType;
                    }
                    if (this.searchValue.isOriginal) {
                        url += '&is_original=' + this.searchValue.isOriginal;
                    }
                    if (this.searchValue.isReptile) {
                        url += '&is_reptile=' + this.searchValue.isReptile;
                    }
                    if (this.searchValue.createDateScope) {
                        url += '&createDateScope=' + this.searchValue.createDateScope;
                    }
                } else {
                    url += "&articleTitle=" + this.keyword;
                }
                this.getRequest(url).then(resp => {
                    this.loading = false;
                    if (resp) {
                        this.articles = resp.data;
                        this.total = resp.total;
                    }
                })
            },
            //获取文章类型
            initArticleTypes() {
                let url = "/article/article/articleType";
                this.getRequest(url).then(resp => {
                    if (resp) {
                        this.articleTypes = resp.data;
                    }
                })
            },
            //添加文章 跳转路由
            addArticle(){
                this.$router.push('/views/article/artilceEdit');
            },
            //改变页数
            currentChange(currentPage) {
                this.page = currentPage;
                this.initArticles('advanced');
            },
            //每页数量改变
            sizeChange(currentSize) {
                this.size = currentSize;
                this.initArticles();
            },
            //刷新
            refresh() {
                this.loading = true;
                this.initArticles();
            },
        }
    }
</script>

<style>
    /* 可以设置不同的进入和离开动画 */
    /* 设置持续时间和动画函数 */
    .slide-fade-enter-active {
        transition: all .4s ease;
    }

    .slide-fade-leave-active {
        transition: all .4s cubic-bezier(1.0, 0.5, 0.8, 1.0);
    }

    .slide-fade-enter, .slide-fade-leave-to
        /* .slide-fade-leave-active for below version 2.1.8 */
    {
        transform: translateX(10px);
        opacity: 0;
    }

    .showAdvanceSearchViewSpan {
        margin-right: 6px;
        font-size: 13px
    }
    .tableButton{
        padding:5px 7px
    }
</style>
