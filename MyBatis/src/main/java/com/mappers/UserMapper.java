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
     * �����һ������
     * */
    public void save(User user);

    /**
     * ����ǻ�������
     * */
    public void delete(int id);

    public void update(User user);

    /**
     * ���ز�����һ������
     * */
    public User findByID(int id);

    /**
     * ���ز�����һ������
     * */
    public List<User> findAll();

    /**
     * �������������
     * */
    public void addAddress(@Param("user") User user,@Param("address")Address address);

    public List<User> findByAddress(Address address);

}
