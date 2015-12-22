package com.package2;

import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2015/12/3 0003.
 */
@Service
public class Action21 {
    public String method21(){
        System.out.println("package2--Action21--method21");
        return "success";
    }
    public String method22(){
        System.out.println("package2--Action21--method22");
        throw new NullPointerException("抛出异常测试");
    }
}
