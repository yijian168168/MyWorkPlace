package com.hibernate.test;

import com.hibernate.dao.oneToOne.IdCard;
import com.hibernate.dao.oneToOne.Person;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 * Created by Administrator on 2016/1/6 0006.
 */
public class OneToOneTest {

    private HibernateTemplate hibernateTemplate;

    @Before
    public void init(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/springConfig.xml");
        SessionFactory sessionFactoryBean = (SessionFactory) applicationContext.getBean("sessionFactory");
        hibernateTemplate = new HibernateTemplate(sessionFactoryBean);
    }

    @Test
    public void save(){
        Person person = new Person();
        IdCard idCard = new IdCard();

        idCard.setCardNum("00001");
        person.setName("haha");
        person.setIdCard(idCard);

        hibernateTemplate.save(person);
        System.out.println("person 保存成功");
    }

    @Test
    public void get(){
        Person person = hibernateTemplate.get(Person.class,1);
        System.out.println("获取Person成功" + person);
    }
}
