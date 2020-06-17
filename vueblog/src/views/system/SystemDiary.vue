<template>
    <div>
        <!-- 首行功能按钮-->
        <div style="display: flex;justify-content: space-between">
            <div>
                <el-input
                        placeholder="请输入日记名称。。。"
                        prefix-icon="el-icon-search"
                        size="small"
                        style="width: 400px;margin-right: 10px"
                        v-model="searchValue.diaryTitle"
                        @keydown.enter.native="initDiaries"
                        :clearable="true">
                </el-input>
                <el-date-picker
                        v-model="searchValue.createDateScope"
                        type="daterange"
                        size="small"
                        style="margin-right: 10px"
                        :clearable="true"
                        value-format="yyyy-MM-dd HH:mm:ss"
                        range-separator="至"
                        start-placeholder="开始日期"
                        end-placeholder="结束日期">
                </el-date-picker>
                <el-button size="small"
                           type="primary"
                           icon="el-icon-search"
                           @click="initDiaries">搜索
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
                           @click="addDiary">添加日记
                </el-button>
            </div>
        </div>
        <!-- 表格及分页-->
        <div style="margin-top: 10px">
            <el-table
                    :data="diaries"
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
                        prop="diaryTitle"
                        label="标题"
                        align="center"
                        width="150px">
                </el-table-column>
                <el-table-column
                        prop="diaryContent"
                        label="内容"
                        show-overflow-tooltip="true"
                        align="center">
                </el-table-column>
                <el-table-column
                        prop="wordCount"
                        label="字数"
                        align="center"
                        width="50px">
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
                        width="150px">
                    <template slot-scope="scope">
                        <el-button
                                size="mini"
                                type="success"
                                icon="el-icon-edit"
                                class="tableButton"
                                @click="editDiary(scope.row)">编辑
                        </el-button>
                        <el-button
                                size="mini"
                                type="danger"
                                icon="el-icon-delete"
                                class="tableButton"
                                @click="deleteDiary(scope.row)">删除
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
        name: "Diary",
        data() {
            return {
                loading: false,
                diaries: [],
                searchValue: {
                    diaryTitle: null,
                    createDateScope: null
                },
                keyword: '',
                page: 1,
                size: 10,
                total: 0,
            }
        },
        mounted() {
            this.initDiaries();
        },
        methods: {
            //编辑日记
            editDiary(data) {
                this.$store.state.diary = data;
                this.addDiary();
            },
            //删除日记
            deleteDiary(data) {
                this.$confirm('此操作将永久删除【' + data.diaryTitle + '】日记, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.deleteRequest("/system/diary/" + data.idiary).then(resp => {
                        if (resp) {
                            this.initDiaries();
                        }
                    })
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消删除'
                    });
                });
            },
            // 获取日记
            initDiaries() {
                this.loading = true;
                let url = '/system/diary/?page=' + this.page + '&size=' + this.size;
                if (this.searchValue.diaryTitle) {
                    url += '&diaryTitle=' + this.searchValue.diaryTitle;
                }
                if (this.searchValue.createDateScope) {
                    url += '&createDateScope=' + this.searchValue.createDateScope;
                }
                this.getRequest(url).then(resp => {
                    this.loading = false;
                    if (resp) {
                        this.diaries = resp.data;
                        this.total = resp.total;
                    }
                })
            },
            //添加日记 跳转路由
            addDiary() {
                this.$router.push('/views/system/systemDiaryEdit');
            },
            //改变页数
            currentChange(currentPage) {
                this.page = currentPage;
                this.initDiaries();
            },
            //每页数量改变
            sizeChange(currentSize) {
                this.size = currentSize;
                this.initDiaries();
            },
            //刷新
            refresh() {
                this.loading = true;
                this.initDiaries();
            },
        }
    }
</script>

<style scoped>
    .tableButton {
        padding: 5px 7px
    }

    .class1 {
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: nowrap;
        /*width: 100%;*/
        /*text-overflow: ellipsis;*/
        /*white-space: nowrap;*/
        /*overflow: hidden; !** 隐藏超出的内容 **!*/
    }
</style>
