package com.cdoehuan.rabbitmq.springbootrabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.CustomExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: ZhangHuan created on 2022/2/28 20:10
 * @Email: codehuan@163.com
 * @Version: 1.0
 * @Description:
 */
@Configuration
public class DelayedQueueConfig {

    // 队列
    public static final String DELAYED_QUEUE_NAME = "delayed.queue";
    // 交换机
    public static final String DELAYED_EXCHANGE_NAME = "delayed.exchange";
    // routingKey
    public static final String DELAYED_ROUTING_KEY = "delayed.routingkey";


    // 声明队列
    @Bean
    public Queue delayedQueue() {
        return new Queue(DELAYED_QUEUE_NAME);
    }

    // 声明交换机
    @Bean
    public CustomExchange delayedExchange() {

        Map<String, Object> arguments = new HashMap<>();
        arguments.put("x-delayed-type", "direct");
        /**
         * 1.交换机名称
         * 2.交换机类型
         * 3.是否持久化
         * 4.是否自动删除
         * 5.其他参数
         */
        return new CustomExchange(DELAYED_EXCHANGE_NAME, "x-delayed-message", false, false, arguments);
    }

    // 绑定
    @Bean
    public Binding delayedQueueBindingDelayedExchange(
            @Qualifier("delayedQueue") Queue delayedQueue,
            @Qualifier("delayedExchange") CustomExchange delayedExchange) {
        return BindingBuilder.bind(delayedQueue).to(delayedExchange).with(DELAYED_ROUTING_KEY).noargs();
    }

}
