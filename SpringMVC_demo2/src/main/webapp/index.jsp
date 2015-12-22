<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<h2>Hello World!</h2>

1、数据转换测试，输入格式：name-email-identify
<form action="controller1/method1" method="post">
    User: <input type="text" name="form1_input1">
    <input type="submit" value="form1_submit">
</form>

2、数据格式化，输入格式，birthDate:yyyy-MM-dd;salary:#,###,###,##
<form action="controller1/method2" method="post">
    birthDate: <input type="text" name="birthDate">,
    salary: <input type="text" name="salary">
    <input type="submit" value="form2_submit">
</form>

3、数据校验，正确输入格式，birthDate:yyyy-MM-dd;email:abc@dd.com
<form action="controller1/method3" method="post">
    name: <input type="text" name="name">,
    birthDate: <input type="text" name="birthDate">,
    email: <input type="text" name="email">,
    <input type="submit" value="form3_submit">
</form>

4、测试文件上传
<form action="controller1/method4" method="post" enctype="multipart/form-data">
    desc: <input type="text" name="desc">,
    file: <input type="file" name="file">,
    <input type="submit" value="form4_submit">
</form>

5.异常测试
<a href="/controller1/method5?i=1">异常测试</a>
</body>
</html>
