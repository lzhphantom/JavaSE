package sg.com.ncs.luozhihui.design.command;

/**
 * @author luozhihui
 * @create 2/27/2023
 */
public class TVOnCommand implements Command {
    TVReceiver tvReceiver;

    public TVOnCommand(TVReceiver tvReceiver) {
        super();
        this.tvReceiver = tvReceiver;
    }

    @Override
    public void execute() {
        tvReceiver.on();
    }

    @Override
    public void undo() {
        tvReceiver.off();
    }
}
