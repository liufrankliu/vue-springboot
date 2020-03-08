<template>
	<el-form ref="form" :model="form" label-width="80px">
		<el-form-item label="姓名">
			<el-input v-model="form.name"></el-input>
		</el-form-item>
		
		<el-form-item label="电弧">
			<el-input v-model="form.telenumber"></el-input>
		</el-form-item>
		
	    <el-form-item label="修理种类">
			<el-select v-model="form.machinename" placeholder="请选择修理种类">
				<el-option label="洗衣机" value="洗衣机"></el-option>
				<el-option label="电视机" value="电视机"></el-option>
				<el-option label="空调" value="空调"></el-option>
				<el-option label="热水器" value="热水器"></el-option>
			</el-select>
	    </el-form-item>
<!-- 
	    <div class="info">
	        <div class="input-item">
	            <span class="input-item-text" style="width:8rem;">请输入关键字</span>
				<input id='tipinput' type="text" >
	        </div>
	    </div> -->
		
		<el-form-item label="请输入地址">
			<el-input id="tipinput"></el-input>
		</el-form-item>
		
		<!-- Modal -->

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
	          username: '2223',//获取用户名///////////////
			  name:"",
			  telenumber: "",
	          machinename: '',
			  address: '',
	          message: ''
	        },
			city1 : '',
			searchService : [],
			markers : [],
			citylocation : [],
	      }
	    },
	    methods: {
	      onSubmit() {
			  const _this = this;
			  this.form.address = this.city1 + document.getElementById('tipinput').value;
			  axios
			  	.post('http://114.55.248.123:8181/order' , this.form)
			  	.then(function(res){
			  		if(res.data == "success"){
						alert(订单提交成功)
						window.location.reload()
					}
			  	})
			  	.catch(function (error) { // 请求失败处理
			  	        console.log(error);	
			  	});
			},
			
	    },
		mounted() {
			var citysearch = new AMap.CitySearch();
			const _this = this;
			//自动获取用户IP，返回当前城市
			citysearch.getLocalCity(function(status, result) {
			    if (status === 'complete' && result.info === 'OK') {
			        if (result && result.city && result.bounds) {
			            _this.city1 = result.city;
						AMap.plugin('AMap.Autocomplete', function(){
						
						  // 实例化Autocomplete
						  var autoOptions = {
						    // input 为绑定输入提示功能的input的DOM ID
							city: _this.city1,
						    input: 'tipinput'
						  }
						  var autoComplete= new AMap.Autocomplete(autoOptions);
						  // 无需再手动执行search方法，autoComplete会根据传入input对应的DOM动态触发search
						})
			        }
			    } else{
					alert(服务加载失败);
				}
			});
			
		},
		created() {
			
		}
	  }
</script>

<style>
</style>
