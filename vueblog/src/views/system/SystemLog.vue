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
                        @keydown.enter.native="initLogs"
                        :clearable="true">
                </el-input>
                <span style="margin-right: 10px">日志类型:</span>
                <el-select v-model="searchValue.logType"
                           placeholder="日志类型"
                           size="small"
                           :clearable="true"
                           style="width: 150px;margin-right: 15px">
                    <el-option
                            v-for="item in logTypeList"
                            :key="item.value"
                            :label="item.label"
                            :value="item.value">
                    </el-option>
                </el-select>
                <span style="margin-right: 10px">创建时间:</span>
                <el-date-picker
                        type="daterange"
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
                           @click="initLogs">搜索
                </el-button>
                <el-button size="small"
                           type="success"
                           icon="el-icon-refresh-right"
                           @click="refresh">刷新
                </el-button>
                <el-button size="small"
                           type="success"
                           icon="el-icon-refresh-right"
                           @click="userDownload">用户数据下载
                </el-button>
                <el-button size="small"
                           type="success"
                           icon="el-icon-refresh-right"
                           @click="provinceDownload">省份数据下载
                </el-button>
            </div>
        </div>
        <!-- 表格及分页-->
        <div style="margin-top: 10px">
            <el-table
                    :data="logs"
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
                        prop="browserName"
                        label="浏览器名字"
                        align="center"
                        width="90">
                </el-table-column>
                <el-table-column
                        prop="browserVersion"
                        label="浏览器版本"
                        align="center"
                        width="110">
                </el-table-column>
                <el-table-column
                        prop="operatingSystem"
                        align="center"
                        label="操作系统"
                        width="100">
                </el-table-column>
                <el-table-column
                        prop="ip"
                        align="center"
                        label="ip"
                        width="100">
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
                        prop="url"
                        align="center"
                        show-overflow-tooltip="true"
                        label="路径"
                        width="350">
                </el-table-column>
                <el-table-column
                        prop="operationalParameter"
                        align="center"
                        show-overflow-tooltip="true"
                        label="操作参数"
                        width="200">
                </el-table-column>
                <el-table-column
                        prop="remark"
                        align="center"
                        show-overflow-tooltip="true"
                        label="备注"
                        width="200">
                </el-table-column>
                <el-table-column
                        prop="logType"
                        align="center"
                        label="日志类型"
                        width="100">
                    <template slot-scope="scope">
                        <span v-if="scope.row.logType==1">前台登录日志</span>
                        <span v-if="scope.row.logType==2">后台登录日志</span>
                        <span v-if="scope.row.logType==3">前台访问日志</span>
                        <span v-if="scope.row.logType==4">后台操作日志</span>
                    </template>
                </el-table-column>
                <el-table-column
                        prop="gmtCreate"
                        align="center"
                        label="创建时间"
                        width="150">
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
    import {Message} from "element-ui";

    export default {
        name: "SystemLog",
        data() {
            return {
                loading: false,
                logs: [],
                logTypeList: [{
                    value: 1,
                    label: '前台登录日志'
                }, {
                    value: 2,
                    label: '后台登录日志'
                }, {
                    value: 3,
                    label: '前台访问日志'
                }, {
                    value: 4,
                    label: '后台操作日志'
                }],
                searchValue: {
                    keyword: null,
                    createDateScope: null,
                    logType: null
                },
                page: 1,
                size: 10,
                total: 0,
            }
        },
        mounted() {
            this.initLogs();
        },
        methods: {
            // 获取友链
            initLogs() {
                this.loading = true;
                let url = '/system/log/?page=' + this.page + '&size=' + this.size;
                if (this.searchValue.keyword) {
                    url += '&user.username=' + this.searchValue.keyword;
                }
                if (this.searchValue.logType) {
                    url += '&logType=' + this.searchValue.logType;
                }
                if (this.searchValue.createDateScope) {
                    url += '&createDateScope=' + this.searchValue.createDateScope;
                }
                this.getRequest(url).then(resp => {
                    this.loading = false;
                    if (resp) {
                        this.logs = resp.data;
                        this.total = resp.total;
                    }
                })
            },
            //改变页数
            currentChange(currentPage) {
                this.page = currentPage;
                this.initLogs();
            },
            //每页数量改变
            sizeChange(currentSize) {
                this.size = currentSize;
                this.initLogs();
            },
            //刷新
            refresh() {
                this.loading = true;
                this.initLogs();
            },
            //用户数据下载
            userDownload() {
                if (!this.searchValue.createDateScope) {
                    Message.error({message: '请选要导出的时间范围'});
                } else {
                    window.open("/system/log/userDataDownload?createDateScope=" + this.searchValue.createDateScope, '_parent')
                }


            },
            //省份数据下载
            provinceDownload() {
                if (!this.searchValue.createDateScope) {
                    Message.error({message: '请选要导出的时间范围'});
                }
            }
        }
    }
</script>

<style scoped>
    .tableButton {
        padding: 5px 7px
    }
</style>
