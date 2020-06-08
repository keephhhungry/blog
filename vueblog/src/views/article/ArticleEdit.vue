<template>
    <div>
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
        components:{
            TinyMce
        }
    };
</script>

<style scoped>

</style>
