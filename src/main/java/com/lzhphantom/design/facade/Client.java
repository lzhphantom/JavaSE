package sg.com.ncs.luozhihui.design.facade;

/**
 * @author luozhihui
 * @create 2/27/2023
 */
public class Client {

    public static void main(String[] args) {
        Facade facade = new Facade();
        facade.methodA();
        facade.methodB();
    }

}
