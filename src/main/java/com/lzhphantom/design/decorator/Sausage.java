package sg.com.ncs.luozhihui.design.decorator;

import sg.com.ncs.luozhihui.design.decorator.component.Snack;

/**
 * @author luozhihui
 * @create 2/22/2023
 */
public class Sausage extends Decorator{
    public Sausage(Snack obj) {
        super(obj);
        setDes(" 烤肠 ");
        setPrice(2.0f);
    }
}
