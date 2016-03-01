package com.dubbo.customer;

import com.dubbo.interf.MyDubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2016/3/1 0001.
 */
@Controller
public class ServiceController {

    @Autowired
    private MyDubboService myDubboService;

    @ResponseBody
    @RequestMapping("callService")
    public String callService(String requestMsg) {
        System.out.println("接收到请求：" + requestMsg);
        String responseMsg = myDubboService.service(requestMsg);
        System.out.println("返回响应：" + responseMsg);
        return responseMsg;

    }
}
