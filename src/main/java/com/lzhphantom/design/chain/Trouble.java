package com.lzhphantom.design.chain;

import lombok.Getter;
import lombok.ToString;

/**
 * @author lzhphantom
 * @create 2/28/2023
 */
@Getter
public class Trouble {
    private int number;

    public Trouble(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "问题编号：[" + number + "]";
    }
}
