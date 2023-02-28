package com.lzhphantom.design.chain;

/**
 * @author lzhphantom
 * @create 2/28/2023
 */
public class NoSupport extends Support {
    public NoSupport(String name) {
        super(name);
    }

    @Override
    protected boolean resolve(Trouble trouble) {
        return false;
    }
}
