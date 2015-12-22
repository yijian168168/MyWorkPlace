package com.models;

/**
 * Created by Administrator on 2015/12/2 0002.
 */
public class Address {

    private int id;

    private String addressId;

    private String address;

    public Address() {
    }

    public Address(String addressId, String address) {
        this.addressId = addressId;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
