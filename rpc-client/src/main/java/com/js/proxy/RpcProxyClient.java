package com.js.proxy;

import java.lang.reflect.Proxy;

public class RpcProxyClient {

    public <T> T clientProxy(final Class<T> interFaceCls,final String host,final int port){
        return (T) Proxy.newProxyInstance(
                interFaceCls.getClassLoader(),
                new Class<?>[]{interFaceCls},
                new RemoteInvocationHandler(host,port));
    }
}
