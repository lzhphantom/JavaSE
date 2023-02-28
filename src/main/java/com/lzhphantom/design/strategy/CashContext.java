package com.lzhphantom.design.strategy;

import lombok.AllArgsConstructor;

/**
 * @author lzhphantom
 * @create 2/24/2023
 */
@AllArgsConstructor
public class CashContext {
    private CashSuper cashSuper;
    public double getResult(double money){
        return cashSuper.acceptCash(money);
    }
}
