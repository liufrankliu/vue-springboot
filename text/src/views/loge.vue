<template>
	<div class="div-1">
		<el-input v-model="input" style="margin: 10px 0px;" placeholder="请输入用户名"></el-input>
		<el-input placeholder="请输入密码" style="margin: 10px 0px;" v-model="message" show-password></el-input>
		<img  :src="imgsrc"/>
		<el-button type="primary"  @click = "tosuccess1" class = "button-1" style="float: right;">看不清换一张</el-button>
		<el-input v-model="loginValidateCode" style="margin: 10px 0px;" placeholder="请输入验证码"></el-input>
		
		<el-form   label-width="80px">
		    <el-select v-model="logestate" placeholder="选择登陆身份" style="width: 100%; margin: 5px 0;">
		      <el-option label="用户" value="user"></el-option>
		      <el-option label="修理员" value="fixer"></el-option>
			  <el-option label="管理员" value="admine"></el-option>
		    </el-select> 
		</el-form>
		
		<el-button type="primary" @click = "tosuccess" style="width: 100%;margin: 5px 0;">登陆</el-button>
		<el-button type="warning" @click = "toregist" style="width: 100%; margin: 5px 0;">注册</el-button>
	</div>
</template>

<script>
	 export default {
	    data() {
	      return {
	        input: '',
			message: '',
			loginValidateCode: '',
			imgsrc:'',
			logestate:''
	      }
	    },
		methods:{
			tosuccess(){
				const _this = this;
				this.$ajax
					.post('http://localhost:8181/checkloginValidcode'  , {
						username : this.input , 
						password : this.message,
						validvode : this.loginValidateCode,
						logestate : this.logestate 
					})
					.then(function(res){
						console.log(res);
						if(res.data == "success")
						{
							_this.setcookie("userid_fixweb" , _this.input);
							if(_this.logestate == "user"){
								_this.$router.push("./user");
							}else if(_this.logestate == "fixer"){
								_this.$router.push("./fixer");
							}else if(_this.logestate == "admine"){
								_this.$router.push("./admine");
							}
							
						}
					})
					.catch(function (error) { // 请求失败处理
					        console.log(error);	
					});
				
			},
			toregist(){
				this.$router.push("./regist");
			},
			tosuccess1()//看不清换一张
			{
				const _this = this;
				console.log(this)
				this.$ajax//请求验证码
					.get('http://localhost:8181/loginValidateCode')
					.then(function(res){
						_this.imgsrc = res.data;
					})
					.catch(function (error) { // 请求失败处理
					        console.log(error);	
					});
			},
			setcookie(name,value){
				let exp = new Date();
					exp.setTime(exp.getTime() + 60 * 60000*4);
					document.cookie = name + "=" + escape(value) + ";expires=" + exp.toGMTString();
			}
		},
		created() {
			let strCookie = document.cookie.split(";");
			for(let i = 0 ; i < strCookie.length ; i++ )
			{
				let strHead = strCookie[i].split("=");
				console.log(strHead[0]);
				console.log(strHead[0] == " userid_fixweb");
				if(strHead[0] == " userid_fixweb"){
					console.log(11);
					this.$router.push("./user");
				}
			}
		},
		mounted() {
			const _this = this
			axios//请求验证码
				.get('http://localhost:8181/loginValidateCode' , {
					withCredentials: true
				})
				.then(function(res){
					_this.imgsrc = res.data;
				})
				.catch(function (error) { // 请求失败处理
				        console.log(error);	
				});
		}
	  }
	  
</script>

<style>
	.div-1{
		position: absolute;
		width: 20vw;
		background: white;
		top: 50%;
		left: 50%;
		transform: translate(-50% , -50%);
		padding: 20px;
		border: 1px solid black;
		border-radius: 10px;
	}
</style>
