package com.liu.fixweb.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.liu.fixweb.domain.User;
import com.liu.fixweb.service.KaptchaService;
import com.liu.fixweb.service.UserService;
import lombok.Data;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;

@Controller
public class KaptchaController {
    private Userfalg userfalg;

    @Autowired
    private UserService userService;

    @Resource
    private DefaultKaptcha captchaProducer;

    public static final String LOGIN_VALIDATE_CODE = "login_validate_code";


    @RequestMapping("/loginValidateCode")
    public void loginValidateCode(HttpServletRequest request , HttpServletResponse response)throws Exception{
         //System.out.println(KaptchaService.validateCode(request,response,captchaProducer,LOGIN_VALIDATE_CODE));
         KaptchaService.validateCode(request,response,captchaProducer,LOGIN_VALIDATE_CODE);
    }

    @PostMapping("/checkloginValidcode")//登陆验证
    @ResponseBody
    public String checkregistvalidcide(HttpServletRequest request, @RequestBody Userfalg userfalg){
        String loginValidateCode = request.getSession().getAttribute(LOGIN_VALIDATE_CODE).toString();
        User user = new User();
        user = userService.find(userfalg.getUsername());
        if (loginValidateCode.equals(userfalg.getValidvode()) && userfalg.getPassword().equals(user.getPassword()) && userfalg.getUsername().equals(user.getUsername())){
            return "success";
        }else{
            return "fail";
        }
    }

    @PostMapping("/checkregistValidateCode")//注册验证
    @ResponseBody
    public String checkLoginValidateCode(HttpServletRequest request, @RequestBody Userfalg userfalg){
        String loginValidateCode = request.getSession().getAttribute(LOGIN_VALIDATE_CODE).toString();
        //HashMap<String,Object> map = new HashMap<String,Object>();
        System.out.println(userfalg);
        User user = new User();
        user.setPassword(userfalg.getPassword());
        user.setUsername(userfalg.getUsername());
        //System.out.println(loginValidateCode);
        if (loginValidateCode.equals(userfalg.getValidvode())){
            userService.insertUser(user);
            return "success";
        }else{
            return "fail";
        }
//        if(loginValidateCode == null){
//            //map.put("status",null);//验证码过期
//        }else if(loginValidateCode.equals(validateCode)){
//            //map.put("status",true);//验证码正确
//        }else if(!loginValidateCode.equals(validateCode)){
//            //map.put("status",false);//验证码不正确
//        }
        //map.put("code",200);
        //return map;
    }

}

@Data
class Userfalg{
    private String username;
    private String password;
    private String validvode;
}
