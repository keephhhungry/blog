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
                        <el-card class="box-card" style="margin-bottom: 20px">
                            <div>
                                <div style="display: flex;justify-content: space-between">
                                    <div>
                                        <p style="font-size: 20px;text-align: left">{{article.articleTitle}}</p>
                                        <span style="font-size: 10px;margin-right: 10px">作者:{{article.articleAuthor}}</span>
                                        <span style="font-size: 10px;margin-right: 10px">更新于:{{article.gmtCreate}}</span>
                                    </div>
                                    <div>
                                        <el-button type="primary" size="mini" @click="$router.back(-1)">返回</el-button>
                                    </div>
                                </div>
                                <el-divider></el-divider>
                                <div v-html="article.articleContent"></div>
                            </div>
                            <el-divider></el-divider>
                            <div>
                                <p style="font-size: 10px;text-align: left ">非特殊说明，本文版权归 程序员小黄 所，转载请注明出处</p>
                                <p style="font-size: 10px;text-align: left ">本文地址:
                                    <el-link :href="url" target="_blank" type="primary" style="margin-bottom: 1px">
                                        {{url}}
                                    </el-link>
                                </p>
                            </div>
                        </el-card>

                        <el-card class="box-card" style="margin-bottom: 20px">
                            <el-divider content-position="left">发表评论</el-divider>
                            <div style="text-align:left">
                                <el-input
                                        type="textarea"
                                        :rows="5"
                                        id="articleComment"
                                        placeholder="请输入评论内容,沟通交流，拉进你我"
                                        v-model="textarea">
                                </el-input>
                            </div>
                            <div style="text-align:left;margin-top: 10px">
                                <el-button size="small" style="text-align: left" @click="commit(textarea,0,0)">提交评论
                                </el-button>
                            </div>
                            <el-divider></el-divider>
                            <div style="text-align: left" v-for="(item,index) in articleComments" :key=index>
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
                                    </div>
                                    <!--留言内容-->
                                    <div>
                                        <span>{{item.commentContext}}</span>
                                    </div>
                                    <!--地址 时间 及回复-->
                                    <div style="font-size: 12px">
                                        <span style="margin-right: 10px">{{item.gmtCreate}}</span>
                                        <el-link type="primary">
                                            <span ref="replyLink"
                                                  @click="messageAssignment($event.target,item.iarticleComment,item.user.username,item.iarticleComment)">{{linkReplyText}}</span>
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
                                                <span style="margin-right: 10px">{{children.commentContext}}</span>
                                            </div>
                                            <!--地址 时间 及回复-->
                                            <div style="font-size: 12px">
                                                <span style="margin-right: 10px">{{children.address}}</span>
                                                <span style="margin-right: 10px">{{children.gmtCreate}}</span>
                                                <el-link type="primary">
                                                    <span ref="replyLink"
                                                          @click="messageAssignment($event.target,children.iarticleComment,children.user.username,item.iarticleComment)">{{linkReplyText}}</span>
                                                </el-link>
                                            </div>
                                        </div>
                                    </div>
                                    <!--回复框-->
                                    <div style="margin-top: 10px;display: none"
                                         ref="replyTextarea"
                                         :id="item.iarticleComment">
                                        <el-input
                                                type="textarea"
                                                :rows="3"
                                                id="childrenArticleComment"
                                                :placeholder=placeholderMessage
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
                        </el-card>
                    </el-col>
                </el-row>
            </el-main>
        </el-container>
    </div>
</template>

<script>
    export default {
        name: "BlogDetail",
        data() {
            return {
                loading: false,
                article: '',
                url: window.location.href,
                articleComments: [],
                //顶部留言框内容
                textarea: '',
                //回复留言框内容
                reply: '',
                //针对回复的评论id
                parentId: '',
                originalParentId: '',
                //默认显示
                placeholderMessage: '',
                linkReplyText: '回复',
                linkCollapseText: '收起',
            }
        },
        mounted() {
            this.initArticle();
            this.initArticleComment();
            this.addLookNum();
            //获取焦点
            document.getElementById("articleComment").focus();
        },
        methods: {
            initArticle() {
                this.loading = true;
                let iarticle = this.$route.params.iarticle;
                this.getRequest("/article/" + iarticle).then(resp => {
                    this.loading = false;
                    if (resp) {
                        this.article = resp.obj;
                    }
                })
            },
            initArticleComment() {
                let iarticle = this.$route.params.iarticle;
                this.getRequest("/comment/" + iarticle).then(resp => {
                    if (resp) {
                        this.articleComments = resp.obj;
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
                    document.getElementById("childrenArticleComment").focus();
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
            commit(commentContext, parentId, originalParentId) {
                //如果登录
                if (this.checkLoginState()) {
                    //留言为空
                    if (commentContext.trim() == "") {
                        this.$message.warning('请正确输入评论内容');
                        //留言不为空
                    } else {
                        this.postRequest("/comment/?commentContext=" + commentContext + "&parentId=" + parentId + "&originalParentId=" + originalParentId + "&iarticle=" + this.article.iarticle).then(resp => {
                            if (resp) {
                                if (parentId == 0) {
                                    this.textarea = '';
                                } else {
                                    this.reduction();
                                }
                                this.initArticleComment();
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
            //增加阅读数量
            addLookNum(){
                let iarticle = this.$route.params.iarticle;
                this.putRequest("/article/addLookNum?iarticle=" + iarticle)
            }
        }
    }
</script>

<style scoped>

</style>
