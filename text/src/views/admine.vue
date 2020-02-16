<template>
	<div>
		<el-container style="height: 500px; border: 1px solid #eee">
			
		  <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
		    <el-menu>
		     <el-submenu index="1">
		        <template slot="title"><i class="el-icon-message"></i>管理用户</template>
		          <el-menu-item @click="setParams(0)">用户信息</el-menu-item>
		      </el-submenu>
			  
		      <el-submenu index="2">
		        <template slot="title"><i class="el-icon-menu"></i>管理修理员</template>
		        <el-menu-item-group>
		          <el-menu-item  @click="setParams(1)">修理员信息</el-menu-item>
		        </el-menu-item-group>
		      </el-submenu> 
			  
			  <el-submenu index="3">
			    <template slot="title"><i class="el-icon-menu"></i>订单信息</template>
			    <el-menu-item-group>
			      <el-menu-item  @click="setParams(2)">订单数据分析</el-menu-item>
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
	    data() {
	      return {
	        index : [
				{
					path : "userinformation"
				},
				{
					path : "fixerinformation"
				},
				{
					path : "orderinformation"
				}
			]
	      }
	    },
		methods:{
			setParams(i){
				this.$router.push({path: '/admine/' + this.index[i].path});
			}
		}
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
