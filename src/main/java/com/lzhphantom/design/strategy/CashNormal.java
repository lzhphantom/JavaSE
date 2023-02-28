package com.lzhphantom.design.strategy;

/**
 * @author lzhphantom
 * @create 2/24/2023
 */
public class CashNormal extends CashSuper{
    @Override
    public double acceptCash(double money) {
        return money;
    }
}
