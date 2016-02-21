package com.exception;

/**
 * Created by Administrator on 2016/2/21 0021.
 */
public class ExceptionTest {

    public String testNullPointException(){
        throw new NullPointerException("空指针异常测试");
    }

    public String testGlobalException() throws Exception {
        throw new Exception("全局异常测试");
    }
}
