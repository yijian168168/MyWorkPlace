package com.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

import javax.interceptor.Interceptor;
import java.lang.annotation.Annotation;

/**
 * 通过继承 AbstractInterceptor 类，实现Struts2自定义异常
 *
 * Created by Administrator on 2016/2/21 0021.
 */
public class Interceptor1 extends AbstractInterceptor {

    @Override
    public String intercept(ActionInvocation actionInvocation) throws Exception {
        System.out.println("Interceptor1的intercept方法。。。");
        return null;
    }
}
