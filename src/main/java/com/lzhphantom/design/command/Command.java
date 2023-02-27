package sg.com.ncs.luozhihui.design.command;

/**
 * @author luozhihui
 * @create 2/27/2023
 */
public interface Command {
    void execute();
    void undo();
}
