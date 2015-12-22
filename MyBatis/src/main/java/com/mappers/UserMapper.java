package com.mappers;

import com.models.Address;
import com.models.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2015/7/12 0012.
 */
@Service
public interface UserMapper {

    /**
     * 入参是一个对象
     * */
    public void save(User user);

    /**
     * 入参是基本类型
     * */
    public void delete(int id);

    public void update(User user);

    /**
     * 返回参数是一个对象
     * */
    public User findByID(int id);

    /**
     * 返回参数是一个集合
     * */
    public List<User> findAll();

    /**
     * 入参是两个参数
     * */
    public void addAddress(@Param("user") User user,@Param("address")Address address);

    public List<User> findByAddress(Address address);

}
