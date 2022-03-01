package com.cdoehuan.rabbitmq.springbootrabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: ZhangHuan created on 2022/2/28 21:01
 * @Email: codehuan@163.com
 * @Version: 1.0
 * @Description: 配置类 发布确认（高级）
 */
@Configuration
public class ConfirmConfig {

    // 队列
    public static final String CONFIRM_QUEUE_NAME = "confirm_queue";
    // 交换机
    public static final String CONFIRM_EXCHANGE_NAME = "confirm_exchange";
    // routingKey
    public static final String CONFIRM_ROUTING_KEY = "key1";

    // 声明队列
    @Bean("confirmQueue")
    public Queue confirmQueue() {
        return QueueBuilder.durable(CONFIRM_QUEUE_NAME).build();
    }

    // 声明交换机
    @Bean("confirmExchange")
    public DirectExchange confirmExchange() {
        return new DirectExchange(CONFIRM_EXCHANGE_NAME);
    }

    //绑定
    @Bean
    public Binding confirmQueueBindingConfirmExchange(@Qualifier("confirmQueue") Queue confirmQueue,
                                                      @Qualifier("confirmExchange") DirectExchange confirmExchange) {

        return BindingBuilder.bind(confirmQueue).to(confirmExchange).with(CONFIRM_ROUTING_KEY);
    }
}
