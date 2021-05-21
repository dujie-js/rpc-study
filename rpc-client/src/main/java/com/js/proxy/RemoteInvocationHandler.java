package com.js.proxy;

import com.js.RpcRequest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class RemoteInvocationHandler implements InvocationHandler {

    private String host;

    private Integer port;

    public RemoteInvocationHandler(String host, Integer port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("远程请求服务端开始");
        RpcRequest rpcRequest = new RpcRequest();
        rpcRequest.setClassName(method.getDeclaringClass().getName());
        rpcRequest.setMethodName(method.getName());
        rpcRequest.setParames(args);
        rpcRequest.setTypes(method.getParameterTypes());

        // 对应建立网络通信
        RpcNetTranSport rpcNetTranSport = new RpcNetTranSport(host,port);
        Object result = rpcNetTranSport.send(rpcRequest);
        // 建立连接
        return result;
    }
}
