package com.js.proxy;

import com.js.RpcRequest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class RpcNetTranSport {
    private String host;

    private Integer port;

    public RpcNetTranSport(String host, Integer port) {
        this.host = host;
        this.port = port;
    }

    public Socket newSocket() {
        try {
            Socket socket = new Socket(host, port);
            return socket;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Object send(RpcRequest rpcRequest) {
        Socket socket = newSocket();
        Object result = null;
        ObjectOutputStream outputStream = null;
        ObjectInputStream inputStream = null;
        try {
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeObject(rpcRequest);
            outputStream.flush();
            inputStream = new ObjectInputStream(socket.getInputStream());
            result = inputStream.readObject();
        } catch (Exception e) {

        }finally {
            if(inputStream!=null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(outputStream!=null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
}
