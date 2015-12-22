package com.controller;

import com.model.User1;
import com.model.User2;
import com.model.User3;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;

/**
 * Created by Administrator on 2015/7/5 0005.
 */
@Controller
@RequestMapping("/controller1")
public class Controller1 {

    /**
     * 数据类型转换,
     */
    @RequestMapping("/method1")
    public String method1(@RequestParam("form1_input1") User1 user) {

        System.out.println("controller1/method1测试返回结果：" + user);
        return "success";
    }

    /**
     * 数据格式化，如果在格式化过程中出现异常，SpringMVC会将异常信息放到BindingResult类型的对象中，
     */
    @RequestMapping("/method2")
    public String method2(User2 user2, BindingResult bindingResult) {

        if (bindingResult.getErrorCount() > 0) {

            System.out.println("格式化过程中出错，异常如下。。。");

            for (FieldError error : bindingResult.getFieldErrors()) {
                System.out.println(error.getField() + ":" + error.getDefaultMessage());
            }

        }
        System.out.println("controller1/method2测试返回结果：" + user2);

        return "success";
    }

    /**
     * 数据校验,
     * 使用JSR 303 标准 (具体可百度)
     * 加入Hibernate validator 验证框架
     * 在bean的属性上加上相应的注解
     * 在目标方法bean类型的前面加上@valid 注解
     * SpringMVC配置文件中加上<mvc:annotation-driven/>注解
     */
    @RequestMapping("/method3")
    public String method3(@Valid User3 user3, BindingResult bindingResult) {

        if (bindingResult.getErrorCount() > 0) {

            System.out.println("数据校验过程中出错，异常如下。。。");

            for (FieldError error : bindingResult.getFieldErrors()) {
                System.out.println(error.getField() + ":" + error.getDefaultMessage());
            }

        }
        System.out.println("controller1/method3测试返回结果：" + user3);

        return "success";
    }

    /**
     * 文件上传
     * */
    @RequestMapping("/method4")
    public String method4(@RequestParam("desc") String desc, @RequestParam("file") MultipartFile file) {

        try {
            System.out.println("desc:" + desc);
            System.out.println("OriginalFilename:" + file.getOriginalFilename());
            System.out.println("InputStream:" + file.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "success";
    }

    /**
     * 异常拦截器测试
     * */
    @RequestMapping("/method5")
    public String method5(@RequestParam("i") int i) {

        System.out.println("result=" + 10/i);

        return "success";
    }

}
