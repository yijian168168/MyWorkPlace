package com.hibernate.test;

import com.hibernate.dao.oneToMany.Address;
import com.hibernate.dao.oneToMany.Street;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 * Created by Administrator on 2016/1/6 0006.
 */
public class OneToManyTest {

    private HibernateTemplate hibernateTemplate;

    @Before
    public void init(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/springConfig.xml");
        SessionFactory sessionFactoryBean = (SessionFactory) applicationContext.getBean("sessionFactory");
        hibernateTemplate = new HibernateTemplate(sessionFactoryBean);
    }

    @Test
    public void save(){

        Street street = new Street();

        street.setStreetName("大庆街");

        hibernateTemplate.save(street);

        Address address = new Address();
        address.setAddressNo("a0002");
//        Street street1 = hibernateTemplate.get(Street.class,1);
        address.setStreet(street);
        hibernateTemplate.save(address);
        System.out.println("保存完毕");
    }

    @Test
    public void get(){
        Address address = hibernateTemplate.get(Address.class,2);

        System.out.println(address);
    }
}
