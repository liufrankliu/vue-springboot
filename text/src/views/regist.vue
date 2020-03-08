<template>
	<div class="div-1">
		<el-input v-model="input" placeholder="请输入用户名" style="margin: 10px 0;"></el-input>
		<el-input placeholder="请输入密码" style="margin: 10px 0;" v-model="message" show-password></el-input>
		<img :src="imgsrc"/>
		<el-button type="primary" @click = "tosuccess1" style="float: right;">看不清换一张</el-button>
		
		<el-form   label-width="80px">
		    <el-select v-model="logestate" placeholder="选择登陆身份" style="width: 100%; margin: 5px 0;">
		      <el-option label="用户" value="user"></el-option>
		      <el-option label="修理员" value="fixer"></el-option>
		    </el-select> 
		</el-form>
		
		<el-input v-model="loginValidateCode" placeholder="请输入验证码" style="margin: 10px 0;"></el-input>
		<el-button type="primary" @click = "tosuccess" style="width: 100%; margin: 5px 0;">确定</el-button>
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
					.post('http://114.55.248.123:8181/checkregistValidateCode'  , {
						username : this.input , 
						password : this.message,
						validvode : this.loginValidateCode,
						logestate : this.logestate
					})
					.then(function(res){
						if(res.data == "success"){
							_this.$router.push("./");
							alert("注册成功");
						}else{
							alert(res.data);
						}
					})
					.catch(function (error) { // 请求失败处理
					        console.log(error);	
					});
					
			},
			check(){//失去焦点时检查是否用户名重复
				const _this = this;
				this.$ajax
					.get('http://114.55.248.123:8181/all')
					.then(function(res){
						_this.input = res.data[0].username;
					})
					.catch(function (error) { // 请求失败处理
					        console.log(error);	
						 });
			},
			tosuccess1()
			{
				const _this = this;
				this.$ajax//请求验证码
					.get('http://114.55.248.123:8181/loginValidateCode')
					.then(function(res){
					_this.imgsrc = res.data;
					})
					.catch(function (error) { // 请求失败处理
					        console.log(error);	
					});
			},
		},
		created() {
			const _this = this;
			axios//请求验证码
				.get('http://114.55.248.123:8181/loginValidateCode' , {
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
