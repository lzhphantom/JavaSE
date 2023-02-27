package sg.com.ncs.luozhihui.design.observer;

import java.util.Observable;
import java.util.Observer;

/**
 * @author luozhihui
 * @create 2/27/2023
 */
public class DigitalObserver implements Observer {

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("DigitalObserver:" + ((RandomNumberGenerator) o).getNumber());
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
