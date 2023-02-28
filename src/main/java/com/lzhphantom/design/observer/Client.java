package com.lzhphantom.design.observer;

import java.util.Observable;

/**
 * @author lzhphantom
 * @create 2/27/2023
 */
public class Client {
    public static void main(String[] args) {
        Observable observable = new RandomNumberGenerator();
        observable.addObserver(new GraphObserver());
        observable.addObserver(new DigitalObserver());
        ((RandomNumberGenerator) observable).execute();
    }
}
