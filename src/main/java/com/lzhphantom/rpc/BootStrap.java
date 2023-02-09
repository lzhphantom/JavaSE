package com.lzhphantom.rpc;

/**
 * @author lzhphantom
 * @date 2/9/2023
 */
public class BootStrap {
    public static void main(String[] args) {
        OrderServiceImpl orderService = new OrderServiceImpl();
        RpcProxyServer rpcProxyServer = new RpcProxyServer();
        rpcProxyServer.publisher(orderService,9090);
    }
}
