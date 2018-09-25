package com.abstractDemo;

public class BmessageHandler extends BaseMessageHandler {


    @Override
    public void handler(Object message) {
        BMessage ms = (BMessage)message;
        System.out.println(ms.getSex());
    }
}
