package com.lzhphantom.design.state;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author lzhphantom
 * @create 2/28/2023
 */
public class SafeFrame extends Frame implements Context, ActionListener {
    private final Button btnUse = new Button("使用");
    private final Button btnAlarm = new Button("警铃");
    private final Button btnPhone = new Button("打电话");
    private final Button btnExit = new Button("退出");

    private final TextField tfClock = new TextField(60);
    private final TextArea taAlarm = new TextArea(10, 60);
    private State state = DayState.getInstance();

    public SafeFrame(String title) throws HeadlessException {
        super(title);
        setBackground(Color.BLUE);
        setLayout(new BorderLayout());

        add(tfClock, BorderLayout.NORTH);
        tfClock.setEditable(false);
        add(taAlarm, BorderLayout.CENTER);
        taAlarm.setEditable(false);

        Panel panel = new Panel();
        panel.add(btnUse);
        panel.add(btnAlarm);
        panel.add(btnPhone);
        panel.add(btnExit);
        add(panel, BorderLayout.SOUTH);

        pack();
        show();

        btnUse.addActionListener(this);
        btnAlarm.addActionListener(this);
        btnPhone.addActionListener(this);
        btnExit.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnUse) {
            state.doUse(this);
        } else if (e.getSource() == btnAlarm) {
            state.doAlarm(this);
        } else if (e.getSource() == btnPhone) {
            state.doPhone(this);
        } else if (e.getSource() == btnExit) {
            System.exit(0);
        } else {
            System.out.print("未预料错误！");
        }
    }

    @Override
    public void setClock(int hour) {
        tfClock.setText(hour < 10 ? "现在时间是:" + "0" + hour : "现在时间是:" + hour);
        state.doClock(this, hour);
    }

    @Override
    public void changeState(State state) {
        System.out.println("从状态" + this.state + "转变到了" + state);
        this.state = state;
    }

    @Override
    public void callSecurity(String str) {
        taAlarm.append("Call..." + str + "\n");
    }

    @Override
    public void recordLog(String msg) {
        taAlarm.append("record..." + msg + "\n");
    }
}
