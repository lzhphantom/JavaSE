package sg.com.ncs.luozhihui.design.state;

/**
 * @author luozhihui
 * @create 2/28/2023
 */
public interface Context {
    void setClock(int hour);
    void changeState(State state);
    void callSecurity(String str);
    void recordLog(String msg);
}
