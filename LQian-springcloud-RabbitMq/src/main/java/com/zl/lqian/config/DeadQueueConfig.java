package com.zl.lqian.config;


import com.rabbitmq.client.Channel;
import com.zl.lqian.utils.MQConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * 定义死信队列
 *
 */
@Configuration
public class DeadQueueConfig {


    @Component
    public class DeadLetterMessageListener implements ChannelAwareMessageListener {
        private Logger logger = LoggerFactory.getLogger(DeadLetterMessageListener.class);

        @Autowired
        private RedisTemplate<String, Object> redisTemplate;
        @Override
        public void onMessage(Message message, Channel channel) throws Exception {

            MessageProperties  messageProperties = message.getMessageProperties();
            //消息体
            String messageBody = String.valueOf(message.getBody());

            logger.warn("dead letter message：{} | tag：{}", messageBody, message.getMessageProperties().getDeliveryTag());
            //手动确认
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);

            redisTemplate.opsForHash().delete(MQConstants.MQ_CONSUMER_RETRY_COUNT_KEY, messageProperties.getMessageId());
        }
    }

    /**
     * 声明死信 交换机
     */
    @Bean
    public DirectExchange dlxExchange() {
        //声明死信交换机的名称
        return new DirectExchange(MQConstants.DLX_EXCHANGE);
    }

    /**
     * durable : 是否持久化
     * exclusive : 仅仅是创建者持有的私有队列 ，断开后自动删除
     * autoDelete : 当所有消费者都断开连接后是否自动删除队列
     * 声明死信队列
     */
    @Bean
    public Queue dlxQueue(){
        return new Queue(MQConstants.DLX_QUEUE,true,false,false);
    }

    /**
     *
     * 绑定队列交换机
     */
    @Bean
    public Binding dlxBinding(){
        return BindingBuilder.bind(dlxQueue()).to(dlxExchange())
                .with(MQConstants.DLX_ROUTING_KEY);
    }

    /**
     *
     * 死信队列的监听
     *
     */
    @Bean
    public SimpleMessageListenerContainer deadLetterListenerContainer(ConnectionFactory connectionFactory,
                                                                      DeadLetterMessageListener deadLetterMessageListener) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
        container.setQueues(dlxQueue());
        container.setExposeListenerChannel(true);
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        container.setMessageListener(deadLetterMessageListener);
        /** 设置消费者能处理消息的最大个数 */
        container.setPrefetchCount(100);
        return container;
    }
}
