package sg.com.ncs.luozhihui.design.bridging;

/**
 * @author luozhihui
 * @create 2/24/2023
 */
public class XiaoMi implements Brand{
    @Override
    public void open() {
        System.out.println(" 小米手机开机 ");
    }

    @Override
    public void close() {
        System.out.println(" 小米手机关机 ");
    }

    @Override
    public void call() {
        System.out.println(" 小米手机打电话 ");
    }
}
