<template>
    <div class="tinymce-editor">
        <Editor v-model="myValue"
                :init="init"
                :disabled="disabled"
                @onClick="onClick">
        </Editor>
    </div>
</template>

<script>
    import tinymce from 'tinymce/tinymce'
    import Editor from '@tinymce/tinymce-vue'
    import 'tinymce/themes/silver'
    import 'tinymce/plugins/image'; // 插入上传图片插件
    import 'tinymce/plugins/media';// 插入视频插件
    import 'tinymce/plugins/table';// 插入表格插件
    import 'tinymce/plugins/lists';// 列表插件
    import 'tinymce/plugins/contextmenu';
    import 'tinymce/plugins/wordcount';// 字数统计插件
    import 'tinymce/plugins/colorpicker';
    import 'tinymce/plugins/textcolor';
    import 'tinymce/icons/default';// 鼠标显示插件

    export default {
        name: "Tinymce",
        components: {
            Editor
        },
        props: {
            //传入一个value，使组件支持v-model绑定
            value: {
                type: String,
                default: ''
            },
            disabled: {
                type: Boolean,
                default: false
            },
            plugins: {
                type: [String, Array],
                default: 'lists image media table textcolor wordcount contextmenu'
            },
            toolbar: {
                type: [String, Array],
                default: 'undo redo | formatselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | lists image media table | removeformat'
            }
        },
        data() {
            return {
                //初始化配置
                init: {
                    language_url: '/tinymce/langs/zh_CN.js',// 语言包的路径
                    language: 'zh_CN',//语言
                    skin_url: '/tinymce/skins/ui/oxide',// skin路径
                    height: 1000,//编辑器高度
                    width: 900,
                    plugins: this.plugins,
                    toolbar: this.toolbar,
                    branding: false,//是否禁用“Powered by TinyMCE”
                    menubar: false,//顶部菜单栏显示
                    //此处为图片上传处理函数，这个直接用了base64的图片形式上传图片，
                    //如需ajax上传可参考https://www.tiny.cloud/docs/configure/file-image-upload/#images_upload_handler
                    images_upload_handler: (blobInfo, success, failure) => {
                        const img = 'data:image/jpeg;base64,' + blobInfo.base64();
                        success(img)
                    }
                },
                myValue: this.value
            }
        },
        mounted() {
            tinymce.init({});
        },
        methods: {
            //添加相关的事件，可用的事件参照文档=> https://github.com/tinymce/tinymce-vue => All available events
            //需要什么事件可以自己增加
            onClick(e) {
                this.$emit('onClick', e, tinymce)
            },
            //可以添加一些自己的自定义事件，如清空内容
            clear() {
                this.myValue = ''
            },
            //获取字数
            getWordCount() {
                return tinyMCE.activeEditor.plugins["wordcount"].getCount();
            }
        },
        watch: {
            value(newValue) {
                this.myValue = newValue
            },
            myValue(newValue) {
                this.$emit('input', newValue)
            }
        }
    }

</script>

<style scoped>

</style>
