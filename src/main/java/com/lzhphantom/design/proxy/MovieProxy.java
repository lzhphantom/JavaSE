package sg.com.ncs.luozhihui.design.proxy;

import java.lang.reflect.Proxy;

/**
 * @author luozhihui
 * @create 2/22/2023
 */
public class MovieProxy {
    private Object target;

    public MovieProxy(Object target) {
        this.target = target;
    }

    public Object getProxyInstance() {
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                (proxy, method, args) -> {
                    System.out.println("JDK代理开始~~");
                    //反射机制调用目标对象的方法
                    Object ret = method.invoke(target, args);
                    System.out.println("JDK代理结束~~");
                    return ret;
                });
    }

    public static void main(String[] args) {
        IMovie target = new Movie();
        IMovie proxyInstance = (IMovie) new MovieProxy(target).getProxyInstance();
        System.out.println("proxyInstance=" + proxyInstance.getClass());
        proxyInstance.advertising(true, "test1");
        proxyInstance.play(" 速度与激情8 ");
        proxyInstance.advertising(false, "test2");
    }
}
