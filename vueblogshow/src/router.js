import Vue from 'vue'
import Router from 'vue-router'
import Home from './views/Home'
import Index from "./views/Index";
import Blog from "./views/Blog";
import Diary from "./views/Diary";
import LeaveMessage from "./views/LeaveMessage";
import About from "./views/About";

Vue.use(Router)

export default new Router({
    routes: [
        {
            path: '/',
            name: 'Index',
            component: Index,
            hidden: true,
            redirect: '/views/Home',
            children: [
                {path: '/views/Home', component: Home},
                {path: '/views/Blog', component: Blog},
                {path: '/views/Diary', component: Diary},
                {path: '/views/LeaveMessage', component: LeaveMessage},
                {path: '/views/About', component: About},
            ]
        }
    ]
})
