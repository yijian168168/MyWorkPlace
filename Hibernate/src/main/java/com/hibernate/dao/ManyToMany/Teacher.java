package com.hibernate.dao.ManyToMany;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Administrator on 2016/1/6 0006.
 */
@Entity
public class Teacher {

    @Id
    @GeneratedValue
    private int id;

    private String teacherName;

    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE},
            targetEntity = Student.class,
            mappedBy = "teachers")
    private Set<Student> students;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }
}
