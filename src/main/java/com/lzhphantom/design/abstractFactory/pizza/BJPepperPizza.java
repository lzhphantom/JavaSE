package com.lzhphantom.design.abstractFactory.pizza;

/**
 * @author lzhphantom
 * @create 2/21/2023
 */
public class BJPepperPizza extends Pizza{
    @Override
    public void prepare() {
        setName("北京的胡椒pizza");
        System.out.println("北京的胡椒pizza 准备原材料");
    }
}
