<template>
    <div><!-- 首行功能按钮-->
        <div>
            <div>
                <el-input
                        placeholder="请输入用户名称。。。"
                        prefix-icon="el-icon-search"
                        size="small"
                        style="width: 200px;margin-right: 15px"
                        v-model="searchValue.username"
                        @keydown.enter.native="initArticleComments"
                        :clearable="true">
                </el-input>
                <el-input
                        placeholder="请输入文章名称。。。"
                        prefix-icon="el-icon-search"
                        size="small"
                        style="width: 200px;margin-right: 15px"
                        v-model="searchValue.articleTitle"
                        @keydown.enter.native="initArticleComments"
                        :clearable="true">
                </el-input>
                <span style="margin-right: 10px">创建时间:</span>
                <el-date-picker
                        type="datetimerange"
                        size="small"
                        v-model="searchValue.createDateScope"
                        style="margin-right: 10px"
                        value-format="yyyy-MM-dd hh:mm:ss"
                        :clearable="true"
                        range-separator="至"
                        start-placeholder="开始日期"
                        end-placeholder="结束日期">
                </el-date-picker>
                <el-button size="small"
                           type="primary"
                           icon="el-icon-search"
                           @click="initArticleComments">搜索
                </el-button>
                <el-button size="small"
                           type="success"
                           icon="el-icon-refresh-right"
                           @click="refresh">刷新
                </el-button>
            </div>
        </div>
        <!-- 表格及分页-->
        <div style="margin-top: 10px">
            <el-table
                    :data="articleComments"
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
                        prop="user.username"
                        label="操作用户"
                        align="center"
                        width="100">
                </el-table-column>
                <el-table-column
                        prop="article.articleTitle"
                        label="文章名称"
                        align="center"
                        width="100">
                </el-table-column>
                <el-table-column
                        prop="commentContext"
                        label="评论内容"
                        align="center"
                        width="180">
                </el-table-column>
                <el-table-column
                        prop="operatingSystem"
                        align="center"
                        label="操作系统">
                </el-table-column>
                <el-table-column
                        prop="browserName"
                        align="center"
                        label="浏览器名字">
                </el-table-column>
                <el-table-column
                        prop="browserVersion"
                        align="center"
                        label="浏览器版本">
                </el-table-column>
                <el-table-column
                        prop="ip"
                        align="center"
                        label="ip">
                </el-table-column>
                <el-table-column
                        prop="address"
                        align="center"
                        label="地址">
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
                        width="80">
                    <template slot-scope="scope">
                        <el-button
                                size="mini"
                                type="danger"
                                icon="el-icon-delete"
                                class="tableButton"
                                @click="deleteArticleComment(scope.row)">删除
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
        name: "Comment",
        data() {
            return {
                loading: false,
                articleComments: [],
                searchValue: {
                    username: null,
                    articleTitle: null,
                    createDateScope: null,
                },
                page: 1,
                size: 10,
                total: 0,
            }
        },
        mounted() {
            this.initArticleComments();
        },
        methods: {
            // 获取评论
            initArticleComments() {
                this.loading = true;
                let url = '/article/comment/?page=' + this.page + '&size=' + this.size;
                if (this.searchValue.username) {
                    url += '&user.username=' + this.searchValue.username;
                }
                if (this.searchValue.articleTitle) {
                    url += '&article.articleTitle=' + this.searchValue.articleTitle;
                }
                if (this.searchValue.createDateScope) {
                    url += '&createDateScope=' + this.searchValue.createDateScope;
                }
                this.getRequest(url).then(resp => {
                    this.loading = false;
                    if (resp) {
                        this.articleComments = resp.data;
                        this.total = resp.total;
                    }
                })
            },
            //删除评论
            deleteArticleComment(data) {
                this.$confirm('此操作将永久删除【' + data.user.username + '】这条评论, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.deleteRequest("/article/comment/" + data.iarticleComment).then(resp => {
                        if (resp && resp.obj == -1) {
                            this.$confirm('删除失败，该评论下有回复，是否强制删除该评论及相关回复?', '提示', {
                                confirmButtonText: '确定',
                                cancelButtonText: '取消',
                                type: 'warning'
                            }).then(() => {
                                this.deleteRequest("/article/comment/enforce/" + data.iarticleComment).then(resp => {
                                    if (resp) {
                                        this.initArticleComments();
                                    }
                                })
                            }).catch(() => {
                                this.$message({
                                    type: 'info',
                                    message: '已取消删除'
                                });
                            });
                        } else {
                            this.initArticleComments();
                        }
                    })
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消删除'
                    });
                });
            },
            //改变页数
            currentChange(currentPage) {
                this.page = currentPage;
                this.initArticleComments();
            },
            //每页数量改变
            sizeChange(currentSize) {
                this.size = currentSize;
                this.initArticleComments();
            },
            //刷新
            refresh() {
                this.loading = true;
                this.initArticleComments();
            },
        }
    }
</script>

<style scoped>
    .tableButton {
        padding: 5px 7px
    }
</style>
