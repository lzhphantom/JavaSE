package sg.com.ncs.luozhihui.design.abstractFactory.factory;

/**
 * @author lzhphantom
 * @create 2/21/2023
 */
public class PizzaStore {
    public static void main(String[] args) {
        new OrderPizza(new BJFactory());
    }
}
