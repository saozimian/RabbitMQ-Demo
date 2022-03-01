package com.cdoehuan.rabbitmq.springbootrabbitmq.consumer;

import com.cdoehuan.rabbitmq.springbootrabbitmq.config.ConfirmConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Author: ZhangHuan created on 2022/2/28 21:18
 * @Email: codehuan@163.com
 * @Version: 1.0
 * @Description: 消费者 接收消息
 */
@Slf4j
@Component
public class Consumer {

    @RabbitListener(queues = ConfirmConfig.CONFIRM_QUEUE_NAME)
    public void receiverConfirmMessage(Message message) {
        String msg = new String(message.getBody());
        log.info("接收到的confirm.queue消息为:{}", msg);
    }
}
