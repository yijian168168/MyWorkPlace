package com.readExcel.model;

/**
 * Created by Administrator on 2015/12/11 0011.
 */
public class Order {
    /*产品代码*/
    private String productId;
    /*产品名称*/
    private String productName;
    /*规格*/
    private String size;
    /*批发价*/
    private String price;
    /*数量*/
    private String num;
    /*折扣*/
    private String discount;
    /*金额*/
    private String sum;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }
}
