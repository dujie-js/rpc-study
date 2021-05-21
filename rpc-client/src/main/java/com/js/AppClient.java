package com.js;


import com.js.proxy.RpcProxyClient;

public class AppClient {
    public static void main(String[] args) {
        RpcProxyClient rpcProxyClient = new RpcProxyClient();

        ISayHelloService sayHelloService = rpcProxyClient.clientProxy(ISayHelloService.class,"localhost",8080);
        System.out.println(sayHelloService.sayHello("JS"));
    }
}
