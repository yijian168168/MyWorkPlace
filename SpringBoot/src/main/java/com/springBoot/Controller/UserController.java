package com.springBoot.Controller;

import com.springBoot.Service.UserService;
import com.springBoot.dao.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * Created by Administrator on 2015/10/24 0024.
 */
@Controller
@RequestMapping("userController")
public class UserController {

    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping("/insert")
    public String insertUser(){
        System.out.println("insert,执行添加User操作。");
        User user = new User("hahqa",new Date(),5000.00);
        userService.save(user);
        return "success";
    }
}
