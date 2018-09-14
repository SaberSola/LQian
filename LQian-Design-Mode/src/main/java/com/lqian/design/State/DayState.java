package com.lqian.design.State;

public class DayState implements State {

    private static DayState singleton = new DayState();
    private DayState() {                                // 构造函数的可见性是private
    }

    public static State getInstance() {                 // 获取唯一实例
        return singleton;
    }


    @Override
    public void doClock(Context context, int hour) {

        if (9 < hour && hour < 17){
            context.changeState(NightState.getInstance());
        }
    }

    @Override
    public void doUse(Context context) {
        context.recordLog("使用金库(白天)");
    }

    @Override
    public void doAlarm(Context context) {
        context.callSecurityCenter("按下警铃(白天)");
    }

    @Override
    public void doPhone(Context context) {
        context.callSecurityCenter("正常通话(白天)");
    }
    public String toString() {                          // 显示表示类的文字
        return "[白天]";
    }
}
