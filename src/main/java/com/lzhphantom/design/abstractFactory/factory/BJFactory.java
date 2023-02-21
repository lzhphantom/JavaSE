package com.lzhphantom.design.abstractFactory.factory;

import com.lzhphantom.design.abstractFactory.pizza.BJCheesePizza;
import com.lzhphantom.design.abstractFactory.pizza.BJPepperPizza;
import com.lzhphantom.design.abstractFactory.pizza.Pizza;

/**
 * @author lzhphantom
 * @create 2/21/2023
 */
public class BJFactory implements AbsFactory {
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
