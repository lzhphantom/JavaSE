package sg.com.ncs.luozhihui.design.bridging;

/**
 * @author luozhihui
 * @create 2/24/2023
 */
public abstract class Phone {
    private Brand brand;

    //构造器
    public Phone(Brand brand) {
        super();
        this.brand = brand;
    }

    protected void open() {
        this.brand.open();
    }

    protected void close() {
        brand.close();
    }

    protected void call() {
        brand.call();
    }
}
