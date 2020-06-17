import Vue from 'vue'
import Router from 'vue-router'
import Login from './views/Login.vue'
import Home from './views/Home'
import ArticleEdit from './views/article/ArticleEdit'
import SystemDiaryEdit from "./views/system/SystemDiaryEdit";

Vue.use(Router)

export default new Router({
    routes: [
        {
            path: '/',
            name: 'Login',
            component: Login,
            hidden: true
        }, {
            path: '/home',
            name: 'Home',
            component: Home,
            hidden: true,
            children: [
                {path: '/views/article/artilceEdit', component: ArticleEdit},
                {path: '/views/system/systemDiaryEdit', component: SystemDiaryEdit},
            ]
        }
    ]
})
