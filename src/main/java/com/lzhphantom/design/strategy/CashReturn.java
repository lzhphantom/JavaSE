package com.lzhphantom.design.strategy;

import lombok.AllArgsConstructor;

/**
 * @author luozhihui
 * @create 2/24/2023
 */
@AllArgsConstructor
public class CashReturn extends CashSuper {
    private double moneyCondition;
    private double moneyReturn;

    @Override
    public double acceptCash(double money) {
        double result = money;
        if (money >= moneyCondition) {
            result = money - Math.floor(money / moneyCondition) * moneyReturn;
        }
        return result;
    }
}
