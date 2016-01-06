package com.hibernate.test;

import com.hibernate.dao.ManyToMany.Student;
import com.hibernate.dao.ManyToMany.Teacher;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate3.HibernateTemplate;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Administrator on 2016/1/6 0006.
 */
public class ManyToMany {

    private HibernateTemplate hibernateTemplate;

    @Before
    public void init(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/springConfig.xml");
        SessionFactory sessionFactoryBean = (SessionFactory) applicationContext.getBean("sessionFactory");
        hibernateTemplate = new HibernateTemplate(sessionFactoryBean);
    }

    @Test
    public void save(){
        Teacher teacher = new Teacher();
        teacher.setTeacherName("teacher");

         Teacher teacher1 = new Teacher();
        teacher1.setTeacherName("teacher1");

        hibernateTemplate.save(teacher);
        hibernateTemplate.save(teacher1);

        Student student = new Student();
        student.setStudentName("student");
        Set<Teacher> teachers = new HashSet<Teacher>();
        teachers.add(teacher);
        student.setTeachers(teachers);

        Student student1 = new Student();
        student1.setStudentName("student1");
        Set<Teacher> teachers1 = new HashSet<Teacher>();
        teachers1.add(teacher);
        teachers1.add(teacher1);
        student1.setTeachers(teachers1);

        Student student2 = new Student();
        student2.setStudentName("student2");
        Set<Teacher> teachers2 = new HashSet<Teacher>();
        teachers2.add(teacher1);
        student2.setTeachers(teachers2);

        hibernateTemplate.save(student);
        hibernateTemplate.save(student1);
        hibernateTemplate.save(student2);

    }
}
