package sg.com.ncs.luozhihui.design.strategy;

import lombok.AllArgsConstructor;

/**
 * @author luozhihui
 * @create 2/24/2023
 */
@AllArgsConstructor
public class CashContext {
    private CashSuper cashSuper;
    public double getResult(double money){
        return cashSuper.acceptCash(money);
    }
}
