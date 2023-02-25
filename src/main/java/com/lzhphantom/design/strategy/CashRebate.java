package sg.com.ncs.luozhihui.design.strategy;

import lombok.AllArgsConstructor;

/**
 * @author luozhihui
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
