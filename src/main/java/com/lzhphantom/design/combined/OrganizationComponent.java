package com.lzhphantom.design.combined;

import lombok.Data;

/**
 * @author lzhphantom
 * @create 2/22/2023
 */
@Data
public abstract class OrganizationComponent {

    private String name;
    private String des;

    protected void add(OrganizationComponent component) {
        throw new UnsupportedOperationException();
    }

    protected void remove(OrganizationComponent component) {
        throw new UnsupportedOperationException();
    }

    public OrganizationComponent(String name, String des) {
        super();
        this.name = name;
        this.des = des;
    }

    protected abstract void print();
}
