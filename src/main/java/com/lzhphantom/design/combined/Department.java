package sg.com.ncs.luozhihui.design.combined;

/**
 * @author luozhihui
 * @create 2/22/2023
 */
public class Department extends OrganizationComponent{
    public Department(String name, String des) {
        super(name, des);
    }

    @Override
    protected void print() {
        System.out.println(getName());
    }
}
