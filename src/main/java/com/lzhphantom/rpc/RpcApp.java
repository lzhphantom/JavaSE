package sg.com.ncs.luozhihui.rpc;

/**
 * @author luozhihui
 * @date 2/9/2023
 */
public class RpcApp {
    public static void main(String[] args) {
        RpcProxyClient rpcProxyClient = new RpcProxyClient();
        IOrderService orderService =
                rpcProxyClient.clientProxy(IOrderService.class, "localhost",
                        9090);
        System.out.println(orderService.queryOrderList());
        System.out.println(orderService.queryOrderInfo("1111"));
    }
}
