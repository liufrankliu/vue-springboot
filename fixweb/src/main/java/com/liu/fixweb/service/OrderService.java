package com.liu.fixweb.service;

import com.liu.fixweb.dao.OrderDao;
import com.liu.fixweb.domain.Order;
import org.apache.ibatis.annotations.Insert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    private OrderDao orderDao;

    public void insertOrder(Order order){
         orderDao.insertOrder(order);
    }
}
