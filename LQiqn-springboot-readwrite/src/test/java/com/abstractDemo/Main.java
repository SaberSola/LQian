package com.abstractDemo;

public class Main {

    public static  void main(String[] args){


        Amessage amessage = new Amessage();
        amessage.setAge(19);
        amessage.setContent("dfafaf");
        amessage.setName("zl");
        BaseMessageHandler messageHandler = new AmessageHandler();
        messageHandler.handler(amessage);

        BMessage message = new BMessage();
        message.setSex("男");
        message.setContent("dfafaf");
        message.setName("zl");
        messageHandler = new BmessageHandler();
        messageHandler.handler(message);
    }
}
