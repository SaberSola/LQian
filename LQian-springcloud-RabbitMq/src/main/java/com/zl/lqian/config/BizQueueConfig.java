package com.zl.lqian.config;


import com.zl.lqian.consume.BizMessageListener;
import com.zl.lqian.utils.MQConstants;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;


/**
 * 业务消费的队列
 * 要是有多个队列 建议配置多割
 *
 */
@Configuration
public class BizQueueConfig {


    /**
     * 首先声明是用哪个交换机
     */
    @Bean
    public DirectExchange businessExchange(){

        return new DirectExchange(MQConstants.BUSINESS_EXCHANGE);
    }

    /**
     * 配置queue 队列以及一些参数
     * @return
     */
    @Bean
    public Queue bizQueue(){
        Map<String, Object> arguments = new HashMap<String, Object>();
        /*****配置死信队列************/
        arguments.put("x-dead-letter-exchange", MQConstants.DLX_EXCHANGE);
        arguments.put("x-dead-letter-routing-key", MQConstants.DLX_ROUTING_KEY);

        /*******设置消息的过期时间**********/
        //arguments.put("x-message-ttl", 60000);
        return new Queue(MQConstants.BUSINESS_QUEUE,true,false,false,arguments);
    }

    /**
     * 开始绑定 exchange routekey
     * @return
     */
    @Bean
    public Binding bizBinding() {
        return BindingBuilder.bind(bizQueue()).to(businessExchange())
                .with(MQConstants.BUSINESS_KEY);
    }


    /**
     *
     * 设置监听器
     */
    @Bean
    public SimpleMessageListenerContainer bizListenerContainer(ConnectionFactory connectionFactory,
                                                               BizMessageListener bizMessageListener) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
        container.setQueues(bizQueue());
        container.setExposeListenerChannel(true);
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        container.setMessageListener(bizMessageListener);
        /** 设置消费者能处理未应答消息的最大个数 */
        container.setPrefetchCount(10);
        return container;
    }

}
