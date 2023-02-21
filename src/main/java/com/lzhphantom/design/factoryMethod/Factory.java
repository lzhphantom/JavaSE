package com.lzhphantom.design.factoryMethod;

/**
 * @author lzhphantom
 * @create 2/21/2023
 */
public abstract class Factory {
    public abstract Product createProduct(String owner);
    public abstract void registerProduct(Product p);
    public abstract void getAllProductOwner();

    public Product create(String owner) {
        Product product = createProduct(owner);
        registerProduct(product);
        return product;
    }
}
