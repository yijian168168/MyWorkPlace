package com.controller;

import com.modles.modle9.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Map;

/**
 * Created by Administrator on 2015/7/1 0001.
 */
@Controller
@SessionAttributes("user2")
@RequestMapping("/controller1")
public class Controller1 {


    /**
     * SpringMvc第一次请求,测试ResuestMapping的value属性
     */
    @RequestMapping("/method1")
    public String method1() {

        System.out.println("Controller1里面的method1执行打印指令、、、、");

        return "success";
    }

    /**
     * 请求表单1，测试ResuestMapping的method属性,限定POST请求
     */
    @RequestMapping(value = "/method2", method = RequestMethod.POST)
    public String method2() {

        System.out.println("Controller1里面的method2执行打印指令、、、、");

        return "success";
    }

    /**
     * params 和 headers 支持简单表达式 •
     * param1:  请求必须包含名为param1的请求参数 –
     * !param1: 请求必须不包含名为param1的请求参数 –
     * param1 != value1:  若包含param1参数，其值不能为value1
     * {“param1=value1”, “param2”}: 请求必 包含名为param1和param2两个请求参数且 param1 参数 值必为 value1
     */
    @RequestMapping(value = "/method3", params = {"name=xiaoqin"})
    public String method3() {

        System.out.println("Controller1里面的method3执行打印指令,name属性为xiaoqin");

        return "success";
    }

    /**
     * 同一个映射下面可以有多个方法去实现，根据参数属性选择不同的方法去执行
     */
    @RequestMapping(value = "/method3", params = {"name=qingrong"})
    public String method33() {

        System.out.println("Controller1里面的method3执行打印指令，name属性为qingrong");

        return "success";
    }

    @RequestMapping(value = "/method4", headers = {"Accept-Language=zh-CN,zh;q=0.8"})
    public String method4() {

        System.out.println("Controller1里面的method4执行打印指令、、、、");

        return "success";
    }

    /**
     * 利用@PathVariable 注解,映射URL绑定的占位符
     */
    @RequestMapping("/method5/{name}")
    public String method5(@PathVariable("name") String name) {

        System.out.println("Controller1里面的method5执行打印指令,@PathVariable从URL中映射的name属性的值为：" + name);

        return "success";
    }

    /**
     * Spring MVC通过分析处理方法的签名将 HTTP  请求信息绑定到处理方法的相应入参中。
     * Spring MVC 对控制器处方法的签名限制是很宽松的，几乎可以按喜欢任何方式对方法进签名。
     * 必要时可以对方法及方法入参标注相应的注解
     *
     * @PathVariable、@RequestParam 、@RequestHeader 、等
     * SpringMVC框架会将 HTTP 请求信息绑定到相应的方法入参中并根据方法回值类型做出相应后续处理
     * <p/>
     * value参数名 –
     * required是否必需。 默认值为true, 若为true请求参数中必须包含对应参数不存在将抛出异常
     * defaultValue 默认值，如果属性为空则取默认值
     */
    @RequestMapping("/method6")
    public String method6(@RequestParam("name") String name, @RequestParam(value = "age", required = true, defaultValue = "20") String age) {

        System.out.println("Controller1里面的method6执行打印指令,@RequestParam从请求参数中获取的name属性的值为：" + name + "，age属性为：" + age);

        return "success";
    }

    /**
     * 使用@RequestHeader 绑定请求报头属性值
     * <p/>
     * value参数名 –
     * required是否必需。 默认值为true, 若为true请求参数中必须包含对应参数不存在将抛出异常
     * defaultValue 默认值，如果属性为空则取默认值
     */
    @RequestMapping("/method7")
    public String method7(@RequestHeader(value = "Accept-Language", required = true, defaultValue = "zh-CN,zh;q=0.8,en;q=0.6") String language) {

        System.out.println("Controller1里面的method7执行打印指令,@RequestHeader从请求头中获取的Accept-Language属性的值为：" + language);

        return "success";
    }

