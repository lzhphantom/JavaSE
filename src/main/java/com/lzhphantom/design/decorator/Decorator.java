package com.lzhphantom.design.decorator;

import com.lzhphantom.design.decorator.component.Snack;

/**
 * @author lzhphantom
 * @create 2/22/2023
 */

public class Decorator extends Snack {
    private Snack obj;
    public Decorator(Snack obj) { //组合
        this.obj = obj;
    }
    @Override
    public float cost() {
        return getPrice()+obj.cost();
    }
    @Override
    public String getDes() {
        // obj.getDes() 输出被装饰者的信息
        return des + " " + getPrice() + " && " + obj.getDes();
    }
}
