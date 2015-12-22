package com.mybatis;

import com.models.User;
import com.service.UserService;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2015/7/12 0012.
 */
@Configuration(value = "classPath:spring/springConfig.xml")
public class test {

    private static UserService userService;

    /**
     * 初始化Spring IOC 容器
     */
    @BeforeClass
    public static void init() {
        ApplicationContext
                context = new ClassPathXmlApplicationContext("spring/springConfig.xml");
        userService = (UserService) context.getBean("userService");
    }

    /**
     * 执行保存操作
     */
    @Test
    public void testSave() {

        User user = new User();
        user.setBirthday(new Date());
        user.setName("qingrong");
        user.setSalary(5000.00);

        userService.save(user);

    }

    /**
     * 执行删除操作
     */
    @Test
    public void testDelete() {
        int id = 1;
        userService.delete(id);
    }

    /**
     * 执行更新操作
     */
    @Test
    public void testUpdate() {
        User user = new User();
        user.setId(2);
        user.setBirthday(new Date());
        user.setName("xiaoqin");
        user.setSalary(4000.00);

        userService.update(user);
    }

    /**
     * 执行查找操作
     */
    @Test
    public void testFindByID() {
        int id = 2;
        User user = userService.findByID(id);
        System.out.println(user);
    }

    /**
     * 执行查找全部操作
     */
    @Test
    public void testFindAll() {

        List<User> users = userService.findAll();
        System.out.println(users);
    }
}
