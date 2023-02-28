package com.lzhphantom.design.visitor;

/**
 * @author lzhphantom
 * @create 2/27/2023
 */
public abstract class Visitor {
    abstract void visit(File file);
    abstract void visit(Directory directory);
}
