package com.lzhphantom.design.facade;

/**
 * @author lzhphantom
 * @create 2/27/2023
 */
public class Client {

    public static void main(String[] args) {
        Facade facade = new Facade();
        facade.methodA();
        facade.methodB();
    }

}
