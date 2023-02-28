package com.lzhphantom.design.memento;

import com.google.common.collect.Lists;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lzhphantom
 * @create 2/28/2023
 */
@Data
public class Memento implements Serializable {
    private int money;
    private ArrayList fruits;

    public Memento(int money) {
        this.money = money;
        this.fruits = Lists.newArrayList();
    }

    List getFruits() {
        return (List) fruits.clone();
    }
    void addFruits(String fruit) {
        fruits.add(fruit);
    }
}
