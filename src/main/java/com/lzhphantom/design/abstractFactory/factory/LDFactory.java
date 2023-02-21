package com.lzhphantom.design.abstractFactory.factory;


import com.lzhphantom.design.abstractFactory.pizza.LDCheesePizza;
import com.lzhphantom.design.abstractFactory.pizza.LDPepperPizza;
import com.lzhphantom.design.abstractFactory.pizza.Pizza;

/**
 * @author lzhphantom
 * @create 2/21/2023
 */
public class LDFactory implements AbsFactory{
    @Override
    public Pizza createPizza(String orderType) {
        System.out.println("~使用的是抽象工厂模式~");
        Pizza pizza = null;
        if (orderType.equals("cheese")) {
            pizza = new LDCheesePizza();
        } else if (orderType.equals("pepper")) {
            pizza = new LDPepperPizza();
        }
        return pizza;
    }
}
