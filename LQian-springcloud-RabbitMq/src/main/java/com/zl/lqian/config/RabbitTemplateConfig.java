package com.zl.lqian.config;

import com.github.rholder.retry.Retryer;
import com.github.rholder.retry.RetryerBuilder;
import com.github.rholder.retry.StopStrategies;
import com.github.rholder.retry.WaitStrategies;
import com.google.common.base.Predicates;
import com.zl.lqian.service.RabbitSender;
import com.zl.lqian.utils.MQConstants;
import com.zl.lqian.utils.RabbitMetaMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

@Configuration
@ComponentScan
public class RabbitTemplateConfig {

    private Logger LOGGER = LoggerFactory.getLogger(RabbitTemplateConfig.class);

    private static volatile boolean SUCESS_SEND = false;

    @Autowired
    RabbitSender rabbitSender;

    @Autowired
    RedisTemplate<String,Object> redisTemplate;



    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public RabbitTemplate customRabbitTemplate(ConnectionFactory connectionFactory){

        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        // mandatory 必须设置为true，ReturnCallback才会调用
        rabbitTemplate.setMandatory(true);
        //消息发送到交换机后，通过callback进行ack 进行确认
        rabbitTemplate.setConfirmCallback((correlationData,ack,cause)->{
            LOGGER.debug("回调为correlationData:{},ack:{},cause;{}",correlationData,ack,cause);
            String cacheKey = correlationData.getId();
            RabbitMetaMessage  metaMessage =  (RabbitMetaMessage)redisTemplate.opsForHash().get(MQConstants.MQ_PRODUCER_RETRY_KEY, cacheKey);
            //如果消息正确投递
            if (ack) {
                LOGGER.info("消息已正确投递到队列，correlationData:{}", correlationData);
                // 清除重发缓存
                redisTemplate.opsForHash().delete(MQConstants.MQ_PRODUCER_RETRY_KEY, cacheKey);
                SUCESS_SEND = true;
            }else {
                LOGGER.error("消息投递至交换机失败。correlationData:{}，原因：{}", correlationData, cause);
                //TODO 消息重发
                reSendMsg(cacheKey, metaMessage);
            }
        });
        //消息发送到RabbitMQ交换器，但无相应Exchange时触发此回掉：重发消息
        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey)->{
            String cacheKey = message.getMessageProperties().getMessageId();
            message.getClass().getName();
            LOGGER.error("消息投递至交换机失败，没有找到任何匹配的队列！message id:{},replyCode{},replyText:{},"
                    + "exchange:{},routingKey{}", cacheKey, replyCode, replyText, exchange, routingKey);
            RabbitMetaMessage metaMessage = (RabbitMetaMessage) redisTemplate.opsForHash().get(MQConstants.MQ_PRODUCER_RETRY_KEY, cacheKey);
            //重发消息
            reSendMsg(cacheKey, metaMessage);
        });
        System.out.println("RabbitTemplate实例化完成");
        return rabbitTemplate;
    }



    /**
     * 消息重发
     * @param msgId
     * @param rabbitMetaMessage
     */
    public void reSendMsg(String msgId, RabbitMetaMessage rabbitMetaMessage) {

        class  RetryThread implements Callable{

            String msgId;
            RabbitMetaMessage rabbitMetaMessage;

            public RetryThread(String msgId, RabbitMetaMessage rabbitMetaMessage) {
                this.msgId = msgId;
                this.rabbitMetaMessage = rabbitMetaMessage;
            }

            @Override
            public Object call() throws Exception {
                //如果发送成功，重发结束
                if (SUCESS_SEND)
                    return true;
                //重发消息
                rabbitSender.sendMsg(this.rabbitMetaMessage, this.msgId);
                return false;
            }
        }
        Retryer<Boolean> retryer = RetryerBuilder
                .<Boolean>newBuilder()
                //抛出runtime异常、checked异常时都会重试，但是抛出error不会重试。
                .retryIfException()
                .retryIfResult(Predicates.equalTo(false))
                //重试策略  100ms*2^n 最多10s
                .withWaitStrategy(WaitStrategies.exponentialWait(MQConstants.MUTIPLIER_TIME,
                        MQConstants.MAX_RETRY_TIME, TimeUnit.SECONDS))
                .withStopStrategy(StopStrategies.neverStop())
                .build();

        RetryThread reSendThread = new RetryThread(msgId, rabbitMetaMessage);
        LOGGER.info("重发消息，msgId:{}", msgId);
        try {
            retryer.call(reSendThread);
            //未发送成功，入死信队列
            if(!SUCESS_SEND)
                rabbitSender.sendMsgToDeadQueue((String)rabbitMetaMessage.getPayload());
        } catch (Exception e) {
            LOGGER.error("重发消息异常");
        }
    }

    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
        Jackson2JsonMessageConverter jsonMessageConverter = new Jackson2JsonMessageConverter();
        return jsonMessageConverter;
    }
}
