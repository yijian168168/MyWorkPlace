package com.models;

import java.util.Date;

/**
 * Created by Administrator on 2015/7/12 0012.
 */
public class User {

    private int id;

    private String name;

    private Date birthday;

    private Double salary;

    private String addressId;

    public User() {
    }

    public User(String name, Date birthday, Double salary, String addressId) {
        this.name = name;
        this.birthday = birthday;
        this.salary = salary;
        this.addressId = addressId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthday=" + birthday +
                ", salary=" + salary +
                ", addressId='" + addressId + '\'' +
                '}';
    }
}
