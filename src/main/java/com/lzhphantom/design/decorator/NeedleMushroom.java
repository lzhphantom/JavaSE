package com.lzhphantom.design.decorator;

import com.lzhphantom.design.decorator.component.Snack;

/**
 * @author lzhphantom
 * @create 2/22/2023
 */
public class NeedleMushroom extends Decorator{
    public NeedleMushroom(Snack obj) {
        super(obj);
        setDes(" 金针菇 ");
        setPrice(2.5f);
    }
}
