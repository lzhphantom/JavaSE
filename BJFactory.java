package sg.com.ncs.luozhihui.design.abstractFactory.factory;

import sg.com.ncs.luozhihui.design.abstractFactory.pizza.BJCheesePizza;
import sg.com.ncs.luozhihui.design.abstractFactory.pizza.BJPepperPizza;
import sg.com.ncs.luozhihui.design.abstractFactory.pizza.Pizza;

/**
 * @author lzhphantom
 * @create 2/21/2023
 */
public class BJFactory implements AbsFactory{
    @Override
    public Pizza createPizza(String orderType) {
        System.out.println("~使用的是抽象工厂模式~");
        Pizza pizza = null;
        if(orderType.equals("cheese")) {
            pizza = new BJCheesePizza();
        } else if (orderType.equals("pepper")){
            pizza = new BJPepperPizza();
        }
        return pizza;
    }
}
