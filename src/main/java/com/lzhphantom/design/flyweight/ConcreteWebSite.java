package sg.com.ncs.luozhihui.design.flyweight;

import lombok.AllArgsConstructor;

/**
 * @author luozhihui
 * @create 2/22/2023
 */
@AllArgsConstructor
public class ConcreteWebSite extends WebSite{
    private String name;
    @Override
    public void use() {
        System.out.println("网站分类：" + name);
    }

    @Override
    public void use(User user) {
        System.out.println("用户：" + user.getName());
    }
}
