package com.cdoehuan.rabbitmq.springbootrabbitmq.consumer;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Author: ZhangHuan created on 2022/2/27 22:58
 * @Email: codehuan@163.com
 * @Version: 1.0
 * @Description: 队列TTL 消费者
 */
@Slf4j
@Component
public class DeadLetterQueueConsumer {

    // 接收消息
    @RabbitListener(queues = "QD")
    public void receiveD(Message message) {
        String msg = new String(message.getBody());
        log.info("当前时间:{},收到死信队列的消息:{}", new Date(), msg);

    }
}
