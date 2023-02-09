package com.lzhphantom.rpc;

import lombok.AllArgsConstructor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author lzhphantom
 * @date 2/9/2023
 */
@AllArgsConstructor
public class RemoteInvocationHandler implements InvocationHandler {
    private String host;
    private int port;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        RpcNetTransport rpcNetTransport = new RpcNetTransport(host, port);
        RpcRequest rpcRequest = new RpcRequest();
        rpcRequest.setClassName(method.getDeclaringClass().getName());
        rpcRequest.setArgs(args);
        rpcRequest.setMethodName(method.getName());
        rpcRequest.setTypes(method.getParameterTypes());
        return rpcNetTransport.send(rpcRequest);
    }
}
