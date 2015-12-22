package com.package1;

import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2015/12/3 0003.
 */
@Service
public class Action1 {

    public String method1(){
        System.out.println("package1---Action1---method1");
        return "success";
    }

    public String method2(){
        System.out.println("package1---Action1---method2");
        return "success";
    }
}
