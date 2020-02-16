<template>
	<div>
		<el-input v-model="input" placeholder="请输入用户名"></el-input>
		<el-input placeholder="请输入密码" v-model="message" show-password></el-input>
		<img :src="imgsrc"/>
		<el-button type="primary" @click = "tosuccess1">看不清换一张</el-button>
		<el-input v-model="loginValidateCode" placeholder="请输入验证码"></el-input>
		<el-button type="primary" @click = "tosuccess">登陆</el-button>
		<el-button type="warning" @click = "toregist">注册</el-button>
	</div>
</template>

<script>
	 export default {
	    data() {
	      return {
	        input: '1111',
			message: '2222',
			loginValidateCode: '',
			imgsrc:'http://localhost:8181/loginValidateCode?',
	      }
	    },
		methods:{
			tosuccess(){
				const _this = this;
				axios
					.post('http://localhost:8181/checkloginValidcode'  , {
						username : this.input , 
						password : this.message,
						validvode : this.loginValidateCode
					})
					.then(function(res){
						if(res.data == "success")
						{
							setcookie("userid" , this.username);
							_this.$router.push("./admine");
						}
					})
					.catch(function (error) { // 请求失败处理
					        console.log(error);	
						 });
				
			},
			toregist(){
				this.$router.push("./regist");
			},
			tosuccess1()
			{
				this.imgsrc = this.imgsrc + Math.random();
			},
			setcookie(name,value){
				let exp = new Date();
					exp.setTime(exp.getTime() + 60 * 2000);
					document.cookie = name + "=" + escape(value) + ";expires=" + exp.toGMTString();
			}
		},
		created() {
			let strCookie = document.cookie.split(";");
			for(let i = 0 ; i < strCookie.length ; i++ )
			{
				let strHead = strCookie[i].split("=");
				if(strHead[0] == "userid")
				{
					username.value = strHead[1];
				}
			}
		}
		
	  }
</script>

<style>
	
</style>
