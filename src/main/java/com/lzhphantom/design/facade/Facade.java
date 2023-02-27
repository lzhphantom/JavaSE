package sg.com.ncs.luozhihui.design.facade;

/**
 * @author luozhihui
 * @create 2/27/2023
 */
public class Facade {
    SubSystemA a;
    SubSystemB b;

    public Facade() {
        a = new SubSystemA();
        b = new SubSystemB();
    }

    //提供给外部访问的方法
    public void methodA() {
        this.a.dosomethingA();
    }

    public void methodB() {
        this.b.dosomethingB();
    }
}
