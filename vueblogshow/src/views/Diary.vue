<template>
    <div>
        <el-container>
            <el-main>
                <div class="block" style="text-align: left;margin-top: 10px">
                    <el-timeline>
                        <el-timeline-item timestamp="2018/4/12" placement="top" >
                            <el-card>
                                <h4>更新 Github 模板</h4>
                                <p>王小虎 提交于 2018/4/12 20:46</p>
                            </el-card>
                        </el-timeline-item>
                        <el-timeline-item timestamp="2018/4/3" placement="top">
                            <el-card>
                                <h4>更新 Github 模板</h4>
                                <p>王小虎 提交于 2018/4/3 20:46</p>
                            </el-card>
                        </el-timeline-item>
                        <el-timeline-item timestamp="2018/4/2" placement="top">
                            <el-card>
                                <h4>更新 Github 模板</h4>
                                <p>王小虎 提交于 2018/4/2 20:46</p>
                            </el-card>
                        </el-timeline-item>
                    </el-timeline>
                </div>
            </el-main>
        </el-container>
    </div>
</template>

<script>
    export default {
        name: "diary"
    }
</script>

<style scoped>
    .el-card{
        width:auto;
        display: inline-block;
        height:auto;
        border: 1px solid #ccc;
    }
</style>
