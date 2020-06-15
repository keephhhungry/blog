<template>
    <div>
        <!--返回上方-->
        <div style="width: 100%;height: 100%;">
            <el-backtop :bottom="60"></el-backtop>
        </div>
        <el-container>
            <el-main>
                <div class="block" style="text-align: left;margin-top: 10px">
                    <h4>建站日记记录</h4>
                    <el-timeline>
                        <el-timeline-item :timestamp=item.gmtCreate placement="top" v-for="(item,index) in diaries"
                                          :key="index">
                            <el-card shadow="hover" body-style="padding: 1px 15px">
                                <h4>{{item.diaryTitle}}</h4>
                                <p style="word-break:break-word;">{{item.diaryContent}}</p>
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
        name: "diary",
        data() {
            return {
                diaries: [],
            }
        },
        mounted() {
            this.initDiary();
        },
        methods: {
            initDiary() {
                this.getRequest("/diary/").then(resp => {
                    if (resp) {
                        this.diaries = resp.obj;
                    }
                })
            }
        }
    }
</script>

<style scoped>
    .el-card {
        width: auto;
        max-width: 100%;
        display: inline-block;
        height: auto;
        border: 1px solid #ccc;
    }

</style>
