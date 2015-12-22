package com.controller;

import com.modles.modle9.Address;
import com.modles.modle9.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2015/7/11 0011.
 */
@Controller
@RequestMapping("/controller2")
public class Controller2 {

    @ResponseBody
    @RequestMapping("/testHttpClient")
    public  User method1( HttpServletRequest request){

        System.out.println(request.getParameter("userName"));
        System.out.println(request.getParameter("passWord"));

        Address address = new Address("changban");
        User user = new User("qingrong","24",address);

        System.out.println("Controller2里面的method1执行打印指令、、、");

        return user;
    }

}
