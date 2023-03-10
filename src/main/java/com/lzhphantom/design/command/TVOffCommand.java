package com.lzhphantom.design.command;

/**
 * @author lzhphantom
 * @create 2/27/2023
 */
public class TVOffCommand implements Command {
    TVReceiver tvReceiver;

    public TVOffCommand(TVReceiver tvReceiver) {
        super();
        this.tvReceiver = tvReceiver;
    }

    @Override
    public void execute() {
        tvReceiver.off();
    }

    @Override
    public void undo() {
        tvReceiver.on();
    }
}
