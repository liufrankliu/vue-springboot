package com.liu.fixweb.controller;

import com.liu.fixweb.domain.Order;
import com.liu.fixweb.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/order")
    @ResponseBody
    public void insertorder(@RequestBody Order order){
        System.out.println(order);
        //orderService.insertOrder(order);
    }
}
