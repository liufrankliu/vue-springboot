<template>
	<div>
	  <el-table
	    :data="tableData"
	    border
	    style="width: 100%">
	    <el-table-column
	      prop="username"
	      label="用户名"
	      width="180">
	    </el-table-column>
	    <el-table-column
	      prop="password"
	      label="密码"
	      width="180">
	    </el-table-column>
	  </el-table>
	
	  <el-pagination
		:page-size="10"
		:pager-count="11"
		layout="prev, pager, next"
		:total="allcount"
		@current-change= "page">
	  </el-pagination>
	</div>
</template>

<script>
	export default{
		data(){
			return{
				allcount: '',
				tableData: []
			}
		},
		methods:{
			page(currentpage){
				const _this = this;
				axios
					.post("http://localhost:8181/page/select" , {
						currentpage:currentpage
					})
					.then(function(res){
						console.log(res);	
						_this.tableData = res.data;
					})
					.catch(function (error) { // 请求失败处理
					        console.log(error);	
						 });
			}
		},
		created(){
			const _this = this;
			axios
				.get("http://localhost:8181/count")
				.then(function(res){
					_this.allcount = res.data;
				})
				.catch(function (error) { // 请求失败处理
				        console.log(error);	
					 });
			axios
				.post("http://localhost:8181/page/select" , {
					currentpage: 1
				})
				.then(function(res){
					console.log(res);	
					_this.tableData = res.data;
				})
				.catch(function (error) { // 请求失败处理
				        console.log(error);	
					 });
		}
	}
</script>

<style>
</style>
