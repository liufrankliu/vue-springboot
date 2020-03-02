import Vue from 'vue'
import './plugins/axios'
import App from './app.vue'
import router from './router'
import store from './store'
import './plugins/element.js'
//import admine from './admine.vue'

import axios from 'axios'

Vue.config.productionTip = false

new Vue(
	{
		el:"#app",
		router,
		store,
		render: h => h(App)
	}
)
Vue.prototype.$ajax = axios
axios.defaults.withCredentials=true