import Vue from 'vue'
import Router from 'vue-router'
import Login from "./views/Login";
import Register from "./views/Register";
import Home from './views/Home'
import Index from "./views/Index";
import Blog from "./views/Blog";
import Diary from "./views/Diary";
import LeaveMessage from "./views/LeaveMessage";
import About from "./views/About";
import BlogDetail from "./views/BlogDetail";

Vue.use(Router)

const originalPush = Router.prototype.push
Router.prototype.push = function push(location) {
    return originalPush.call(this, location).catch(err => err)
}

export default new Router({
    routes: [
        {
            path: '/',
            name: 'Index',
            component: Index,
            redirect: '/views/Home',
            children: [
                {path: '/views/Home', component: Home},
                {path: '/views/Blog', component: Blog},
                {path: '/views/Diary', component: Diary},
                {path: '/views/LeaveMessage', component: LeaveMessage},
                {path: '/views/About', component: About},
                {path: '/views/BlogDetail/:iarticle', component: BlogDetail},
            ]
        }, {
            path: '/login',
            name: 'Login',
            component: Login,
        }, {
            path: '/register',
            name: 'Register',
            component: Register,
        }
    ]
})
