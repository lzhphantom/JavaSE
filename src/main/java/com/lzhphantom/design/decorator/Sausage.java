package com.lzhphantom.design.decorator;

import com.lzhphantom.design.decorator.component.Snack;

/**
 * @author lzhphantom
 * @create 2/22/2023
 */
public class Sausage extends Decorator{
    public Sausage(Snack obj) {
        super(obj);
        setDes(" 烤肠 ");
        setPrice(2.0f);
    }
}
