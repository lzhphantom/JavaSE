package com.lzhphantom.design.command;

/**
 * @author lzhphantom
 * @create 2/27/2023
 */
public interface Command {
    void execute();
    void undo();
}
