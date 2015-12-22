<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<h1><center>我是初始化页面</center></h1>

<table align="center" border="2" width="1000px">
    <tr >
        <td width="750px">1、SpringMvc第一次请求,测试ResuestMapping的value属性</td>
        <td><a href="controller1/method1">controller1_method1请求</a></td>
    </tr>
    <tr >
        <td width="750px">2、请求表单1，测试ResuestMapping的method属性,限定POST请求</td>
        <td valign="center">
            <br><form action="controller1/method2" name="form1" method="post">
            <input type="submit" name="form1_submit1" value="controller1_method2请求">
        </form>
        </td>
    </tr>
    <tr >
        <td width="750px">3、测试ResuestMapping的params属性</td>
        <td><a href="controller1/method3?name=qingrong">controller1_method3请求</a></td>
    </tr>
    <tr >
        <td width="750px">4、测试ResuestMapping的headers属性</td>
        <td><a href="controller1/method4">controller1_method4请求</a></td>
    </tr>
    <tr >
        <td width="750px">5、利用@PathVariable 注解,映射URL绑定的占位符</td>
        <td><a href="controller1/method5/qingrong">controller1_method5请求</a></td>
    </tr>
    <tr >
        <td width="750px">6、使用@RequestParam 绑定请求参数值</td>
        <td><a href="controller1/method6?name=qingrong&age=24">controller1_method6请求</a></td>
    </tr>
    <tr >
        <td width="750px">7、使用@RequestHeader 绑定请求报头属性值</td>
        <td><a href="controller1/method7">controller1_method7请求</a></td>
    </tr>
    <tr >
        <td width="750px">8、使用@CookieValue 绑定请求Cookie的属性值</td>
        <td><a href="controller1/method8">controller1_method8请求</a></td>
    </tr>
    <tr >
        <td width="750px">9、使用POJO 对象绑定请求参数值</td>
        <td><a href="controller1/method9?name=qingrong&age=24&address.street=cangban">controller1_method9请求</a></td>
    </tr>
    <tr >
        <td width="750px">10、使用Servlet API  作为入参</td>
        <td><a href="controller1/method9?name=qingrong&age=24&address.street=cangban">controller1_method10请求</a></td>
    </tr>
    <tr >
        <td width="750px">11、目标方法可以添加Map 类型的入参，SpringMVC会自动将此map对象放入Request请求域中</td>
        <td><a href="controller1/method11">controller1_method11请求</a></td>
    </tr>
    <tr >
        <td width="750px">12、目标方法可以添加Map 类型的入参，SpringMVC会自动将此map对象放入Request请求域中,
            希望在多个请求之间共用某个模型属性数据，则可以在控制器类上标注一个 @SessionAttributes 标签</td>
        <td><a href="controller1/method12">controller1_method12请求</a></td>
    </tr>
    <tr >
        <td width="750px">13、SpringMVC转发操作</td>
        <td><a href="controller1/method13">controller1_method13请求</a></td>
    </tr>
    <tr >
        <td width="750px">14、SpringMVC重定向操作</td>
        <td><a href="controller1/method14">controller1_method14请求</a></td>
    </tr>
    <tr >
        <td width="750px">15、SpringMVC注解@ResponseBody的使用</td>
        <td><a href="controller2/testHttpClient">controller2_method1请求</a></td>
    </tr>

</table>

</body>
</html>