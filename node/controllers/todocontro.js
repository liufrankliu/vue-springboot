let makeCapcha = require('./makeCapcha.js');
const mysql = require('mysql');

var connection = mysql.createConnection({
  host: 'localhost',
  user: 'root',
  password: 'lhb980601',
  database: 'fixersystem'
})
connection.connect();


module.exports = function (app) {
	app.get('/loginValidateCode' , function(req , res){//验证码请求
		let q = makeCapcha();
		console.log(req.sessionID);
		req.session.code = q[1];
		res.send('data:image/bmp;base64,' + q[0]);
	})

	app.post('/checkloginValidcode' , function(req , res){//验证账号密码
		//console.log(req.sessionID + "======")
		let addsql;
		if(req.body.logestate == "user"){
			addsql = 'SELECT username,password FROM user WHERE username = "' + req.body.username +'"';
		}else if(req.body.logestate == "fixer"){
			addsql = 'SELECT username,password FROM fixer WHERE username = "' + req.body.username +'"';
		}else if(req.body.logestate == "admine"){
			addsql = 'SELECT username,password FROM admine WHERE username = "' + req.body.username +'"';
		}
		if(req.body.validvode == req.session.code){
			connection.query(addsql  , function(error , result){
				console.log(result)
            	if(error){
            		console.log(error)
            		res.send("错误");
            	}
            	else{//从数据库读取数据是否正确(是否有此人)
                	if(result[0] == undefined){
                   		res.send("查无此人");
                	}
                	else {
                		if(req.body.username == result[0].username && req.body.password == result[0].password){
                			res.send("success");
                		}else{
                			res.send("用户名或密码不对");
                		}
               		}
            	}
        	});
		}else{
			res.send("验证码错误");
		}
		
	})

	app.post('/checkregistValidateCode' , function(req , res){//注册
		if(req.body.validvode == req.session.code){
			connection.query('SELECT username FROM user WHERE username = "' + req.body.username +'"' , function(error , result){
            	if(error)
            	{
                	console.log(error)
                	return;
            	}
            	else
            	{
                	if(result[0] != undefined)
                	{
                    	res.send("repeat");
                	}
                	else
                	{
                    	let addsql;
                    	if(req.body.logestate == "fixer"){
                    		addsql = 'INSERT INTO fixer(username , password) VALUES(? , ?)';
                    	}else if(req.body.logestate == "user"){
                    		addsql = 'INSERT INTO user(username , password) VALUES(? , ?)';
                    	}
                    	let addsqlparams = [req.body.username , req.body.password];
                    	connection.query(addsql , addsqlparams , function(error)
                    	{
                        	if(error)
                        	{
                            	console.log(error.message);
                        	}else{
                        		res.send("success");
                        	}
                    	});
                	}
            	}
        	});
		}

	})

	app.get('/count' , function(req , res){//分页总页数
		let addsql = "select count(*) as count from user"
		connection.query(addsql  , function(error , result){
			if(error){
				console.log(error);
			}else{
				res.send(result)
			}
		});
	})

	app.post('/page/select' , function(req , res){//分页
		let addsql = "select * from user limit 10 offset " + (0 + (req.body.currentpage-1)*10);
		let q = "select * from user limit 10 offset 0"
		console.log(addsql)
		connection.query(addsql , function(error , result){
			res.send(result)
		});
	})

	app.post('/order' , function(req , res){//订单提交
		let timestamp = new Date().getTime() + Math.ceil(Math.random()*10000);
		let addsql = "INSERT INTO orderinformation(username , fixername , address , machinename , message , name , telenumber , orderid) VALUES (? , ? , ? , ? , ? , ? , ? , ?)";
		let addsqlparams = [req.body.username , null , req.body.address , req.body.machinename , req.body.message , req.body.name , req.body.telenumber , timestamp];
		connection.query(addsql , addsqlparams , function(error){
            if(error){
                console.log(error.message);
           	}else{
              	res.send("success");
            }
       	});		
	})

	app.post('/order/information' , function(req , res){//订单信息
		console.log(req.body);
		let addsql = "SELECT * FROM orderinformation WHERE username = '" + req.body.username + "'";
		connection.query(addsql ,  function(error , result){
            if(error){
            	console.log(111)
                console.log(error.message);
           	}else{
              	res.send(result);
            }
       	});	
	})

	app.get('/' , function(req , res){//验证码请求
	})
}