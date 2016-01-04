package com.hibernate.service;

import com.hibernate.dao.User;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 * Created by Administrator on 2016/1/4 0004.
 */
public class UserService {

    @Test
    public void test() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/springConfig.xml");
        SessionFactory sessionFactoryBean = (SessionFactory) applicationContext.getBean("sessionFactory");
        HibernateTemplate hibernateTemplate = new HibernateTemplate(sessionFactoryBean);

        User user = new User();
        user.setName("qingrong");
        hibernateTemplate.save(user);
    }
}
