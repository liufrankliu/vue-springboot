<template>
	<div>
		
		<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
			
		  <div  v-for="order in orderinformation">
		    <div >
		      <h4 class="panel-title">
		        <span style="margin-left: 10vw;" >
		         订单号： {{order.orderid}}
		        </span>
				<span style="margin-left: 20vw;">维修类型：{{order.machinename}}</span>
		      </h4>
		    </div>
		    <div id="collapseTwo" class="panel-collapse " role="tabpanel" aria-labelledby="headingTwo">
				<div class="panel-body">
					<span style="margin:0 6vw ">维修人：{{order.name}}</span>  
					<span tyle="margin:0 6vw ">联系电话：{{order.telenumber}}</span>
					<span style="margin:0 6vw ">维修地址：{{order.address}}</span>  
					<span style="margin:0 6vw ">维修人员：{{order.fixername}}</span>
				</div>
		    </div>
		  
		  </div>
		</div>
		
	</div>
</template>

<script>
  export default {
    data() {
      return {
        activeNames: ['1'],
		username: '1111',
		orderinformation : [
		]
      };
    },
    methods: {
      handleChange(val) {
        console.log(val);
      }
    },
	created() {
		const _this = this;
		let strCookie = document.cookie.split(";");
		for(let i = 0 ; i < strCookie.length ; i++ )
		{
			let strHead = strCookie[i].split("=");
			if(strHead[0] == " userid_fixweb"){
				this.username = strHead[1];
				break;
			}
		}
		axios
			.post("http://114.55.248.123:8181/order/information" , {username :this.username} , {
					withCredentials: true
			})
			.then(function(res){
				console.log(res.data)
				_this.orderinformation = res.data;
				for(let i = 0 ; i<_this.orderinformation.length ; i++){
					if(_this.orderinformation[i].fixername == null){
						_this.orderinformation[i].fixername = "未接单"
					}
				}
			})
			.catch(function (error) { // 请求失败处理
				console.log(error);	
			});
	}
  }
</script>

<style>
</style>
