import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import 'font-awesome/css/font-awesome.css'


import {postKeyValueRequest} from "./utils/api";
import {postRequest} from "./utils/api";
import {getRequest} from "./utils/api";
import {deleteRequest} from "./utils/api";
import {putRequest} from "./utils/api";

Vue.prototype.postKeyValueRequest = postKeyValueRequest;
Vue.prototype.postRequest = postRequest;
Vue.prototype.getRequest = getRequest;
Vue.prototype.deleteRequest = deleteRequest;
Vue.prototype.putRequest = putRequest;

Vue.use(ElementUI);

Vue.config.productionTip = false

router.beforeEach((to, from, next) => {
    if (to.path == from.path) {
        return;
    }else{
      next();
    }
})

new Vue({
    router,
    store,
    render: h => h(App)
}).$mount('#app')
