package com.hibernate.dao.oneToMany;

import javax.persistence.*;

/**
 * Created by Administrator on 2016/1/6 0006.
 */
@Entity
public class Address {

    @Id
    @GeneratedValue
    private int id;

    private String addressNo;

    @ManyToOne
    @JoinColumn(name = "street_id")
    private Street street;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddressNo() {
        return addressNo;
    }

    public void setAddressNo(String addressNo) {
        this.addressNo = addressNo;
    }

    public Street getStreet() {
        return street;
    }

    public void setStreet(Street street) {
        this.street = street;
    }
}
