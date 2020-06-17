<template>
    <div>
        <el-form :model="diary" :rules="rules" ref="diaryForm" :inline="true">
            <el-form-item label="标题" prop="diaryTitle">
                <el-input
                        placeholder="请输入日记标题"
                        prefix-icon="el-icon-search"
                        size="small"
                        style="width: 700px;margin-right: 10px"
                        v-model="diary.diaryTitle"
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
            <el-form-item label="内容" prop="diaryContent">
                <!--富文本编辑器-->
                <tiny-mce ref="editor"
                          v-model="diary.diaryContent"
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
        name: "SystemDiaryEdit",
        components: {
            TinyMce
        },
        data() {
            return {
                rules: {
                    diaryTitle: [{required: true, message: '请输入日记名称', trigger: 'blur'}],
                    diaryContent: [{required: true, message: '请输入日记内容', trigger: 'blur'}],
                },
                diary: {
                    diaryTitle: '',
                    diaryContent: '',
                },
            }
        },
        mounted() {
            if (this.$store.state.diary) {
                this.diary = this.$store.state.diary;
            }

        },
        methods: {
            //返回
            comeBack() {
                this.$store.state.diary = null;
                this.$router.back(-1);
            },
            //提交表单
            submitForm() {
                // 修改日记
                if (this.diary.idiary) {
                    this.$refs['diaryForm'].validate(valid => {
                        if (valid) {
                            this.diary.wordCount = this.$refs.editor.getWordCount();
                            this.putRequest("/system/diary/", this.diary).then(resp => {
                                if (resp) {
                                    this.cleanData();
                                }
                            })
                        }
                    });
                } else {
                    // 新增日记
                    this.$refs['diaryForm'].validate(valid => {
                        if (valid) {
                            this.diary.wordCount = this.$refs.editor.getWordCount()
                            this.postRequest("/system/diary/", this.diary).then(resp => {
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
                this.diary = {
                    diaryTitle: '',
                    diaryTitle: '',
                };
            }
        }
    };
</script>

<style scoped>

</style>
