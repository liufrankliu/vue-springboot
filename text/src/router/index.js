import Vue from 'vue'
import VueRouter from 'vue-router'

import Admine from '../views/admine.vue'
import Loge from '../views/loge.vue'
import Fixer from '../views/fixer.vue'
import User from '../views/user.vue'
import Regist from '../views/regist.vue'
import UserOrder from '../views/userorder.vue'
import UserOrderStstus from '../views/userorderstatus.vue'
import UserInformation from '../views/userinformation.vue'
import FixerInformation from '../views/fixerinformation.vue'
import OrderInformation from '../views/orderinformation.vue'

Vue.use(VueRouter)

const routes = [
	{
		path: '/',
		name: 'Loge',
		component: Loge
	},
	{
		  path: '/admine',
		  name: 'Admine',
		  component: Admine,
		  redirect: "/admine/userinformation",
		  children: [
			  {
				  path:'orderinformation',
				  name: 'OrderInformation',
				  component: OrderInformation
			  },
			  {
				  path:'userinformation',
				  name: 'UserInformation',
				  component: UserInformation
			  },
			  {
				  path:'fixerinformation',
				  name: 'FixerInformation',
				  component: FixerInformation
				  
			  }
			  
		  ]
	},
	{
		path: '/fixer',
		name: 'Fixer',
		component: Fixer
	},
	{
		path: '/user',
		name: 'User',
		component: User,
		children: [
					  {
						  path:'userorder',
						  name: 'UserOrder',
						  component: UserOrder
					  },
					  {
						  path:'orderstatus',
					  	  name: 'UserOrderStstus',
						  component: UserOrderStstus
					  }
		]
	},
	{
		path: '/regist',
		name: 'Regist',
		component: Regist
	}
]

const router = new VueRouter({
  mode: 'hash',
  base: process.env.BASE_URL,
  routes
})

export default router
