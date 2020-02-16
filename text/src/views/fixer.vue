<template>
	<div>
		<el-container style="height: 500px; border: 1px solid #eee">
			
		  <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
		    <el-menu :default-openeds="['1', '2']">
		     <el-submenu index="1">
		        <template slot="title"><i class="el-icon-message"></i>查询</template>
		          <el-menu-item-group>
				  <el-menu-item index="1-1">报修订单</el-menu-item>
				  </el-menu-item-group>
		      </el-submenu>
			  
		      <!-- <el-submenu index="2">
		        <template slot="title"><i class="el-icon-menu"></i>导航二</template>
		        <el-menu-item-group>
		          <el-menu-item index="2-1">选项1</el-menu-item>
		        </el-menu-item-group>
		      </el-submenu> -->
		    </el-menu>
		  </el-aside>
		  
		  <el-container>
		    <el-header style="text-align: right; font-size: 12px">
		      <span>王小虎</span>
		    </el-header>
		    
		    <el-main>
		      <el-table :data="tableData">
		        <el-table-column prop="date" label="日期" width="140">
		        </el-table-column>
		        <el-table-column prop="name" label="姓名" width="120">
		        </el-table-column>
		        <el-table-column prop="address" label="地址">
		        </el-table-column>
		      </el-table>
		    </el-main>
		  </el-container>
		</el-container>
	</div>
</template>

<script>
	export default {
	    data() {
	      const item = {
	        date: '2016-05-02',
	        name: '王小虎',
	        address: '上海市普陀区金沙江路 1518 弄'
	      };
	      return {
	        tableData: Array(2).fill(item)
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
