package com.simple.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Socket 服务器端启动类
 * Created by Administrator on 2015/11/29 0029.
 */
public class SocketServer {

    private int serverPort;

    private ServerSocket serverSocket = null;

    public SocketServer(int serverPort) {
        this.serverPort = serverPort;
    }

    /**
     * Socket 服务器启动方法
     * */
    public void startServer(){
        try {
            serverSocket = new ServerSocket(serverPort);
            while (true){
                SocketClient socketClient = new SocketClient(serverSocket.accept());
                Thread thread = new Thread(socketClient);
                thread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Socket服务器关闭方法
     * */
    public void closeServer() throws IOException {
        serverSocket.close();
    }

    /**
     * Socket 线程，
     * 服务器接收到客户端连接后，启动一个线程为客户端服务
     * */
    private class SocketClient implements Runnable{

        Socket socket = null;

        public SocketClient(Socket socket) {
            this.socket = socket;
        }
        public void run() {
            DataInputStream inputStream = null;
            try {
                inputStream = new DataInputStream(socket.getInputStream());
                System.out.println("服务器端收到[" + socket.getRemoteSocketAddress() + "]的信息：" + inputStream.readUTF());
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                if (null != socket){
                    try {
                        socket.close();
                    } catch (IOException e) {
                        System.out.println("socket连接关闭时出现异常");
                    }
                }
                if (null != inputStream){
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        System.out.println("关闭socket输入流时出现异常");
                    }
                }
            }
        }
    }
}
