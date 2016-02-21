package com.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

/**
 * 通过实现 Interceptor 接口，自定义Struts2异常
 *
 * Created by Administrator on 2016/2/21 0021.
 */
public class Interceptor2 implements Interceptor {

    public void destroy() {
        System.out.println("Interceptor2的destroy方法。。。");
    }

    public void init() {
        System.out.println("Interceptor2的init方法。。。");
    }

    public String intercept(ActionInvocation actionInvocation) throws Exception {
        System.out.println("Interceptor2的intercept方法。。。");
        return null;
    }
}
