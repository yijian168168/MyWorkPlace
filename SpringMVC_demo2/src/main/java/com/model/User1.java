package com.model;

/**
 * Created by Administrator on 2015/7/5 0005.
 */
public class User1 {

    private String name;

    private String email;

    private String identify;

    public User1() {
    }

    public User1(String name, String email, String identify) {
        this.name = name;
        this.email = email;
        this.identify = identify;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdentify() {
        return identify;
    }

    public void setIdentify(String identify) {
        this.identify = identify;
    }

    @Override
    public String toString() {
        return "User1{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", identify='" + identify + '\'' +
                '}';
    }
}
