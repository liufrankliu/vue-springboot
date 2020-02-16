package com.liu.fixweb.dao;

import com.github.pagehelper.Page;
import com.liu.fixweb.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserDao {

    @Select("select username , password from user where username = #{username}")
    public User find(String username);

    @Insert("insert into user values(#{username},#{password})")
    public void insertUser(User user);

    @Select("SELECT * FROM USER")
    List<User> getUserList();

    @Select("select count(*) from USER")
    int countuser();
}
