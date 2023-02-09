package com.lzhphantom.rpc;

/**
 * @author lzhphantom
 * @date 2/9/2023
 */
public interface IOrderService {
    String queryOrderInfo(String id);
    String queryOrderList();
}
