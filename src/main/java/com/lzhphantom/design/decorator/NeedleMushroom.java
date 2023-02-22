package sg.com.ncs.luozhihui.design.decorator;

import sg.com.ncs.luozhihui.design.decorator.component.Snack;

/**
 * @author luozhihui
 * @create 2/22/2023
 */
public class NeedleMushroom extends Decorator{
    public NeedleMushroom(Snack obj) {
        super(obj);
        setDes(" 金针菇 ");
        setPrice(2.5f);
    }
}
