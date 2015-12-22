package com.modles.modle9;

/**
 * Created by Administrator on 2015/7/4 0004.
 */
public class User {

    private String name;

    private String age;

    private Address address;

    public User(String name, String age) {
        this.name = name;
        this.age = age;
    }

    public User(String name, String age, Address address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "name:"+name+"age:"+age;
    }
}
