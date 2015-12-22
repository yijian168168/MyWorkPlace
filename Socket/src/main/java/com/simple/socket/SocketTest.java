package com.simple.socket;

import org.junit.Test;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by Administrator on 2015/11/29 0029.
 */
public class SocketTest {

    @Test
    public void startServer5566(){
        //启动服务器 5566端口
        SocketServer socketServer = new SocketServer(5566);
        socketServer.startServer();
    }
    @Test
    public void test1() throws IOException {
        //向服务器发送信息
        SocketClient socket1 = new SocketClient("127.0.0.1",5566);
        socket1.sendMsg("socket请求信息1");
        socket1.sendMsg("socket请求信息2");
    }
}
