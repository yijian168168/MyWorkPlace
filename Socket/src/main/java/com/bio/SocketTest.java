package com.bio;

import org.junit.Test;

import java.util.Date;

/**
 * 下面测试一下大量请求的场景下，Socket服务端处理的效率。
 *
 * Created by Administrator on 2016/1/6 0006.
 */
public class SocketTest {

    /**
     * 第一种方式：通过for循环来启动5000个Socket客户端，发送请求，代码如下所示：
     *
     * 经过测试，大约需要9864ms，大概接近10s。
     * */
    @Test
    public void test1() {
        int n = 5000;
        StringBuffer data = new StringBuffer();
        Date start = new Date();
        for (int i = 0; i < n; i++) {
            data.delete(0, data.length());
            data.append("I am the client ").append(i).append(".");
            SimpleBioTcpClient client = new SimpleBioTcpClient("localhost", 1983);
            client.send(data.toString().getBytes());
        }
        Date end = new Date();
        long cost = end.getTime() - start.getTime();
        System.out.println(n + " requests cost " + cost + " ms.");
    }


}
