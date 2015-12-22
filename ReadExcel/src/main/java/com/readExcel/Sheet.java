package com.readExcel;

import com.readExcel.model.Order;

import java.util.List;

/**
 * Created by Administrator on 2015/12/11 0011.
 */
public class Sheet {

    //客户名称：
    private String customerName;

    //店铺号
    private String customerId;

    //单号
    private String orderId;

    //电话
    private String phone;

    //地址
    private String address;

    //送货日期
    private String sendDate;

    //备注
    private String reverse;

    //实收
    private String allPrice;

    //数量合计
    private String allNum;

    //金额合计
    private String allPrice2;

    //快递费
    private String emailFei;

    private List<Order> orders;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSendDate() {
        return sendDate;
    }

    public void setSendDate(String sendDate) {
        this.sendDate = sendDate;
    }

    public String getReverse() {
        return reverse;
    }

    public void setReverse(String reverse) {
        this.reverse = reverse;
    }

    public String getAllPrice() {
        return allPrice;
    }

    public void setAllPrice(String allPrice) {
        this.allPrice = allPrice;
    }

    public String getAllNum() {
        return allNum;
    }

    public void setAllNum(String allNum) {
        this.allNum = allNum;
    }

    public String getAllPrice2() {
        return allPrice2;
    }

    public void setAllPrice2(String allPrice2) {
        this.allPrice2 = allPrice2;
    }

    public String getEmailFei() {
        return emailFei;
    }

    public void setEmailFei(String emailFei) {
        this.emailFei = emailFei;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
