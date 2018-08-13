package com.zl.lqian.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zl.lqian.utils.MQConstants;
import com.zl.lqian.utils.RabbitMetaMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RabbitSender {


    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    RabbitTemplate rabbitTemplate;

    Logger logger =  LoggerFactory.getLogger(RabbitSender.class);


    /**
     * 发送消息
     * @param rabbitMetaMessage
     * @return
     * @throws Exception
     */
    public String send(RabbitMetaMessage rabbitMetaMessage) throws Exception {

        final String msgId = UUID.randomUUID().toString();

        // 放缓存
        redisTemplate.opsForHash().put(MQConstants.MQ_PRODUCER_RETRY_KEY, msgId, rabbitMetaMessage);
        return sendMsg(rabbitMetaMessage, msgId);
    }

    public String sendMsg(RabbitMetaMessage rabbitMetaMessage,String msgId) throws Exception{

        MessagePostProcessor messagePostProcessor = message -> {
            message.getMessageProperties().setMessageId(msgId);
            // 设置消息持久化
            message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
            return message;
        };

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(rabbitMetaMessage.getPayload());
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setContentType("application/json");
        Message message = new Message(json.getBytes(),messageProperties);

        try {
            rabbitTemplate.convertAndSend(rabbitMetaMessage.getExchange(), rabbitMetaMessage.getRoutingKey(),
                    message, messagePostProcessor, new CorrelationData(msgId));

            logger.info("发送消息，消息ID:{}", msgId);
            return msgId;
        }catch (AmqpException e){
            throw new RuntimeException("发送RabbitMQ消息失败！", e);
        }
    }


    /**
     *
     * 发送到死信队列
     */
    public String sendMsgToDeadQueue(String msg) throws Exception{

        //产生一个消息对象
        RabbitMetaMessage message = new RabbitMetaMessage();
        //设置交换机
        message.setExchange(MQConstants.DLX_EXCHANGE);
        //设置路由
        message.setRoutingKey(MQConstants.DLX_ROUTING_KEY);
        //设置消息体
        message.setPayload(msg);
        //开始发送消息
        return send(message);
    }
}
