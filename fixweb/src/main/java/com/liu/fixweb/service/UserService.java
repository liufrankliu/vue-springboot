package com.liu.fixweb.service;

import com.github.pagehelper.Page;
import com.liu.fixweb.domain.User;
import com.liu.fixweb.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public User find(String username){
        return userDao.find(username);
    }

    public void insertUser(User user){
        userDao.insertUser(user);
    }

    public List<User> getUserList(){
        return userDao.getUserList();
    }

    public int countuser(){
        return userDao.countuser();
    }
}
