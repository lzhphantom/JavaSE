package com.lzhphantom.rpc;

/**
 * @author lzhphantom
 * @date 2/9/2023
 */
public class OrderServiceImpl implements IOrderService{
    @Override
    public String queryOrderInfo(String id) {
        return "this is query order info - "+id;
    }

    @Override
    public String queryOrderList() {
        return "this is query order list";
    }
}
