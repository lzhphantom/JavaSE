package com.lzhphantom.design.chain;

/**
 * @author lzhphantom
 * @create 2/28/2023
 */
public class SpecialSupport extends Support {
    public int specialNumber;

    public SpecialSupport(String name, int specialNumber) {
        super(name);
        this.specialNumber = specialNumber;
    }

    @Override
    protected boolean resolve(Trouble trouble) {
        return trouble.getNumber() == specialNumber;
    }
}
