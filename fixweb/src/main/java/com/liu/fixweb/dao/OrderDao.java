package com.liu.fixweb.dao;

import com.liu.fixweb.domain.Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface OrderDao {
    @Insert("insert into order values(#{username} , #{address} , #{machine} , #{message})")
    public void insertOrder(Order order);
}
