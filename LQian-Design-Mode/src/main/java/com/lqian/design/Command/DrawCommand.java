package com.lqian.design.Command;

import java.awt.*;

public class DrawCommand implements Command {

    // 绘制对象
    protected Drawable drawable;
    // 绘制位置
    private Point position;

    // 构造函数
    public DrawCommand(Drawable drawable, Point position) {
        this.drawable = drawable;
        this.position = position;
    }

    @Override
    public void execute() {
        drawable.draw(position.x, position.y);
    }
}
