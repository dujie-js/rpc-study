package com.js;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.Socket;

public class ProcessorHandler implements Runnable {

    Socket socket;

    Object service;

    public ProcessorHandler(Socket socket, Object service) {
        this.socket = socket;
        this.service = service;
    }

    @Override
    public void run() {
        ObjectInputStream inputStream = null;

        ObjectOutputStream outputStream = null;
        try {
            // IO会阻塞
            inputStream = new ObjectInputStream(socket.getInputStream());

            RpcRequest request = (RpcRequest) inputStream.readObject();

            Object result = invoke(request);

            outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeObject(result);
            outputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭留
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private Object invoke(RpcRequest rpcRequest) throws Exception {
        //反射调用
        //拿到客户端请求的参数
        Object[] args = rpcRequest.getParames();
        //获得每个参数的类型
        Class<?>[] types = new Class[args.length];
        for (int i = 0; i < args.length; i++) {
            types[i] = args[i].getClass();
        }
        //跟去请求的类进行加载
        Class clazz = Class.forName(rpcRequest.getClassName());
        //sayHello, saveUser找到这个类中的方法
        Method method = clazz.getMethod(rpcRequest.getMethodName(), types);
        //HelloServiceImpl 进行反射调用
        Object result = method.invoke(service, args);
        return result;
    }
}
