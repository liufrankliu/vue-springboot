package com.liu.fixweb.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.liu.fixweb.domain.User;
import com.liu.fixweb.service.UserService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired

    @RequestMapping("/")
    @ResponseBody
    public String hello(){
        return "hello";
    }

//    @RequestMapping("/all")
//    @ResponseBody
//    public User all(){
//        //System.out.println(userService.findAll());
//        //return userService.findAll();
//    }

    @PostMapping("/page/select")
    @ResponseBody
    public List getUserList(@RequestBody CurrentPage currentpage){
        //System.out.println(currentpage.getCurrentpage());
        PageHelper.startPage(currentpage.getCurrentpage() , 10);
        List<User>  userList= userService.getUserList();
        PageInfo pageInfo = new PageInfo(userList);
        //System.out.println(pageInfo.getPageNum());
        //System.out.println("每页显示数量：" + pageInfo.getPageSize());
        return pageInfo.getList();
    }

    @PostMapping("/insert")
    @ResponseBody
    public void insertUser(@RequestBody User user){
        userService.insertUser(user);
    }

    @RequestMapping("/count")
    @ResponseBody
    public int countuser(){
        return userService.countuser();
    }
}
@Data
class CurrentPage{
    public int currentpage;
}
