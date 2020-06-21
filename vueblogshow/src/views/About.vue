<template>
    <div>
        <!--返回上方-->
        <div style="width: 100%;height: 100%;">
            <el-backtop :bottom="60"></el-backtop>
        </div>
        <el-container style="width: 100%">
            <!--中间-->
            <el-main>
                <el-row :gutter="30" style="text-align: left">
                    <el-col :span="24" style="margin: auto auto">
                        <el-card class="box-card">
                            <!--关于我-->
                            <h3>关于我</h3>
                            <p>沉迷代码，日渐消瘦，努力赚钱，养家糊口</p>
                            <p>联系方式↓↓↓↓↓↓</p>
                            <p>微信公众号：程序员小黄</p>
                            <el-divider></el-divider>
                            <!--关于本站-->
                            <h3>关于本站</h3>
                            <p>本站建于2020年07月，个人爱好写着玩。</p>
                            <p>本站结构</p>
                            <p>前端： Vue + Element UI</p>
                            <p>后端： SpringBoot + SpringSecurity +Mybatis</p>
                            <p>数据库： Mysql</p>
                            <el-divider></el-divider>

                            <!--关于版权-->
                            <h3>关于版权</h3>
                            <p>本站采用「
                                <el-link :underline="false" type="primary"
                                         href="https://creativecommons.org/licenses/by-nc/4.0/deed.zh"
                                         style="align-items: center">署名-非商业性使用 4.0 国际 (CC BY-NC 4.0)
                                </el-link>
                                」创作共享协议。
                            </p>
                            <p>只要在使用时注明出处，那么您可以可以对本站所有原创内容进行转载、节选、二次创作，但是您不得对其用于商业目的。</p>
                            <el-divider></el-divider>

                            <!--特别说明-->
                            <h3>特别说明</h3>
                            <p>本站文章仅代表个人观点，和其他任何组织或个人无关。</p>
                            <p>本站前端开发代码没有考虑对IE浏览器的兼容。</p>
                            <!--            </div>-->
                        </el-card>
                    </el-col>
                </el-row>
            </el-main>
        </el-container>
    </div>
</template>

<script>
    export default {
        name: "About",
    }
</script>

<style scoped>
    p {
        font-size: 15px;
    }

    el-link {
        font-size: 15px;
    }
</style>
