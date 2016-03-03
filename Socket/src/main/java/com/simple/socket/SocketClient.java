package com.simple.socket;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by Administrator on 2015/11/29 0029.
 */
public class SocketClient {

    private String serverIp;

    private int serverPort;

    public SocketClient(String serverIp, int serverPort) {
        this.serverIp = serverIp;
        this.serverPort = serverPort;
    }

    public void sendMsg(String msg) throws IOException {
        Socket socket = new Socket(serverIp,serverPort);
        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
        dataOutputStream.writeUTF(msg);
        dataOutputStream.close();
        socket.shutdownOutput();
        socket.close();
    }
}
