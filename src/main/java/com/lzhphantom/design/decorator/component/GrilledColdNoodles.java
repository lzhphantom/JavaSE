package com.lzhphantom.design.decorator.component;

/**
 * @author lzhphantom
 * @create 2/22/2023
 */
public class GrilledColdNoodles extends Snack {
    public GrilledColdNoodles() {
        setPrice(4.0f);
        setDes(" 烤冷面 " + cost());
    }

    @Override
    public float cost() {
        return getPrice();
    }
}
