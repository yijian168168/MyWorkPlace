package com.bio;

/**
 * 基于BIO的Socket服务端进程
 *
 * BIO通信改进
 * 通过上面的测试我们可以发现，在Socket服务端对来自客户端的请求进行处理时，会发生阻塞，严重地影响了能够并发处理请求的效率。实际上，在Socket服务端接收来自客户端连接能力的范围内，可以将接收请求独立出来，从而在将处理请求独立粗话来，通过一个请求一个线程处理的方式来解决上述问题。这样，服务端是多处理线程对应客户端多请求，处理效率有一定程度的提高。
 * 下面，通过单线程接收请求，然后委派线程池进行多线程并发处理请求：
 * 可见，这种改进方式增强服务端处理请求的并发度，但是每一个请求都要由一个线程去处理，大量请求造成服务端启动大量进程进行处理，也是比较占用服务端资源的。
 * Created by Administrator on 2016/1/6 0006.
 */

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SocketServer extends Thread {

    /** 服务端口号 */
    private int port = 8888;
    /** 为客户端分配编号  */
    private static int sequence = 0;
    /** 处理客户端请求的线程池 */
    private ExecutorService pool;

    public SocketServer(int port, int poolSize) {
        this.port = port;
        this.pool = Executors.newFixedThreadPool(poolSize);
    }

    @Override
    public void run() {
        Socket socket = null;
        int counter = 0;
        try {
            ServerSocket serverSocket = new ServerSocket(this.port);
            boolean flag = false;
            Date start = null;
            while(true) {
                socket = serverSocket.accept(); // 监听
                // 有请求到来才开始计时
                if(!flag) {
                    start = new Date();
                    flag = true;
                }
                // 将客户端请求放入线程池处理
                pool.execute(new RequestHandler(socket));
//                if(++counter==threadCount) {
//                    Date end = new Date();
//                    long last = end.getTime() - start.getTime();
//                    System.out.println(threadCount + " requests cost " + last + " ms.");
//                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 客户端请求处理线程类
     *
     * @author shirdrn
     */
    class RequestHandler implements Runnable {

        private Socket socket;

        public RequestHandler(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            try {
                InputStream in = socket.getInputStream(); // 流：客户端->服务端（读）
                OutputStream out = socket.getOutputStream(); // 流：服务端->客户端（写）
                int receiveBytes;
                byte[] receiveBuffer = new byte[128];
                String clientMessage = "";
                if((receiveBytes=in.read(receiveBuffer))!=-1) {
                    clientMessage = new String(receiveBuffer, 0, receiveBytes);
                    if(clientMessage.startsWith("I am the client")) {
                        String serverResponseWords =
                                "I am the server, and you are the " + (++sequence) + "th client.";
                        out.write(serverResponseWords.getBytes());
                    }
                }
                out.flush();
                System.out.println("Server: receives clientMessage->" + clientMessage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
