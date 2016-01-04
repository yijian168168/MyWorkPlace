package com.hibernate.dao;

import javax.persistence.*;

/**
 * Created by Administrator on 2016/1/4 0004.
 */
@Entity
public class User {

    @Id
    @GeneratedValue
    private int id;

    private String name;

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
}
