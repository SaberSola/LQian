package com.zl.lqian.ConditionBean;

import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;

public class Zl extends NotificationBroadcasterSupport implements ZlMBean {

    private int seq = 0;


    /*
     * 必需继承NotificationBroadcasterSupport
     * 此类只有一个hi方法，方法只有两句：创建一个Notification消息包，然后将包发出去
     * 如果你还要在消息包上附加其他数据，Notification还有一个setUserData方法可供使用
     */
    @Override
    public void hi() {
        Notification notification = new Notification(
                "zl.hi",//给这个Notification起个名称
                this,//由谁发出的Notification
                ++seq,//一系列通知中的序列号，可以设置任意数值
                System.currentTimeMillis(),//发出时间
                "zl"//发出信息的消息文本
        );
        sendNotification(notification);

    }
}
