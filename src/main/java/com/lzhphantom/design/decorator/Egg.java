package com.lzhphantom.design.decorator;

import sg.com.ncs.luozhihui.design.decorator.component.Snack;

/**
 * @author lzhphantom
 * @create 2/22/2023
 */
public class Egg extends Decorator{
    public Egg(Snack obj) {
        super(obj);
        setDes(" 鸡蛋 ");
        setPrice(1.0f);
    }
}
