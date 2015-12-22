package com.interceptors;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2015/7/5 0005.
 */
public class Interceptor3 implements HandlerInterceptor {

    /**
     * 该方法在目标方法被调用之前调用，
     * 若返回值为true,则执行目标方法；若返回值为false,则不执行目标方法
     * 可以考虑做权限，日志，事务等。。。
     * */
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        System.out.println("Interceptor3_preHandle");
        return true;
    }

    /**
     *在调用目标方法之后，但在渲染视图之前被调用
     *
     *
     * 可以对请求域中的属性或视图坐车修改
     * */
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

        System.out.println("Interceptor3_postHandle");
    }

    /**
     *渲染视图之后被调用
     *
     *作用是释放资源
     * */
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

        System.out.println("Interceptor3_afterCompletion");
    }
}