    /**
     * 使用@CookieValue 绑定请求Cookie的属性值
     * <p/>
     * value参数名 –
     * required是否必需。 默认值为true, 若为true请求参数中必须包含对应参数不存在将抛出异常
     * defaultValue 默认值，如果属性为空则取默认值
     */
    @RequestMapping("/method8")
    public String method8(@CookieValue(value = "JSESSIONID", required = true, defaultValue = "2895F27E8274421021ACC8B84AFC2F2C") String sessionID) {

        System.out.println("Controller1里面的method8执行打印指令,使用@CookieValue 绑定请求Cookie的属性值为：" + sessionID);

        return "success";
    }

    /**
     * 使用POJO 对象绑定请求参数值
     */
    @RequestMapping("/method9")
    public String method9(User user) {

        System.out.println("Controller1里面的method9执行打印指令,使用User对象绑定请求参数的name属性值为：" + user.getName());
        System.out.println("Controller1里面的method9执行打印指令,使用User对象绑定请求参数的age属性值为：" + user.getAge());
        System.out.println("Controller1里面的method9执行打印指令,使用User对象绑定请求参数的address.street属性值为：" + user.getAddress().getStreet());

        return "success";
    }

    /**
     * 使用Servlet API  作为入参
     * <p/>
     * SpringMVC可以接受以下类型的 ServletAPI 类型参数
     * HttpServletRequest •
     * HttpServletResponse •
     * HttpSession •
     * java.security.Principal •
     * Locale •
     * InputStream •
     * OutputStream •
     * Reader •
     * Writer
     */
    @RequestMapping("/method10")
    public String method10(HttpServletRequest request, HttpServletResponse response) {

        System.out.println(request.getQueryString());
        System.out.println(request.getRequestURI());
        System.out.println("Controller1里面的method10执行打印指令,使用User对象绑定请求参数的name属性值为：" + request.getParameter("name"));
        System.out.println("Controller1里面的method10执行打印指令,使用User对象绑定请求参数的age属性值为：" + request.getParameter("age"));
        System.out.println("Controller1里面的method10执行打印指令,使用User对象绑定请求参数的address.street属性值为：" + request.getParameter("address.street"));

        return "success";
    }

    /**
     * 目标方法可以添加Map 类型的入参，SpringMVC会自动将此map对象放入Request请求域中。
     */

    @RequestMapping("/method11")
    public String method11(Map<String, Object> map) {

        map.put("names", Arrays.asList("qingrong", "xiaoqin"));

        User user1= new User("qingrong","24");
        map.put("user1",user1);
        System.out.println("Controller1里面的method11执行打印指令、、、");

        return "success";
    }

    /**
     * 希望在多个请求之间共用某个模型属性数据，则可以在控制器类上标注一个 @SessionAttributes 标签,
     * Spring MVC将在模型中对应属性 暂存到 HttpSession 中。
     * @SessionAttributes 除了可以通过属性名指定放到会话中属性外，还可以通过模型属性对类型指定
     * 哪些模型属性放到会话中
     * @SessionAttributes(types=User.class) 会将含模型中所有类型为 User.class 属性添加到会话中。
     * @SessionAttributes(value={“user1”, “user2”}) –
     * @SessionAttributes(types={User.class, Dept.class}) –
     * @SessionAttributes(value={“user1”, “user2”},types={Dept.class})
     * */
    @RequestMapping("/method12")
    public String method12(Map<String, Object> map) {

        User user2 = new User("xiaoqin","22");
        map.put("user2",user2);
        System.out.println("Controller1里面的method12执行打印指令、、、");

        return "success";
    }

    /**
     * 一情况下控制器方法回字串类型的值会当成辑视图名处，
     * 如果回字串中 带 forward:  或 redirect: 前缀时
     * SpringMVC 会对他们进特殊处理，将 forward: 和 redirect: 当成指其后字串作为 URL 来处理
     * redirect:success.jsp 会完成一个到 success.jsp 定向操作 –
     * forward:success.jsp 会完成一个到 success.jsp  转发操作
     */

    @RequestMapping("/method13")
    public String method13() {

        System.out.println("Controller1里面的method13执行打印指令、、、");

        //return "forward:/WEB-INF/views/Exception.jsp";//转发到一个页面

        return "forward:/controller1/method12";//转发到另一个Action
    }

    @RequestMapping("/method14")
    public String method14() {

        System.out.println("Controller1里面的method14执行打印指令、、、");

        //return "redirect:/index.jsp";//重定向到一个页面

        return "redirect:/controller1/method12";//重定向到另一个Action
    }

}
