<template>
	<div>
		<el-input v-model="input" placeholder="请输入用户名"></el-input>
		<el-input placeholder="请输入密码" v-model="message" show-password></el-input>
		<img :src="imgsrc"/>
		<el-button type="primary" @click = "tosuccess1">看不清换一张</el-button>
		<el-input v-model="loginValidateCode" placeholder="请输入验证码"></el-input>
		<el-button type="primary" @click = "tosuccess">确定</el-button>
	</div>
</template>

<script>
	export default {
	   data() {
	     return {
	       input: '',
		   message: '',
		   loginValidateCode: '',
		   imgsrc:'http://localhost:8181/loginValidateCode?'
	     }
	   },
		methods:{
			tosuccess(){
				console.log(this.loginValidateCode)
				axios
					.post('http://localhost:8181/checkregistValidateCode'  , {
						username : this.input , 
						password : this.message,
						validvode : this.loginValidateCode
					})
					.then(function(res){
						console.log(res);
					})
					.catch(function (error) { // 请求失败处理
					        console.log(error);	
						 });
					
			},
			check(){//失去焦点时检查是否用户名重复
				const _this = this;
				axios
					.get('http://localhost:8181/all')
					.then(function(res){
						_this.input = res.data[0].username;
					})
					.catch(function (error) { // 请求失败处理
					        console.log(error);	
						 });
			},
			tosuccess1()
			{
				this.imgsrc = this.imgsrc + Math.random();
			}
		}
	 }
</script>

<style>
	
</style>
