package com.dubbo.impl;

import com.dubbo.interf.MyDubboService;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2016/3/1 0001.
 */
@Service
public class MyDubboServiceImpl implements MyDubboService {

    public String service(String request){
        System.out.println("服务端收到请求：" + request);
        return "服务端响应信息";
    }
}
