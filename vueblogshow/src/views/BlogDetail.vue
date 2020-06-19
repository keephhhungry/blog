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
                                    <el-link :href="url" target="_blank" type="primary" style="margin-bottom: 1px">{{url}}</el-link>
                                </p>
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
            }
        },
        mounted() {
            this.init();
        },
        methods: {
            init() {
                this.loading = true;
                let iarticle = this.$route.params.iarticle;
                this.getRequest("/article/" + iarticle).then(resp => {
                    if (resp) {
                        this.loading = false;
                        this.article = resp.obj;
                    }
                })
            }
        }
    }
</script>

<style scoped>

</style>
