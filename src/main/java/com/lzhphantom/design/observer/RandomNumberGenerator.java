package com.lzhphantom.design.observer;

import java.util.Observable;
import java.util.Random;

/**
 * @author lzhphantom
 * @create 2/27/2023
 */
public class RandomNumberGenerator extends Observable {
    private Random random = new Random();
    private int number;

    public void execute() {
        for (int i = 0; i < 20; i++) {
            number = random.nextInt(60);
            setChanged();
            notifyObservers();
        }
    }

    public int getNumber() {
        return number;
    }
}
