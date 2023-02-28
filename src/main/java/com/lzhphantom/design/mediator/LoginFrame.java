package com.lzhphantom.design.mediator;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author lzhphantom
 * @create 2/27/2023
 */
public class LoginFrame extends Frame implements ActionListener, Mediator {
    private ColleagueButton buttonOK;
    private ColleagueButton buttonCancel;

    private ColleagueCheckBox chkGuest;
    private ColleagueCheckBox chkUser;

    private ColleagueTextField tfUser;
    private ColleagueTextField tfPass;

    public LoginFrame(String title) throws HeadlessException {
        super(title);
        setBackground(Color.lightGray);
        setLayout(new GridLayout(4, 2));
        createColleagues();
        add(chkGuest);
        add(chkUser);
        add(new Label("用户名："));
        add(tfUser);
        add(new Label("密码："));
        add(tfPass);
        add(buttonOK);
        add(buttonCancel);
        colleagueChanged();
        pack();
        show();

    }

    @Override
    public void createColleagues() {
        CheckboxGroup chk = new CheckboxGroup();
        chkGuest = new ColleagueCheckBox("Guest", chk, true);
        chkUser = new ColleagueCheckBox("User", chk, false);
        buttonOK = new ColleagueButton("OK");
        buttonCancel = new ColleagueButton("Cancel");
        tfUser = new ColleagueTextField("", 10);
        tfPass = new ColleagueTextField("", 10);
        tfPass.setEchoChar('#');

        chkGuest.setMediator(this);
        chkUser.setMediator(this);
        buttonOK.setMediator(this);
        buttonCancel.setMediator(this);
        tfUser.setMediator(this);
        tfPass.setMediator(this);

        chkGuest.addItemListener(chkGuest);
        chkUser.addItemListener(chkUser);
        buttonOK.addActionListener(this);
        buttonCancel.addActionListener(this);
        tfUser.addTextListener(tfUser);
        tfPass.addTextListener(tfPass);

    }

    @Override
    public void colleagueChanged() {
        if (chkGuest.getState()) {
            tfUser.setColleagueEnable(false);
            tfPass.setColleagueEnable(false);
            buttonOK.setColleagueEnable(true);
        } else {
            tfUser.setColleagueEnable(true);
            userPassChanged();
        }
    }

    private void userPassChanged() {
        if (tfUser.getText().length() > 0) {
            tfPass.setColleagueEnable(true);
            if (tfPass.getText().length() > 0) {
                buttonOK.setColleagueEnable(true);
            } else {
                buttonOK.setColleagueEnable(false);
            }
        } else {
            tfPass.setColleagueEnable(false);
            buttonOK.setColleagueEnable(false);
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.toString());
        System.exit(0);
    }

}
