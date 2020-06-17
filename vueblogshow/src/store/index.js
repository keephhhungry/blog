import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    routes:[

    ],
    article:{

    },
    defaultActive:"/views/Home",
  },
  mutations: {
    initRoutes(state,data){
      state.routes = data;
    }
  },
  actions: {
  },
  modules: {
  }
})
