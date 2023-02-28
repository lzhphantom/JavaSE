package com.lzhphantom.design.state;

/**
 * @author lzhphantom
 * @create 2/28/2023
 */
public interface State {
    void doClock(Context context,int hour);
    void doUse(Context context);
    void doAlarm(Context context);
    void doPhone(Context context);
}
