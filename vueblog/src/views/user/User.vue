<template>
    <div>
        <!-- 首行功能按钮-->
        <div style="display: flex;justify-content: space-between">
            <div>
                <el-input
                        placeholder="请输入账号"
                        prefix-icon="el-icon-search"
                        size="small"
                        style="width: 400px;margin-right: 10px"
                        v-model="keyword"
                        @keydown.enter.native="initUsers"
                        :clearable="true"
                        :disabled="showAdvanceSearchView">
                </el-input>
                <el-button size="small"
                           type="primary"
                           icon="el-icon-search"
                           @click="initUsers"
                           :disabled="showAdvanceSearchView">搜索
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
                           @click="showAddUserDialog">添加用户
                </el-button>
                <el-button size="small"
                           type="danger"
                           icon="el-icon-delete"
                           :disabled="multipleSelection.length==0"
                           @click="deleteUsers">批量删除
                </el-button>
            </div>
        </div>
        <!-- 高级搜索框-->
        <transition name="slide-fade">
            <div v-show="showAdvanceSearchView"
                 style="border: 1px solid #409eff;border-radius: 5px;box-sizing: border-box;padding: 5px;margin: 10px 0px;">
                <el-row>
                    <el-col :span="6">
                        <span class="showAdvanceSearchViewSpan">姓名:</span>
                        <el-input size="mini"
                                  style="width: 200px"
                                  prefix-icon="el-icon-edit"
                                  v-model="searchValue.name"
                                  :clearable="true"
                                  placeholder="请输入姓名">
                        </el-input>
                    </el-col>
                    <el-col :span="6">
                        <span class="showAdvanceSearchViewSpan">年龄:</span>
                        <el-select v-model="searchValue.age" placeholder="年龄" size="mini"
                                   style="width: 200px;">
                            <el-option
                                    label=""
                                    :value="null">
                            </el-option>
                            <el-option
                                    v-for="index in 120"
                                    :key="index"
                                    :label="index"
                                    :value="index">
                            </el-option>
                        </el-select>
                    </el-col>
                    <el-col :span="6">
                        <span class="showAdvanceSearchViewSpan">性别:</span>
                        <el-select v-model="searchValue.sex" placeholder="性别" size="mini"
                                   style="width: 200px;">
                            <el-option
                                    label=""
                                    :value="null">
                            </el-option>
                            <el-option
                                    v-for="item in sexList"
                                    :key="item.value"
                                    :label="item.label"
                                    :value="item.value">
                            </el-option>
                        </el-select>
                    </el-col>
                    <el-col :span="6">
                        <span class="showAdvanceSearchViewSpan">邮箱:</span>
                        <el-input size="mini" style="width: 200px" prefix-icon="el-icon-edit"
                                  v-model="searchValue.email"
                                  :clearable="true"
                                  placeholder="请输入邮箱"></el-input>
                    </el-col>
                </el-row>
                <el-row style="margin-top: 10px">
                    <el-col :span="6">
                        <span class="showAdvanceSearchViewSpan">手机:</span>
                        <el-input size="mini" style="width: 200px" prefix-icon="el-icon-edit"
                                  v-model="searchValue.telephone"
                                  :clearable="true"
                                  placeholder="请输入手机号"></el-input>
                    </el-col>
                    <el-col :span="6">
                        <span class="showAdvanceSearchViewSpan">状态:</span>
                        <el-select v-model="searchValue.enabled" placeholder="状态" size="mini" disabled="disabled"
                                   style="width: 200px;">
                            <el-option
                                    label=""
                                    :value="null">
                            </el-option>
                            <el-option
                                    v-for="item in enabledList"
                                    :key="item.value"
                                    :label="item.label"
                                    :value="item.value">
                            </el-option>
                        </el-select>
                    </el-col>
                    <el-col :span="9">
                        <span class="showAdvanceSearchViewSpan">创建时间:</span>
                        <el-date-picker
                                v-model="searchValue.createDateScope"
                                type="daterange"
                                size="mini"
                                value-format="yyyy-MM-dd HH:mm:ss"
                                range-separator="至"
                                start-placeholder="开始日期"
                                end-placeholder="结束日期">
                        </el-date-picker>
                    </el-col>
                    <el-col :span="1" style="margin-left: 25px">
                        <el-button size="mini" icon="el-icon-search" type="primary" @click="initUsers('advanced')">搜索
                        </el-button>
                    </el-col>
                </el-row>
            </div>
        </transition>
        <!-- 表格及分页-->
        <div style="margin-top: 10px">
            <!--表格-->
            <el-table
                    :data="users"
                    border
                    stripe
                    size="small"
                    align="center"
                    @selection-change="selectionChange"
                    @row-dblclick="showUpdateUserDialog"
                    v-loading="loading"
                    element-loading-text="正在加载"
                    element-loading-spinner="el-icon-loading"
                    element-loading-background="rgba(0,0,0,0.1)"
                    style="width: 100%">
                <el-table-column
                        type="selection"
                        width="40">
                </el-table-column>
                <el-table-column
                        prop="username"
                        label="账号"
                        align="center"
                        width="100">
                </el-table-column>
                <el-table-column
                        prop="name"
                        label="姓名"
                        align="center"
                        width="100">
                </el-table-column>
                <el-table-column
                        prop="age"
                        label="年龄"
                        align="center"
                        width="50">
                </el-table-column>
                <el-table-column
                        prop="sex"
                        label="性别"
                        align="center"
                        width="50">
                    <template slot-scope="scope">
                        <span v-if="scope.row.sex">男</span>
                        <span v-else>女</span>
                    </template>
                </el-table-column>
                <el-table-column
                        prop="email"
                        label="邮箱"
                        align="center"
                        width="200">
                </el-table-column>
                <el-table-column
                        prop="telephone"
                        label="电话"
                        align="center"
                        width="150">
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
                        label="创建时间"
                        align="center"
                        width="150">
                </el-table-column>
                <el-table-column
                        label="操作"
                        align="center"
                        width="250"
                        fixed="right">
                    <template slot-scope="scope">
                        <el-button
                                size="mini"
                                type="success"
                                icon="el-icon-edit"
                                class="tableButton"
                                @click="showUpdateUserDialog(scope.row)">编辑
                        </el-button>
                        <el-button
                                size="mini"
                                type="primary"
                                icon="el-icon-setting"
                                class="tableButton"
                                @click="resetPassword(scope.row)">重置密码
                        </el-button>
                        <el-button
                                size="mini"
                                type="danger"
                                icon="el-icon-delete"
                                class="tableButton"
                                @click="deleteUser(scope.row)">删除
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
                width="60%">
            <div>
                <el-form :model="user" :rules="rules" ref="userForm">
                    <el-row>
                        <el-col :span="8">
                            <el-form-item label="账号:" prop="username">
                                <el-input size="mini" style="width: 150px" prefix-icon="el-icon-edit"
                                          v-model="user.username"
                                          placeholder="请输入账号"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="8">
                            <el-form-item label="姓名:" prop="name">
                                <el-input size="mini" style="width: 150px" prefix-icon="el-icon-edit"
                                          v-model="user.name"
                                          placeholder="请输入姓名"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="8">
                            <el-form-item label="年龄:" prop="age">
                                <el-select v-model="user.age" placeholder="年龄" size="mini"
                                           style="width: 150px;">
                                    <el-option
                                            lable=""
                                            value="">
                                    </el-option>
                                    <el-option
                                            v-for="index in 120"
                                            :key="index"
                                            :label="index"
                                            :value="index">
                                    </el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :span="8">
                            <el-form-item label="性别:" prop="sex">
                                <el-select v-model="user.sex" placeholder="性别" size="mini"
                                           style="width: 150px;">
                                    <el-option
                                            v-for="item in sexList"
                                            :key="item.value"
                                            :label="item.label"
                                            :value="item.value">
                                    </el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                        <el-col :span="8">
                            <el-form-item label="邮箱:" prop="email">
                                <el-input size="mini" style="width: 150px" prefix-icon="el-icon-edit"
                                          v-model="user.email"
                                          placeholder="请输入邮箱"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="8">
                            <el-form-item label="电话:" prop="telephone">
                                <el-input size="mini" style="width: 150px" prefix-icon="el-icon-edit"
                                          v-model="user.telephone"
                                          placeholder="请输入电话"></el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :span="8">
                            <el-form-item label="状态:" prop="enabled">
                                <el-select v-model="user.enabled" placeholder="状态" size="mini"
                                           style="width: 150px;">
                                    <el-option
                                            v-for="item in enabledList"
                                            :key="item.value"
                                            :label="item.label"
                                            :value="item.value">
                                    </el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :span="24">
                            <el-form-item label="角色:" prop="roles">
                                <!--                                <div >-->
                                <!--                                    <span style="margin-right: 5px">角色</span>-->
                                <el-switch
                                        v-for="item in roles"
                                        :key="item.irole"
                                        v-model="value"
                                        active-color="#13ce66"
                                        inactive-color="#ff4949"
                                        :active-text="item.nameZh"
                                        style="margin-right: 20px">
                                </el-switch>
                                <!--                                </div>-->
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :span="24">
                            <el-form-item label="备注:" prop="remark">
                                <el-input
                                        size="mini"
                                        type="textarea"
                                        :rows="3"
                                        style="width: 680px"
                                        placeholder="备注。。"
                                        v-model="user.remark">
                                </el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>
                </el-form>
            </div>
            <span slot="footer" class="dialog-footer">
                <el-button size="mini" @click="dialogVisible=false">取 消</el-button>
                <el-button size="mini" type="primary" @click="updateUser">确 定</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script>
    export default {
        name: "User",
        data() {
            return {
                value: true,
                loading: false,
                dialogVisible: false,
                dialogTitle: '',
                multipleSelection: [],
                showAdvanceSearchView: false,
                searchValue: {
                    username: null,
                    name: null,
                    age: null,
                    sex: null,
                    telephone: null,
                    email: null,
                    enabled: null,
                    createDateScope: null
                },
                enabledList: [{
                    value: true,
                    label: '启用'
                }, {
                    value: false,
                    label: '禁用'
                }],
                sexList: [{
                    value: 1,
                    label: '男'
                }, {
                    value: 0,
                    label: '女'
                }],
                page: 1,
                size: 10,
                keyword: '',
                users: [],
                total: 0,
                user: {
                    username: "",
                    name: "",
                    age: "",
                    sex: "",
                    telephone: "",
                    email: "",
                    remark: "",
                    enabled: true,
                },
                roles: [],
                rules: {
                    username: [{required: true, message: '请输入账号', trigger: 'blur'}],
                    name: [{required: true, message: '请输入姓名', trigger: 'blur'}],
                    age: [{required: true, message: '请输入年龄', trigger: 'blur'}],
                    sex: [{required: true, message: '请选择性别', trigger: 'blur'}],
                    telephone: [{required: true, message: '请输入电话', trigger: 'blur'}],
                    email: [{required: true, message: '请输入邮箱', trigger: 'blur'}],
                    enabled: [{required: true, message: '请输入状态', trigger: 'blur'}],
                },
            }
        },
        mounted() {
            this.initUsers();
            this.initRoles();
        },
        methods: {
            // 打开添加用户的界面
            showAddUserDialog() {
                this.dialogTitle = '添加用户';
                this.cleanDialogData();
                this.dialogVisible = true;
            },
            // 删除用户
            deleteUser(data) {
                this.$confirm('此操作将永久删除【' + data.username + '】用户, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.deleteRequest("/user/user/" + data.iuser).then(resp => {
                        if (resp) {
                            this.initUsers();
                        }
                    })
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消删除'
                    });
                });
            },
            //当选择框出现变动的时候调用的方法
            selectionChange(selection) {
                this.multipleSelection = selection;
            },
            // 批量删除用户
            deleteUsers() {
                this.$confirm('此操作将永久删除【' + this.multipleSelection.length + '】条记录, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    let ids = '?';
                    this.multipleSelection.forEach(item => {
                        ids += 'ids=' + item.iuser + '&';
                    })
                    this.deleteRequest("/user/user/" + ids).then(resp => {
                        if (resp) {
                            this.initUsers();
                        }
                    })
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消删除'
                    });
                });
            },
            // 新增用户 或者 更新用户数据
            updateUser() {
                // 更新用户数据
                if (this.user.iuser) {
                    this.$refs['userForm'].validate(valid => {
                        if (valid) {
                            this.putRequest("/user/user/", this.user).then(resp => {
                                if (resp) {
                                    this.dialogVisible = false;
                                    this.initUsers();
                                }
                            })
                        }
                    });
                } else {
                    // 新增用户数据
                    this.$refs['userForm'].validate(valid => {
                        if (valid) {
                            // alert(JSON.stringify(this.user))
                            this.postRequest("/user/user/", this.user).then(resp => {
                                if (resp) {
                                    this.dialogVisible = false;
                                    this.initUsers();
                                    this.cleanDialogData();
                                }
                            })
                        }
                    });
                }
            },
            //清除dialog数据
            cleanDialogData() {
                this.user = {
                    username: "",
                    name: "",
                    age: "",
                    sex: "",
                    telephone: "",
                    email: "",
                    remark: "",
                }
                this.dialogVisible = false;
            },
            // 编辑弹出框
            showUpdateUserDialog(data) {
                this.dialogTitle = '编辑用户';
                // alert(JSON.stringify(data));
                Object.assign(this.user, data);
                this.dialogVisible = true;
            },
            // 重置密码
            resetPassword(data) {
                this.$confirm('此操作将重置【' + data.username + '】用户密码, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.postRequest("/user/user/" + data.iuser).then(resp => {
                        if (resp) {
                            this.initUsers();
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
                this.initUsers('advanced');
            },
            //每页数量改变
            sizeChange(currentSize) {
                this.size = currentSize;
                this.initUsers();
            },
            // 初始化员工数据
            initUsers(type) {
                this.loading = true;
                let url = '/user/user/?page=' + this.page + '&size=' + this.size;
                if (type && type == 'advanced') {
                    if (this.searchValue.username) {
                        url += '&username=' + this.searchValue.username;
                    }
                    if (this.searchValue.name) {
                        url += '&name=' + this.searchValue.name;
                    }
                    if (this.searchValue.age) {
                        url += '&age=' + this.searchValue.age;
                    }
                    if (this.searchValue.sex == 1 || this.searchValue.sex == 0) {
                        url += '&sex=' + this.searchValue.sex;
                    }
                    if (this.searchValue.telephone) {
                        url += '&telephone=' + this.searchValue.telephone;
                    }
                    if (this.searchValue.email) {
                        url += '&email=' + this.searchValue.email;
                    }
                    if (this.searchValue.enabled == true || this.searchValue.enabled == false) {
                        url += '&enabled=' + this.searchValue.enabled;
                    }
                    if (this.searchValue.createDateScope) {
                        url += '&createDateScope=' + this.searchValue.createDateScope;
                    }
                } else {
                    url += "&username=" + this.keyword;
                }
                this.getRequest(url).then(resp => {
                    this.loading = false;
                    if (resp) {
                        this.users = resp.data;
                        this.total = resp.total;
                    }
                })
            },
            // 查询角色数据
            initRoles() {
                this.getRequest("/user/user/role/").then(resp => {
                    if (resp) {
                        this.roles = resp.obj;
                    }
                })
            },
            // 刷新数据
            refresh() {
                this.loading = true;
                this.initUsers();
            }
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

    .tableButton {
        padding: 5px 7px
    }
</style>
