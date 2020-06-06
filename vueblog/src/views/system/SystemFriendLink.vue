<template>
    <div>
        <!-- 首行功能按钮-->
        <div style="display: flex;justify-content: space-between">
            <div>
                <el-input
                        placeholder="请输入友链名称。。。"
                        prefix-icon="el-icon-search"
                        size="small"
                        style="width: 400px;margin-right: 10px"
                        v-model="keyword"
                        @keydown.enter.native="initFriendLinks"
                        :clearable="true">
                </el-input>
                <el-button size="small"
                           type="primary"
                           icon="el-icon-search"
                           @click="initFriendLinks">搜索
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
                           @click="showAddFriendLinkDialog">添加友链
                </el-button>
            </div>
        </div>
        <!-- 表格及分页-->
        <div style="margin-top: 10px">
            <el-table
                    :data="friendLinks"
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
                        prop="linkName"
                        label="名称"
                        align="center"
                        width="180">
                </el-table-column>
                <el-table-column
                        prop="linkUrl"
                        label="地址"
                        align="center"
                        width="180">
                </el-table-column>
                <el-table-column
                        prop="remark"
                        align="center"
                        label="备注">
                </el-table-column>
                <el-table-column
                        prop="gmtCreate"
                        align="center"
                        label="创建时间">
                </el-table-column>
                <el-table-column
                        label="操作"
                        align="center"
                        width="250">
                    <template slot-scope="scope">
                        <el-button
                                size="mini"
                                type="success"
                                @click="showUpdateFriendLinkDialog(scope.row)">编辑
                        </el-button>
                        <el-button
                                size="mini"
                                type="danger"
                                @click="deleteFriendLink(scope.row)">删除
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
        <!-- 弹窗-->
        <el-dialog
                :title="dialogTitle"
                :visible.sync="dialogVisible"
                width="330px">
            <div>
                <el-form :model="friendLink" :rules="rules" ref="friendLinkForm" style="text-align: center">
                    <el-form-item label="名称:" prop="linkName">
                        <el-input size="small" style="width: 180px" prefix-icon="el-icon-edit"
                                  v-model="friendLink.linkName"
                                  placeholder="请输入友链名称"></el-input>
                    </el-form-item>
                    <el-form-item label="地址:" prop="linkUrl">
                        <el-input size="small" style="width: 180px" prefix-icon="el-icon-edit"
                                  v-model="friendLink.linkUrl"
                                  placeholder="请输入友链地址"></el-input>
                    </el-form-item>
                    <el-form-item label="备注:" prop="remark">
                        <el-input
                                size="small"
                                type="textarea"
                                :rows="3"
                                style="width: 230px"
                                placeholder="备注。。"
                                v-model="friendLink.remark">
                        </el-input>
                    </el-form-item>
                </el-form>
            </div>
            <span slot="footer" class="dialog-footer">
                <el-button size="small" @click="dialogVisible=false">取 消</el-button>
                <el-button size="small" type="primary" @click="updateFriendLink">确 定</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script>
    export default {
        name: "FriendLink",
        data() {
            return {
                loading: false,
                dialogTitle: '',
                dialogVisible: false,
                friendLinks: [],
                friendLink: {
                    linkName: '',
                    linkUrl: '',
                    remark: '',
                    gmtCreate: ''
                },
                keyword: '',
                rules: {
                    linkName: [{required: true, message: '请输入友链名称', trigger: 'blur'}],
                    linkUrl: [{required: true, message: '请输入友链地址', trigger: 'blur'}],
                },
                page: 1,
                size: 10,
                total: 0,
            }
        },
        mounted() {
            this.initFriendLinks();
        },
        methods: {
            //更新 /新增 友链信息
            updateFriendLink() {
                // 更新友链数据
                if (this.friendLink.ifriendLink) {
                    this.$refs['friendLinkForm'].validate(valid => {
                        if (valid) {
                            this.putRequest("/system/friendlink/", this.friendLink).then(resp => {
                                if (resp) {
                                    this.dialogVisible = false;
                                    this.initFriendLinks();
                                }
                            })
                        }
                    });
                } else {
                    // 新增友链数据
                    this.$refs['friendLinkForm'].validate(valid => {
                        if (valid) {
                            this.postRequest("/system/friendlink/", this.friendLink).then(resp => {
                                if (resp) {
                                    this.dialogVisible = false;
                                    this.initFriendLinks();
                                }
                            })
                        }
                    });
                }
            },
            //删除友链
            deleteFriendLink(data) {
                this.$confirm('此操作将永久删除【' + data.linkName + '】友链, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.deleteRequest("/system/friendlink/" + data.ifriendLink).then(resp => {
                        if (resp) {
                            this.initFriendLinks();
                        }
                    })
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消删除'
                    });
                });
            },
            //展示编辑框
            showUpdateFriendLinkDialog(data) {
                this.dialogTitle = '编辑友链';
                Object.assign(this.friendLink, data);
                this.dialogVisible = true;
            },
            //展示添加框
            showAddFriendLinkDialog() {
                this.dialogTitle = '添加友链';
                this.cleanDialogData();
                this.dialogVisible = true;
            },
            // 获取友链
            initFriendLinks() {
                this.loading = true;
                let url = "/system/friendlink/?page=" + this.page + "&size=" + this.size + "&keyword=" + this.keyword;
                this.getRequest(url).then(resp => {
                    this.loading = false;
                    if (resp) {
                        this.friendLinks = resp.data;
                        this.total = resp.total;
                    }
                })
            },
            //改变页数
            currentChange(currentPage) {
                this.page = currentPage;
                this.initFriendLinks('advanced');
            },
            //每页数量改变
            sizeChange(currentSize) {
                this.size = currentSize;
                this.initFriendLinks();
            },
            //刷新
            refresh() {
                this.loading = true;
                this.initFriendLinks();
            },
            //清除dialog数据
            cleanDialogData() {
                this.friendLink = {
                    linkName: '',
                    linkUrl: '',
                    remark: '',
                };
                this.dialogVisible = false;
            },
        }
    }
</script>

<style scoped>

</style>
