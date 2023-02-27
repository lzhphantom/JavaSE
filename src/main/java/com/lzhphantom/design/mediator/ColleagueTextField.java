package sg.com.ncs.luozhihui.design.mediator;

import java.awt.*;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;

/**
 * @author luozhihui
 * @create 2/27/2023
 */
public class ColleagueTextField extends TextField implements TextListener, Colleague {
    public ColleagueTextField(String text, int columns) throws HeadlessException {
        super(text, columns);
    }

    private Mediator mediator;

    @Override
    public void textValueChanged(TextEvent e) {
        mediator.colleagueChanged();
    }

    @Override
    public void setMediator(Mediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public void setColleagueEnable(boolean enable) {
        setEnabled(enable);
        setBackground(enable ? Color.WHITE : Color.BLACK);
    }
}
