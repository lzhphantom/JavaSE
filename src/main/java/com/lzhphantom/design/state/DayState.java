package com.lzhphantom.design.state;

/**
 * @author lzhphantom
 * @create 2/28/2023
 */
public class DayState implements State{
    private DayState() {
    }
    public  static DayState dayState = new DayState();
    public static DayState getInstance(){
        return dayState;
    }

    @Override
    public void doClock(Context context, int hour) {
        if(hour<6 || hour >=18){
            //晚上
            context.changeState(NightState.getInstance());
        }
    }

    @Override
    public void doUse(Context context) {
        context.callSecurity("白天使用");
    }

    @Override
    public void doAlarm(Context context) {
        context.callSecurity("白天警铃");
    }

    @Override
    public void doPhone(Context context) {
        context.recordLog("白天打电话");
    }
}
