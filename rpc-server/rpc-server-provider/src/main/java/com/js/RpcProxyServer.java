package com.js;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RpcProxyServer {
    ExecutorService executorService = Executors.newCachedThreadPool();

    public void punlisher(Object service, int port) {
        ServerSocket serverSocket = null;

        try {
            // 链接会阻塞
            serverSocket = new ServerSocket(port);
            System.out.println(serverSocket);
            // 线程多路复用 一个线程管理多个链接
            while (true) {
                // 有客户端连接
                Socket socket = serverSocket.accept();
                executorService.submit(new ProcessorHandler(socket, service));
            }
        } catch (Exception e) {

        } finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
