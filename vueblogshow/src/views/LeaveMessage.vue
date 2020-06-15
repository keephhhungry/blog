<template>
    <div>
        <!--返回上方-->
        <div style="width: 100%;height: 100%;">
            <el-backtop :bottom="60"></el-backtop>
        </div>
        <!--输入留言-->
        <el-card class="box-card">
                <h2>留言板</h2>
                <span>沟通交流，拉进你我</span>
                <Tinymce ></Tinymce>
        </el-card>
        <!--展示留言-->
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
        data(){
            return{

            }
        },
        mounted() {

        },
        methods:{

        }
    }
</script>

<style scoped>

</style>
