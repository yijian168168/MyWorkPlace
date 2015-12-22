package com.exceptionHandler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2015/11/15 0015.
 */
@ControllerAdvice
public class PersonExceptionAdvice {

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public String personException(Exception ex){
        ex.printStackTrace();
        return "success";
    }
}
