package com.lzhphantom.design.combined;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lzhphantom
 * @create 2/22/2023
 */
public class University extends OrganizationComponent{
    List<OrganizationComponent> organizationComponents = Lists.newArrayList();

    public University(String name, String des) {
        super(name, des);
    }

    // 重写add
    @Override
    protected void add(OrganizationComponent organizationComponent) {
        organizationComponents.add(organizationComponent);
    }

    // 重写remove
    @Override
    protected void remove(OrganizationComponent organizationComponent) {
        organizationComponents.remove(organizationComponent);
    }

    @Override
    protected void print() {
        System.out.println("--------------" + getName() + "--------------");
        //遍历 organizationComponents
        for (OrganizationComponent organizationComponent : organizationComponents) {
            organizationComponent.print();
        }
    }
}
