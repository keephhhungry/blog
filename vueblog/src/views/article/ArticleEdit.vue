<template>
    <div>
        <el-form :model="article" :rules="rules" ref="ruleForm" :inline="true">
            <el-form-item label="标题" prop="articleTitle">
                <el-input
                        placeholder="请输入文章标题"
                        prefix-icon="el-icon-search"
                        size="small"
                        style="width: 700px;margin-right: 10px"
                        v-model="article.articleTitle"
                        :clearable="true">
                </el-input>
            </el-form-item>
            <el-form-item>
                <el-button
                        type="primary"
                        size="small"
                        style="width: 80px"
                        @click="comeBack">返回
                </el-button>
            </el-form-item>
            <el-form-item>
                <el-button size="small"
                           type="primary"
                           style="width: 80px"
                           icon="el-icon-search"
                           @click="submitForm">提交
                </el-button>
            </el-form-item>
            <el-form-item label="作者" prop="articleAuthor">
                <el-input
                        placeholder="请输入作者"
                        prefix-icon="el-icon-search"
                        size="small"
                        style="width: 200px;margin-right: 10px"
                        v-model="article.articleAuthor"
                        :clearable="true">
                </el-input>
            </el-form-item>
            <el-form-item label="原创" prop="isOriginal">
                <el-select v-model="article.isOriginal" :clearable="true" size="small" style="width: 200px"
                           placeholder="是否原创">
                    <el-option
                            v-for="item in isOriginals"
                            :key="item.value"
                            :label="item.label"
                            :value="item.value">
                    </el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="类型" prop="iarticleType">
                <el-select v-model="article.iarticleType" :clearable="true" size="small" style="width: 200px"
                           placeholder="文章类型">
                    <el-option
                            v-for="item in articleTypes"
                            :key="item.iarticleType"
                            :label="item.typeName"
                            :value="item.iarticleType">
                    </el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="简介" prop="articleIntroduction">
                <el-input
                        type="textarea"
                        :rows="3"
                        style="width: 900px;"
                        placeholder="请输入文章简介"
                        v-model="article.articleIntroduction">
                </el-input>
            </el-form-item>
            <el-form-item label="内容" prop="articleContent">
                <!--富文本编辑器-->
                <tiny-mce ref="editor"
                          v-model="article.articleContent"
                ></tiny-mce>
            </el-form-item>
        </el-form>
    </div>
</template>

<script>

    import TinyMce from '../../components/Tinymce';

    function getBase64(img, callback) {
        const reader = new FileReader();
        reader.addEventListener('load', () => callback(reader.result));
        reader.readAsDataURL(img)
    }

    export default {
        name: "ArticleEdit",
        components: {
            TinyMce
        },
        data() {
            return {
                rules: {
                    articleAuthor: [{required: true, message: '请输入文章作者', trigger: 'blur'}],
                    articleTitle: [{required: true, message: '请输入文章名称', trigger: 'blur'}],
                    articleIntroduction: [{required: true, message: '请输入文章简介', trigger: 'blur'}],
                    articleContent: [{required: true, message: '请输入文章内容', trigger: 'blur'}],
                    iarticleType: [{required: true, message: '请选择文章类型', trigger: 'blur'}],
                    isOriginal: [{required: true, message: '请选择是否原创', trigger: 'blur'}],
                },
                article: {
                    articleAuthor: '',
                    articleTitle: '',
                    articleIntroduction: '',
                    articleContent: '',
                    iarticleType: '',
                    isOriginal: ''
                },
                isOriginals: [{
                    label: "原创",
                    value: 1
                }, {
                    label: "非原创",
                    value: 0
                }],
                articleTypes: [],
            }
        },
        mounted() {
            this.initArticleTypes();
            if (this.$store.state.article) {
                this.article = this.$store.state.article;
            }

        },
        methods: {
            //初始化文章类型
            initArticleTypes() {
                let url = "/article/article/articleType/";
                this.getRequest(url).then(resp => {
                    this.loading = false;
                    if (resp) {
                        this.articleTypes = resp.data;
                    }
                })
            },
            //返回
            comeBack() {
                this.$store.state.article = null;
                this.$router.back(-1);
            },
            //提交表单
            submitForm() {
                // 修改文章
                if (this.article.iarticle) {
                    this.$refs['ruleForm'].validate(valid => {
                        if (valid) {
                            this.article.wordCount = this.$refs.editor.getWordCount();
                            this.putRequest("/article/article/", this.article).then(resp => {
                                if (resp) {
                                    this.cleanData();
                                }
                            })
                        }
                    });
                } else {
                    // 新增文章
                    this.$refs['ruleForm'].validate(valid => {
                        if (valid) {
                            this.article.wordCount = this.$refs.editor.getWordCount();
                            this.postRequest("/article/article/", this.article).then(resp => {
                                if (resp) {
                                    this.cleanData();
                                }
                            })
                        }
                    });
                }
            },
            //清除表单数据
            cleanData() {
                this.article = {
                    articleAuthor: '',
                    articleTitle: '',
                    articleIntroduction: '',
                    articleContent: '',
                    iarticleType: '',
                    isOriginal: 1
                };
            }
        }
    };
</script>

<style scoped>

</style>
