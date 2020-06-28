<template>
    <div>
        <!--返回上方-->
        <div style="width: 100%;height: 100%;">
            <el-backtop :bottom="60"></el-backtop>
        </div>
        <el-container style="width: 100%"
                      v-loading="loading"
                      element-loading-text="正在拼命加载中..."
                      element-loading-spinner="el-icon-loading"
                      element-loading-background="rgba(0,0,0,0.1)">
            <!--中间-->
            <el-main
                    style="margin-bottom: 30px">
                <el-row :gutter="30">
                    <el-col :span="24">
                        <!--输入留言-->
                        <el-card class="box-card">
                            <span style="font-size: 20px;font-weight: 700"></span>
                            <div style="text-align:left;margin-top: 10px">
                                <el-input
                                        type="textarea"
                                        :rows="5"
                                        id="leaveMessage"
                                        placeholder="请输入留言内容,沟通交流，拉进你我"
                                        v-model="textarea">
                                </el-input>
                            </div>
                            <div style="text-align:left;margin-top: 10px">
                                <el-button size="small" style="text-align: left" @click="commit(textarea,0,0)">提交留言
                                </el-button>
                            </div>
                        </el-card>
                        <!--展示留言-->
                        <el-card class="box-card" style="margin-top: 20px">
                            <div style="text-align: left" v-for="(item,index) in leaveMessages" :key=index>
                                <!--头像-->
                                <el-avatar
                                        shape="square"
                                        :size=60
                                        style="float: left"
                                        src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png">
                                </el-avatar>

                                <div style="margin-left: 70px">
                                    <!--名字 浏览器-->
                                    <div>
                                        <span style="margin-right: 10px;color: #207ab7">{{item.user.username}}</span>
                                        <span>{{item.browserName}}</span>
                                    </div>
                                    <!--留言内容-->
                                    <div>
                                        <span>{{item.messageContext}}</span>
                                    </div>
                                    <!--地址 时间 及回复-->
                                    <div style="font-size: 12px">
                                        <span style="margin-right: 10px">{{item.address}}</span>
                                        <span style="margin-right: 10px">{{item.gmtCreate}}</span>
                                        <el-link type="primary">
                                            <span ref="replyLink"
                                                  @click="messageAssignment($event.target,item.ileaveMessage,item.user.username,item.ileaveMessage)">{{linkReplyText}}</span>
                                        </el-link>
                                    </div>
                                    <el-divider v-if="item.children.length!=0"></el-divider>

                                    <!--嵌套回复-->
                                    <div v-for="(children,index1) in item.children" :key="index1">
                                        <el-avatar
                                                shape="square"
                                                :size=40
                                                style="float: left"
                                                src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png">
                                        </el-avatar>
                                        <div style="margin-left: 50px">
                                            <!--名字 浏览器-->
                                            <div>
                                                <span style="margin-right: 10px;color: #207ab7">{{children.user.username}}</span>
                                                <span style="margin-right: 10px">回复</span>
                                                <span style="margin-right: 10px;color: #207ab7">{{item.user.username}}</span>
                                                <span style="margin-right: 10px">{{children.messageContext}}</span>
                                            </div>
                                            <!--地址 时间 及回复-->
                                            <div style="font-size: 12px">
                                                <span style="margin-right: 10px">{{children.address}}</span>
                                                <span style="margin-right: 10px">{{children.browserName}}</span>
                                                <span style="margin-right: 10px">{{children.gmtCreate}}</span>
                                                <el-link type="primary">
                                                    <span ref="replyLink"
                                                          @click="messageAssignment($event.target,children.ileaveMessage,children.user.username,item.ileaveMessage)">{{linkReplyText}}</span>
                                                </el-link>
                                            </div>
                                        </div>
                                    </div>
                                    <!--回复框-->
                                    <div style="margin-top: 10px;display: none"
                                         ref="replyTextarea"
                                         :id="item.ileaveMessage">
                                        <el-input
                                                type="textarea"
                                                :rows="3"
                                                :placeholder=placeholderMessage
                                                id="childrenLeaveMessage"
                                                v-model="reply">
                                        </el-input>
                                        <el-button type="success" size="mini" style="margin-top: 5px"
                                                   @click="commit(reply,parentId,originalParentId)">
                                            提交
                                        </el-button>
                                    </div>
                                </div>
                                <el-divider></el-divider>
                            </div>
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
                        </el-card>
                    </el-col>
                </el-row>
            </el-main>
        </el-container>
    </div>
