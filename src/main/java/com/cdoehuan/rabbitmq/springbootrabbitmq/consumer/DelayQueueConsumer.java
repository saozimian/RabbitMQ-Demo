package com.cdoehuan.rabbitmq.springbootrabbitmq.consumer;

import com.cdoehuan.rabbitmq.springbootrabbitmq.config.DelayedQueueConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Author: ZhangHuan created on 2022/2/28 20:31
 * @Email: codehuan@163.com
 * @Version: 1.0
 * @Description: 消费者 基于插件的延迟消息
 */
@Slf4j
@Component
public class DelayQueueConsumer {

    // 接收消息
    @RabbitListener(queues = DelayedQueueConfig.DELAYED_QUEUE_NAME)
    public void receiveDelayQueue(Message message) {
        String msg = new String(message.getBody());
        log.info("当前时间:{},收到延迟队列的消息:{}", new Date(), msg);
    }
}
