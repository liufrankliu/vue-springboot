<template>
	<el-form ref="form" :model="form" label-width="80px">
	  <el-form-item label="活动区域">
	    <el-select v-model="form.machinename" placeholder="请选择修理种类">
	      <el-option label="洗衣机" value="洗衣机"></el-option>
	      <el-option label="电视机" value="电视机"></el-option>
		  <el-option label="空调" value="空调"></el-option>
		  <el-option label="热水器" value="热水器"></el-option>
	    </el-select>
	  </el-form-item>

	  <el-form-item label="家庭住址">
	  <el-input v-model="form.address"></el-input>
	  </el-form-item>

	  <el-form-item label="留言">
	    <el-input type="textarea" v-model="form.message"></el-input>
	  </el-form-item>
	  
	  <el-form-item>
	    <el-button type="primary" @click="onSubmit">立即创建</el-button>
	  </el-form-item>
	</el-form>
</template>

<script>
	export default {
	    data() {
	      return {
	        form: {
	          username: '1111',//获取用户名///////////////
	          machinename: '',
			  address: '',
	          message: ''
	        }
	      }
	    },
	    methods: {
	      onSubmit() {
			  axios
			  	.post('http://localhost:8181/order' , this.form)
			  	.then(function(res){
			  		console.log(res)
			  	})
			  	.catch(function (error) { // 请求失败处理
			  	        console.log(error);	
			  		 });
	      }
	    }
	  }
</script>

<style>
</style>
