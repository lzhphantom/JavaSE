package com.lzhphantom.design.visitor;

/**
 * @author lzhphantom
 * @create 2/27/2023
 */
public interface Element {
    void accept(Visitor visitor);
}