</template>

<script>

    import Tinymce from "../components/Tinymce";

    function getBase64(img, callback) {
        const reader = new FileReader();
        reader.addEventListener('load', () => callback(reader.result));
        reader.readAsDataURL(img)
    }

    export default {
        name: "LeaveMessage",
        components: {
            Tinymce
        },
        data() {
            return {
                loading: false,
                user: null,
                //顶部留言框内容
                textarea: '',
                //回复留言框内容
                reply: '',
                total: 0,
                page: 1,
                size: 10,
                leaveMessages: [],
                //针对回复的留言id
                parentId: '',
                originalParentId: '',
                //默认显示
                placeholderMessage: '',
                linkReplyText: '回复',
                linkCollapseText: '收起',
            }
        },
        mounted() {
            //初始化留言数据
            this.initLeaveMessage();
            //获取焦点
            document.getElementById("leaveMessage").focus();
        },
        methods: {
            //初始化数据
            initLeaveMessage() {
                this.loading = true;
                this.getRequest("/leaveMessage/?page=" + this.page + "&size=" + this.size).then(resp => {
                    this.loading = false;
                    if (resp) {
                        this.total = resp.total;
                        this.leaveMessages = resp.data;
                    }
                })
            },
            //点击回复，给回复id赋值，给placeholder赋值
            messageAssignment(el, parentId, username, textareaId) {
                let text = el.innerText.trim();
                this.reduction();
                if (text == '回复') {
                    this.reduction();
                    el.textContent = this.linkCollapseText;
                    //显示对应的文本域
                    this.placeholderMessage = "回复【" + username + "】";
                    this.parentId = parentId;
                    this.originalParentId = textareaId;
                    document.getElementById(textareaId).style.display = "block";
                    document.getElementById("childrenLeaveMessage").focus();
                }
            },
            //还原
            reduction() {
                // 1.文本域赋值为空
                this.reply = '';
                // 2.隐藏所有的文本域，
                let textareas = this.$refs.replyTextarea;
                for (let i = 0; i < textareas.length; i++) {
                    textareas[i].style.display = "none";
                }
                // 3.将所有链接的文字改为'回复'
                let replyLinks = this.$refs.replyLink;
                for (let i = 0; i < replyLinks.length; i++) {
                    replyLinks[i].textContent = this.linkReplyText;
                }
            },
            //提交
            commit(messageContext, parentId, originalParentId) {
                //如果登录
                if (this.checkLoginState()) {
                    //留言为空
                    if (messageContext.trim() == "") {
                        this.$message.warning('请正确输入留言内容');
                        //留言不为空
                    } else {
                        this.postRequest("/leaveMessage/?messageContext=" + messageContext + "&parentId=" + parentId + "&originalParentId=" + originalParentId).then(resp => {
                            if (resp) {
                                if (parentId == 0) {
                                    this.textarea = '';
                                } else {
                                    this.reduction();
                                }
                                this.initLeaveMessage();
                            }
                        })
                    }
                    //没有登录提示登录
                } else {
                    this.$confirm('留言需要先登录，点击确定跳转到登录页?', '提示', {
                        confirmButtonText: '确定',
                        cancelButtonText: '取消',
                        type: 'warning'
                    }).then(() => {
                        this.$router.push("/login");
                    }).catch(() => {
                        this.$message({
                            type: 'info',
                            message: '已取消跳转'
                        });
                    });
                }
            },
            //检查登录状态
            checkLoginState() {
                let user = window.sessionStorage.getItem("user");
                this.user = eval('(' + user + ')');
                if (this.user) {
                    return true;
                } else {
                    return false;
                }
            },
            //改变页数
            currentChange(currentPage) {
                this.page = currentPage;
                this.initLeaveMessage();
                scrollTo(0, 0);
            },
            //每页数量改变
            sizeChange(currentSize) {
                this.size = currentSize;
                this.initLeaveMessage();
            },
        }
    }
</script>

<style scoped>

</style>
