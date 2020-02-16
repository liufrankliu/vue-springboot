<template>
	<div>
		<el-container style="height: 500px; border: 1px solid #eee">
			
		  <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
		    <el-menu :default-openeds="['1', '2']">
		     <el-submenu index="1">
		        <template slot="title"><i class="el-icon-message"></i>报修</template>
				<el-menu-item-group>
		          <el-menu-item index="1-1">报修</el-menu-item>
				</el-menu-item-group>
		      </el-submenu>
			  
		      <el-submenu index="2">
		        <template slot="title"><i class="el-icon-menu"></i>查询</template>
		        <el-menu-item-group>
		          <el-menu-item index="2-1">查询订单状态</el-menu-item>
		        </el-menu-item-group>
		      </el-submenu> 
		    </el-menu>
		  </el-aside>
		  
		  <el-container>
		    <el-header style="text-align: right; font-size: 12px">
		      <span>王小虎</span>
		    </el-header>
		    
			
			<router-view></router-view>
			
		  </el-container>
		</el-container>
	</div>
</template>

<script>
	export default {
		
	  };
</script>

<style>
	.el-header {
	  background-color: #B3C0D1;
	  color: #333;
	  line-height: 60px;
	}
	
	.el-aside {
	  color: #333;
	}
</style>
