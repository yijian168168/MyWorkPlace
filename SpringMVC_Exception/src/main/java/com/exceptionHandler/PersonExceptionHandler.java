package com.exceptionHandler;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.annotation.Annotation;

/**
 * Created by Administrator on 2015/11/15 0015.
 */
//@Service("exceptionHandler")
public class PersonExceptionHandler implements HandlerExceptionResolver{

    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        e.printStackTrace();
        try {
            PrintWriter printWriter = httpServletResponse.getWriter();
            printWriter.write("请求出现异常");
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        System.out.println("SpringMVC 拦截器拦截到异常。。。。");
        return new ModelAndView();
    }
}
