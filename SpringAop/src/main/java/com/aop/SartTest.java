package com.aop;

import com.aop.controller.PersonController;
import com.aop.dao.Person;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2015/10/27 0027.
 */
public class SartTest {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/application-context.xml");
        PersonController personController = applicationContext.getBean(PersonController.class);

        Person person1 = new Person();
        Map<String,String> map1 = new HashMap<String, String>();
        map1.put("name","qingrong");
        map1.put("age", "24");
        personController.personService1(person1, map1);

        Person person2 = new Person();
        Map<String,String> map2 = new HashMap<String, String>();
        map2.put("name","xiaoqin");
        map2.put("age","22");
        personController.personService2(person2,map2);
    }
}
