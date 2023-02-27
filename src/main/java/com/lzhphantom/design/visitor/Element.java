package sg.com.ncs.luozhihui.design.visitor;

/**
 * @author luozhihui
 * @create 2/27/2023
 */
public interface Element {
    void accept(Visitor visitor);
}
