package com.model;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import java.util.Date;

/**
 * Created by Administrator on 2015/7/5 0005.
 */
public class User2 {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthDate;

    @NumberFormat(pattern = "#,###,###.##")
    private Float salary;

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Float getSalary() {
        return salary;
    }

    public void setSalary(Float salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "User2{" +
                "birthDate=" + birthDate +
                ", salary=" + salary +
                '}';
    }
}
