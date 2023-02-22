package sg.com.ncs.luozhihui.design.decorator.component;

/**
 * @author luozhihui
 * @create 2/22/2023
 */
public class HandGrabCake extends Snack {
    public HandGrabCake() {
        setPrice(5.0f);
        setDes("手抓饼" + cost());
    }

    @Override
    public float cost() {
        return getPrice();
    }
}
