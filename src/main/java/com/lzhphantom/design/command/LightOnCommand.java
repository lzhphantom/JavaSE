package com.lzhphantom.design.command;

/**
 * @author lzhphantom
 * @create 2/27/2023
 */
public class LightOnCommand implements Command {
    LightReceiver light;

    public LightOnCommand(LightReceiver light) {
        super();
        this.light = light;
    }

    @Override
    public void execute() {
        light.on();
    }

    @Override
    public void undo() {
        light.off();
    }
}
