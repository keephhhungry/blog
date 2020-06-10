<template>
    <div>
        <el-form :model="article" :rules="rules" ref="ruleForm" :inline="true">
            <el-form-item label="标题" prop="articleTitle">
                <el-input
                        placeholder="请输入文章标题"
                        prefix-icon="el-icon-search"
                        size="small"
                        style="width: 700px;margin-right: 10px"
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
                        :clearable="true">
                </el-input>
            </el-form-item>
            <el-form-item label="原创" prop="isOriginal">
                <el-select v-model="article.isOriginal" size="small" style="width: 200px" placeholder="是否原创">
                    <el-option
                            v-for="item in isOriginals"
                            :key="item.value"
                            :label="item.label"
                            :value="item.value">
                    </el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="类型" prop="iarticleType">
                <el-select v-model="article.iarticleType" size="small" style="width: 200px" placeholder="文章类型">
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
        </el-form>
        <!--富文本编辑器-->
        <tiny-mce></tiny-mce>
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
                    isOriginal: 1
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
                this.$router.back(-1);
            },
            //提交表单
            submitForm() {
                this.$refs['ruleForm'].validate(valid => {
                    if (valid) {
                        alert("ok");
                        // this.putRequest("/article/type/", this.articleType).then(resp => {
                        //     if (resp) {
                        //         this.dialogVisible = false;
                        //         this.initArticleTypes();
                        //     }
                        // })
                    }
                });
            }
        }
    };
</script>

<style scoped>

</style>
