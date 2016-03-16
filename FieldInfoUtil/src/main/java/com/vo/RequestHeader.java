package com.vo;

import com.util.FieldInfo;
import lombok.Data;

/**
 * Created by Administrator on 2016/3/16 0016.
 */
public class RequestHeader {

    @FieldInfo(index = "1", length = 4, align = FieldInfo.ALIGN.left, pad = "0")
    public String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
