package com.service;

import com.interf.Test1Interface;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2015/7/2 0002.
 */
@Service
public class TestService implements Test1Interface {

    public String method1(String msg) {

        System.out.println("TestService-method1,接收到消息：" + msg);

        return "TestService-method123";
    }
}
