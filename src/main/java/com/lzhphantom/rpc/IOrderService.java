package sg.com.ncs.luozhihui.rpc;

/**
 * @author luozhihui
 * @date 2/9/2023
 */
public interface IOrderService {
    String queryOrderInfo(String id);
    String queryOrderList();
}
