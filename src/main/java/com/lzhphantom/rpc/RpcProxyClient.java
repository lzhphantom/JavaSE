package sg.com.ncs.luozhihui.rpc;

import java.lang.reflect.Proxy;
import java.rmi.server.RemoteObjectInvocationHandler;

/**
 * @author luozhihui
 * @date 2/9/2023
 */
public class RpcProxyClient {
    public <T> T clientProxy(final Class<T> clazz, final String host,
                             final int port) {
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class<?>[]{clazz}, new RemoteInvocationHandler(host, port));
    }
}
