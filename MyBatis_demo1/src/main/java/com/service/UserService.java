package com.service;

import com.mappers.UserMapper;
import com.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2015/7/12 0012.
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public void save(User user){
        System.out.println("save。。。");
        userMapper.save(user);
    }

    public void delete(int id){
        System.out.println("delete。。。");
        userMapper.delete(id);
    }

    public void update(User user){
        System.out.println("update。。。");
        userMapper.update(user);
    }

    public User findByID(int id){
        System.out.println("findByID。。。");
        User user = userMapper.findByID(id);
        return user;
    }

    public List<User> findAll(){
        System.out.println("findAll。。。");
        List<User> users = userMapper.findAll();
        return users;
    }
}
