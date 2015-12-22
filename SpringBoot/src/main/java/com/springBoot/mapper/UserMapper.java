package com.springBoot.mapper;

import com.springBoot.dao.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2015/7/12 0012.
 */
@Service
public interface UserMapper {

    public void save(User user);

    public void delete(int id);

    public void update(User user);

    public User findByID(int id);

    public List<User> findAll();

}
