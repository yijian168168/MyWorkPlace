package com.exceptionHandle;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * 异常统一处理类
 *
 * Created by Administrator on 2015/7/11 0011.
 */
@ControllerAdvice
public class ExceptionHandle {

    /**
     * 1、@ExceptionHandler中可以加入Exception类型的参数,该参数对应异常对象
     * 2、@ExceptionHandler中不可以传入Map对象，如果要把异常返回到页面，可以返回ModelAndView对象
     * 3、@ExceptionHandler标记的异常有优先级问题，自动选择更贴近的异常
     * 4、进行统一异常处理时，类上要用@ControllerAdvice注解
     * */
    @ExceptionHandler
    public String exceptionHandle1(Exception ex){

        System.out.println("Exception异常,exceptionHandle1-Exception:" + ex);

        return "Exception";
    }

    /**
     * 运行时异常
     *
     * */
    @ExceptionHandler({RuntimeException.class})
    public String exceptionHandle2(Exception ex){

        System.out.println("ArithmeticException异常,exceptionHandle1-Exception:" + ex);

        return "Exception";
    }

    /**
     * 处理数学异常
     *
     * 返回ModelAndView对象，可以在页面中打印异常。
     * */
    @ExceptionHandler({ArithmeticException.class})
    public ModelAndView exceptionHandle3(Exception ex){

        System.out.println("RuntimeException异常,exceptionHandle1-Exception:" + ex);

        ModelAndView modelAndView = new ModelAndView("Exception");

        modelAndView.addObject("exception",ex);

        return modelAndView;
    }
}
