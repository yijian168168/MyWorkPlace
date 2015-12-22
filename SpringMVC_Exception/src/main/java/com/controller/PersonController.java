package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2015/11/15 0015.
 */
@Controller
public class PersonController {

    @ResponseBody
    @RequestMapping("/personController")
    public Object controller1() throws Exception {
//        return "success";
        throw new Exception("personController's Exception");
    }

    /*@ExceptionHandler(Exception.class)
    public String personException(Exception ex){
        ex.printStackTrace();
        return "success";
    }*/
}
