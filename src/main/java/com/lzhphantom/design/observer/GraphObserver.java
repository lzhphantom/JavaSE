package com.lzhphantom.design.observer;

import java.util.Observable;
import java.util.Observer;

/**
 * @author lzhphantom
 * @create 2/27/2023
 */
public class GraphObserver implements Observer {

    @Override
    public void update(Observable o, Object arg) {
        System.out.print("GraphObserver:");
        for (int i = 0; i < ((RandomNumberGenerator) o).getNumber(); i++) {
            System.out.print("*");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println();
    }
}
