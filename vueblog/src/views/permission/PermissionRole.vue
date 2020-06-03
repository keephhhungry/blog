<template>
    <div>
        <!-- 首行功能按钮-->
        <div style="display: flex;justify-content: space-between">
            <div>
                <el-input
                        placeholder="请输入角色中文名。。。"
                        prefix-icon="el-icon-search"
                        size="small"
                        style="width: 400px;margin-right: 10px"
                        v-model="keyword"
                        @keydown.enter.native="initRoles"
                        :clearable="true" >
                </el-input>
                <el-button size="small"
                           type="primary"
                           icon="el-icon-search"
                           @click="initRoles">搜索
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
                           @click="showAddRoleDialog">添加角色
                </el-button>
            </div>
        </div>
        <!-- 表格-->
        <div style="margin-top: 10px">
            <el-table
                    :data="roles"
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
                        prop="name"
                        label="角色(英)"
                        align="center"
                        width="180">
                </el-table-column>
                <el-table-column
                        prop="nameZh"
                        label="角色(中)"
                        align="center"
                        width="180">
                </el-table-column>
                <el-table-column
                        prop="description"
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
                                @click="showUpdateRoleDialog(scope.row)">编辑
                        </el-button>
                        <el-button
                                size="mini"
                                type="danger"
                                @click="deleteRole(scope.row)">删除
                        </el-button>
                    </template>
                </el-table-column>
            </el-table>
        </div>
        <!-- 弹窗-->
        <el-dialog
                :title="dialogTitle"
                :visible.sync="dialogVisible"
                width="330px">
            <div>
                <el-form :model="role" :rules="rules" ref="roleForm" style="text-align: center">
                    <el-form-item label="角色名(英):" prop="name">
                        <el-input size="small" style="width: 180px" prefix-icon="el-icon-edit"
                                  v-model="role.name"
                                  placeholder="请输入账号"></el-input>
                    </el-form-item>
                    <el-form-item label="角色名(中):" prop="nameZh">
                        <el-input size="small" style="width: 180px" prefix-icon="el-icon-edit"
                                  v-model="role.nameZh"
                                  placeholder="请输入账号"></el-input>
                    </el-form-item>
                    <el-form-item label="备注:" prop="description">
                        <el-input
                                size="small"
                                type="textarea"
                                :rows="3"
                                style="width: 230px"
                                placeholder="备注。。"
                                v-model="role.description">
                        </el-input>
                    </el-form-item>
                </el-form>
            </div>
            <span slot="footer" class="dialog-footer">
                <el-button size="small" @click="dialogVisible=false">取 消</el-button>
                <el-button size="small" type="primary" @click="updateRole">确 定</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script>
    export default {
        name: "Role",
        data() {
            return {
                loading: false,
                dialogTitle: '',
                dialogVisible: false,
                roles: [],
                menus: [],
                role: {
                    irole: '',
                    name: '',
                    nameZh: '',
                    description: '',
                    gmtCreate: ''
                },
                keyword: '',
                rules: {
                    name: [{required: true, message: '请输入角色英文名', trigger: 'blur'}],
                    nameZh: [{required: true, message: '请输入角色中文名', trigger: 'blur'}],
                },
            }
        },
        mounted() {
            this.initRoles();
            this.initMenus();
        },
        methods: {
            //更新 /新增 角色信息
            updateRole() {
                // 更新角色数据
                if (this.role.irole) {
                    this.$refs['roleForm'].validate(valid => {
                        if (valid) {
                            this.putRequest("/permission/role/", this.role).then(resp => {
                                if (resp) {
                                    this.dialogVisible = false;
                                    this.initRoles();
                                }
                            })
                        }
                    });
                } else {
                    // 新增角色数据
                    this.$refs['roleForm'].validate(valid => {
                        if (valid) {
                            this.postRequest("/permission/role/", this.role).then(resp => {
                                if (resp) {
                                    this.dialogVisible = false;
                                    this.initRoles();
                                }
                            })
                        }
                    });
                }
            },
            //删除角色
            deleteRole(data) {
                this.$confirm('此操作将永久删除【' + data.nameZh + '】角色, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.deleteRequest("/permission/role/" + data.irole).then(resp => {
                        if (resp) {
                            this.initRoles();
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
            showUpdateRoleDialog(data) {
                this.dialogTitle = '编辑角色';
                Object.assign(this.role, data);
                this.dialogVisible = true;
            },
            //展示添加框
            showAddRoleDialog() {
                this.dialogTitle = '添加角色';
                this.cleanDialogData();
                this.dialogVisible = true;
            },
            // 获取角色
            initRoles() {
                this.loading = true;
                this.getRequest("/permission/role/?keyword=" + this.keyword).then(resp => {
                    this.loading = false;
                    if (resp) {
                        this.roles = resp.obj;
                    }
                })
            },
            // 获取菜单
            initMenus() {
                this.getRequest("/permission/role/menu").then(resp => {
                    if (resp) {
                        this.menus = resp.obj;
                    }
                })
            },
            //刷新
            refresh() {
                this.loading = true;
                this.initRoles();
            },
            //清除dialog数据
            cleanDialogData() {
                this.role = {
                    irole: '',
                    name: '',
                    nameZh: '',
                    description: '',
                    gmtCreate: ''
                };
                this.dialogVisible = false;
            },
        }
    }
</script>

<style scoped>

</style>
