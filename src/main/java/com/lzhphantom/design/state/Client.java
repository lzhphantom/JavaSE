package com.lzhphantom.design.state;

/**
 * @author lzhphantom
 * @create 2/28/2023
 */
public class Client {
    public static void main(String[] args) {

        SafeFrame f = new SafeFrame("状态模式");
        while (true) {
            for (int hour = 1; hour <= 24; hour++) {
                f.setClock(hour);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
