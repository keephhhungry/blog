<template>
    <div>
        <!-- 首行功能按钮-->
        <div style="display: flex;justify-content: space-between">
            <div>
                <el-input
                        placeholder="请输入菜单名称"
                        prefix-icon="el-icon-search"
                        size="small"
                        style="width: 400px;margin-right: 10px"
                        v-model="keyword"
                        @keydown.enter.native="initMenus"
                        :clearable="true" >
                </el-input>
                <el-button size="small"
                           type="primary"
                           icon="el-icon-search"
                           @click="initMenus">搜索
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
                           @click="showAddMenuDialog">添加角色
                </el-button>
            </div>
        </div>
        <!-- 表格-->
        <div style="margin-top: 10px">
            <el-table
                    :data="menus"
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
                        prop="url"
                        label="路径规则"
                        align="center"
                        width="180">
                </el-table-column>
                <el-table-column
                        prop="path"
                        label="根路径"
                        align="center"
                        width="180">
                </el-table-column>
                <el-table-column
                        prop="component"
                        align="center"
                        label="组件名称">
                </el-table-column>
                <el-table-column
                        prop="name"
                        align="center"
                        label="权限名称">
                </el-table-column>
                <el-table-column
                        prop="icon"
                        align="center"
                        label="图标 ">
                </el-table-column>
                <el-table-column
                        align="center"
                        label="状态">
                    <template slot-scope="scope">
                        <el-tag size="mini" type="success" v-if="scope.row.enabled">启用</el-tag>
                        <el-tag size="mini" type="danger" v-else>禁用</el-tag>
                    </template>
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
                                @click="showUpdateMenuDialog(scope.row)">编辑
                        </el-button>
                        <el-button
                                size="mini"
                                type="danger"
                                @click="deleteMenu(scope.row)">删除
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
                <el-form :model="menu" :rules="rules" ref="menuForm" style="text-align: center">
                    <el-form-item label="匹配规则:" prop="url">
                        <el-input size="small" style="width: 180px" prefix-icon="el-icon-edit"
                                  v-model="menu.url"
                                  placeholder="请输入账号"></el-input>
                    </el-form-item>
                    <el-form-item label="根路径:" prop="path">
                        <el-input size="small" style="width: 180px" prefix-icon="el-icon-edit"
                                  v-model="menu.path"
                                  placeholder="请输入账号"></el-input>
                    </el-form-item>
                </el-form>
            </div>
            <span slot="footer" class="dialog-footer">
                <el-button size="small" @click="dialogVisible=false">取 消</el-button>
                <el-button size="small" type="primary" @click="updateMenu">确 定</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script>
    export default {
        name: "Menu",
        data(){
            return{
                loading: false,
                dialogTitle: '',
                dialogVisible: false,
                menus:[],
                menu:{
                    url:'',
                    path:'',
                    component:'',
                    name:'',
                    icon:'',
                    meta:'',
                    parentId:'',
                    isEnable:'',
                    gmtCreate:'',
                },
                keyword: '',
                rules: {
                    name: [{required: true, message: '请输入角色英文名', trigger: 'blur'}],
                    nameZh: [{required: true, message: '请输入角色中文名', trigger: 'blur'}],
                },
            }
        },
        mounted() {
            this.initMenus();
        },
        methods: {
            //更新 /新增 角色信息
            updateMenu() {
                // 更新角色数据
                if (this.menu.imenu) {
                    this.$refs['menuForm'].validate(valid => {
                        if (valid) {
                            this.putRequest("/permission/menu/", this.menu).then(resp => {
                                if (resp) {
                                    this.dialogVisible = false;
                                    this.initMenus();
                                }
                            })
                        }
                    });
                } else {
                    // 新增角色数据
                    this.$refs['menuForm'].validate(valid => {
                        if (valid) {
                            this.postRequest("/permission/menu/", this.menu).then(resp => {
                                if (resp) {
                                    this.dialogVisible = false;
                                    this.initMenus();
                                }
                            })
                        }
                    });
                }
            },
            //删除角色
            deleteMenu(data) {
                this.$confirm('此操作将永久删除【' + data.nameZh + '】角色, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.deleteRequest("/permission/menu/" + data.imenu).then(resp => {
                        if (resp) {
                            this.initMenus();
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
            showUpdateMenuDialog(data) {
                this.dialogTitle = '编辑角色';
                Object.assign(this.role, data);
                this.dialogVisible = true;
            },
            //展示添加框
            showAddMenuDialog() {
                this.dialogTitle = '添加角色';
                this.cleanDialogData();
                this.dialogVisible = true;
            },
            // 获取角色
            initMenus() {
                this.loading = true;
                this.getRequest("/permission/menu/?keyword=" + this.keyword).then(resp => {
                    this.loading = false;
                    if (resp) {
                        this. menus = resp.obj;
                    }
                })
            },
            //刷新
            refresh() {
                this.loading = true;
                this.initMenus();
            },
            //清除dialog数据
            cleanDialogData() {
                this.menu = {
                    url:'',
                    path:'',
                    component:'',
                    name:'',
                    icon:'',
                    meta:'',
                    parentId:'',
                    isEnable:'',
                    gmtCreate:'',
                };
                this.dialogVisible = false;
            },
        }
    }
</script>

<style scoped>

</style>
