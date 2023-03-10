package com.lzhphantom.design.chain;

/**
 * @author lzhphantom
 * @create 2/28/2023
 */
public class OddSupport extends Support {
    public OddSupport(String name) {
        super(name);
    }

    @Override
    protected boolean resolve(Trouble trouble) {
        return (trouble.getNumber() % 2) == 1;
    }
}
