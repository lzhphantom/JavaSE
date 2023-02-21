package com.lzhphantom.design.abstractFactory.factory;

import com.lzhphantom.design.abstractFactory.pizza.Pizza;

/**
 * @author lzhphantom
 * @create 2/21/2023
 */
public interface AbsFactory {
    Pizza createPizza(String orderType);
}
