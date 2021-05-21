package com.js;

public class AppServer {
    public static void main(String[] args) {
        ISayHelloService service = new SayHelloService();
        RpcProxyServer rpcProxyServer = new RpcProxyServer();
        rpcProxyServer.punlisher(service,8080);
    }
}
