package sg.com.ncs.luozhihui.design.observer;

import java.util.Observable;

/**
 * @author luozhihui
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
