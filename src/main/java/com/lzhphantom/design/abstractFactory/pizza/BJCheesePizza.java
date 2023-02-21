package sg.com.ncs.luozhihui.design.abstractFactory.pizza;

/**
 * @author lzhphantom
 * @create 2/21/2023
 */
public class BJCheesePizza extends Pizza{
    @Override
    public void prepare() {
        setName("北京奶酪pizza");
        System.out.println("北京的奶酪pizza 准备原材料");
    }
}
