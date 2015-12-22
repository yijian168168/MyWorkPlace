package com.test;

import com.caucho.hessian.client.HessianProxyFactory;
import com.interf.Test1Interface;
import com.service.TestService;
import org.junit.Test;
import org.springframework.remoting.caucho.HessianProxyFactoryBean;

import java.net.MalformedURLException;

/**
 * Created by Administrator on 2015/7/2 0002.
 */
public class Test1 {

    @Test
    public void test1(){
        HessianProxyFactory factoryBean = new HessianProxyFactory();
        factoryBean.setConnectTimeout(50000);
        factoryBean.setReadTimeout(50000);
        String url = "http://127.0.0.1:8080/test1Service";
        Test1Interface testService = null;
        String returnMsg = null;
        try {
            testService = (Test1Interface)factoryBean.create(Test1Interface.class,url);
            returnMsg = testService.method1("我是测试数据");
            System.out.println(returnMsg);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


    }
}
