package com.hibernate.dao.ManyToMany;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Administrator on 2016/1/6 0006.
 */
@Entity
public class Student {

    @Id
    @GeneratedValue
    private int id;

    private String studentName;

    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE}, targetEntity = Teacher.class)
    @JoinTable(name = "student_teacher",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "teacher_id"))
    private Set<Teacher> teachers;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Set<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(Set<Teacher> teachers) {
        this.teachers = teachers;
    }
}
