package com.lzhphantom.design.decorator.component;

import lombok.Data;

/**
 * @author lzhphantom
 * @create 2/22/2023
 */
@Data
public abstract class Snack {
    public String des;
    private float price = 0.0f;

    public abstract float cost();
}
