package com.liu.fixweb.dao;

import com.liu.fixweb.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserDaoTest {
    @Autowired
    UserDao userDao;
    @Test
    public void find(){
        User user = new User();
        user.setUsername("1111");
        System.out.println(userDao.countuser());
    }

}