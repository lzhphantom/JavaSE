package com.lzhphantom.design.iterator;

/**
 * @author lzhphantom
 * @create 2/28/2023
 */
public interface Iterator<T> {
    boolean hasNext();
    T next();
}
