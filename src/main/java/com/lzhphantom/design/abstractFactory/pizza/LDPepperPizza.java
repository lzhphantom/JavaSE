package sg.com.ncs.luozhihui.design.abstractFactory.pizza;

/**
 * @author lzhphantom
 * @create 2/21/2023
 */
public class LDPepperPizza extends Pizza{
    @Override
    public void prepare() {
        setName("伦敦的胡椒pizza");
        System.out.println(" 伦敦的胡椒pizza 准备原材料");
    }
}
