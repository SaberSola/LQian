package com.zl.lqian.controller;

import com.zl.lqian.service.RabbitSender;
import com.zl.lqian.utils.MQConstants;
import com.zl.lqian.utils.RabbitMetaMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class SendContoller {


    @Autowired
    private RabbitSender rabbitSender;

    Logger logger = LoggerFactory.getLogger(getClass());


    @GetMapping("/testmq")
    @Transactional
    public String testMessage() throws Exception {

        //封装消息体
        RabbitMetaMessage rabbitMetaMessage = new RabbitMetaMessage();
        /**设置交换机 */
        rabbitMetaMessage.setExchange(MQConstants.BUSINESS_EXCHANGE);
        /**指定routing key */
        rabbitMetaMessage.setRoutingKey(MQConstants.BUSINESS_KEY);
        /** 设置需要传递的消息体,可以是任意对象 */
        rabbitMetaMessage.setPayload("the message you want to send");

        /** 发送消息 */
        rabbitSender.send(rabbitMetaMessage);
        return "success";
    }
}
