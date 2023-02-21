package com.lzhphantom.design.factoryMethod;

/**
 * @author lzhphantom
 * @create 2/21/2023
 */
public class FactoryMain {
    public static void main(String[] args) {
        IDCardFactory factory = new IDCardFactory();
        Product p = factory.create("jsy");
        p.use();
        System.out.println("--------------------");
        p=factory.create("qsz");
        p.use();
        System.out.println("--------------------");
        factory.getAllProductOwner();
    }
}
