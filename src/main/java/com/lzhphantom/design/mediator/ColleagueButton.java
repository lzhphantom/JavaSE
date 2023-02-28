package com.lzhphantom.design.mediator;

import java.awt.*;

/**
 * @author lzhphantom
 * @create 2/27/2023
 */
public class ColleagueButton extends Button implements Colleague {
    public ColleagueButton(String label) throws HeadlessException {
        super(label);
    }

    private Mediator mediator;

    @Override
    public void setMediator(Mediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public void setColleagueEnable(boolean enable) {
        setEnabled(enable);
    }
}
