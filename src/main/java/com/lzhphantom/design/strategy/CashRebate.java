package com.lzhphantom.design.strategy;

import lombok.AllArgsConstructor;

/**
 * @author lzhphantom
 * @create 2/24/2023
 */
@AllArgsConstructor
public class CashRebate extends CashSuper {
    private double moneyRebate = 0.8;

    @Override
    public double acceptCash(double money) {
        return money * moneyRebate;
    }
}
