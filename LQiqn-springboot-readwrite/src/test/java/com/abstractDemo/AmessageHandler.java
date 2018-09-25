package com.abstractDemo;

public class AmessageHandler extends  BaseMessageHandler {


    @Override
    public void handler(Object message) {
        Amessage amessage = (Amessage)message;
        System.out.println(amessage.getAge());
    }
}
