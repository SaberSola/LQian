package com.lqian.design.Mediator;

import java.awt.*;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;

public class ColleagueTextField  extends TextField implements TextListener, Colleague{

    private Mediator mediator;
    public ColleagueTextField(String text, int columns) {   // 构造函数
        super(text, columns);
    }
    public void setMediator(Mediator mediator) {            // 保存Mediator
        this.mediator = mediator;
    }
    public void setColleagueEnabled(boolean enabled) {      // Mediator下达启用/禁用的指示
        setEnabled(enabled);
        setBackground(enabled ? Color.white : Color.lightGray);
    }
    public void textValueChanged(TextEvent e) {             // 当文字发生变化时通知Mediator
        mediator.colleagueChanged();
    }
}
