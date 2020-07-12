<template>
    <div>
        <!-- 首行功能按钮-->
        <div>
            <div>
                <el-input
                        placeholder="请输入用户名称。。。"
                        prefix-icon="el-icon-search"
                        size="small"
                        style="width: 250px;margin-right: 15px"
                        v-model="searchValue.keyword"
                        @keydown.enter.native="initLeaveMessages"
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
                           @click="initLeaveMessages">搜索
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
                    :data="leaveMessages"
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
                        prop="messageContext"
                        label="留言内容"
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
                        prop="province"
                        align="center"
                        label="省份">
                </el-table-column>
                <el-table-column
                        prop="city"
                        align="center"
                        label="城市">
                </el-table-column>
                <el-table-column
                        prop="gmtCreate"
                        align="center"
                        label="创建时间"
                        width="150">
                </el-table-column>
                <el-table-column
                        label="操作"
                        align="center"
                        fixed="right"
                        width="80">
                    <template slot-scope="scope">
                        <el-button
                                size="mini"
                                type="danger"
                                icon="el-icon-delete"
                                class="tableButton"
                                @click="deleteLeaveMessage(scope.row)">删除
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
        name: "LeaveMessage",
        data() {
            return {
                loading: false,
                leaveMessages: [],
                searchValue: {
                    keyword: null,
                    createDateScope: null,
                },
                page: 1,
                size: 10,
                total: 0,
            }
        },
        mounted() {
            this.initLeaveMessages();
        },
        methods: {
            // 获取留言
            initLeaveMessages() {
                this.loading = true;
                let url = '/system/leavemessage/?page=' + this.page + '&size=' + this.size;
                if (this.searchValue.keyword) {
                    url += '&user.username=' + this.searchValue.keyword;
                }
                if (this.searchValue.createDateScope) {
                    url += '&createDateScope=' + this.searchValue.createDateScope;
                }
                this.getRequest(url).then(resp => {
                    this.loading = false;
                    if (resp) {
                        this.leaveMessages = resp.data;
                        this.total = resp.total;
                    }
                })
            },
            //删除留言
            deleteLeaveMessage(data) {
                this.$confirm('此操作将永久删除【' + data.user.username + '】这条留言, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.deleteRequest("/system/leavemessage/" + data.ileaveMessage).then(resp => {
                        if (resp && resp.obj == -1) {
                            this.$confirm('删除失败，该留言下有回复，是否强制删除该留言及相关回复?', '提示', {
                                confirmButtonText: '确定',
                                cancelButtonText: '取消',
                                type: 'warning'
                            }).then(() => {
                                this.deleteRequest("/system/leavemessage/enforce/" + data.ileaveMessage).then(resp => {
                                    if (resp) {
                                        this.initLeaveMessages();
                                    }
                                })
                            }).catch(() => {
                                this.$message({
                                    type: 'info',
                                    message: '已取消删除'
                                });
                            });
                        }else{
                            this.initLeaveMessages();
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
                this.initLeaveMessages();
            },
            //每页数量改变
            sizeChange(currentSize) {
                this.size = currentSize;
                this.initLeaveMessages();
            },
            //刷新
            refresh() {
                this.loading = true;
                this.initLeaveMessages();
            },
        }
    }
</script>

<style scoped>
    .tableButton{
        padding:5px 7px
    }
</style>
