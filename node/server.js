const express = require("express");
const bodyParser = require('body-parser');
const todecontroller = require('./controllers/todocontro.js');
const session = require('express-session');

const app = express();

app.set('trust proxy', 1) // trust first proxy

app.use(session({
  secret: 'keyboard cat',
  resave: false,
  saveUninitialized: true,
  name: 'sessionId'
}))

app.use(bodyParser.json());

app.set('view engine' , 'ejs');

app.use(express.static('./public'));


app.all('*', function (req, res, next) {
  res.header("Access-Control-Allow-Origin", req.header("Origin"));
  res.header('Access-Control-Allow-Headers', 'Content-Type, Content-Length, Authorization, Accept, X-Requested-With , yourHeaderFeild');
  res.header("Access-Control-Allow-Methods", "PUT,POST,GET,DELETE,OPTIONS");
  res.header("Access-Control-Allow-Credentials", "true")
  //res.header("X-Powered-By", ' 3.2.1')
  res.header("Content-Type", "application/json;charset=utf-8");
  res.header("XDomainRequestAllowed","1")
  next();
});

todecontroller(app);

app.listen(8181);
console.log("正在监听端口8181");