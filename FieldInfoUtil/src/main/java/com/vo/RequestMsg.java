package com.vo;

import com.util.FieldInfo;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Administrator on 2016/3/16 0016.
 */
public class RequestMsg extends RequestHeader {

    @FieldInfo(index = "1",length = 8,align = FieldInfo.ALIGN.left, pad = "*")
    private String name;

    @FieldInfo(index = "2",length = 8,align = FieldInfo.ALIGN.right, pad = " ")
    private String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
