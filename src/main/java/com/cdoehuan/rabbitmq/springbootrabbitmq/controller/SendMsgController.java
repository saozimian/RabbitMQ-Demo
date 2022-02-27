package com.cdoehuan.rabbitmq.springbootrabbitmq.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @Author: ZhangHuan created on 2022/2/27 22:49
 * @Email: codehuan@163.com
 * @Version: 1.0
 * @Description: 发送延迟消息 10ms
 */
@Slf4j
@RestController
@RequestMapping("/ttl")
public class SendMsgController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/sendMsg/{message}")
    public void sendMsg(@PathVariable String message) {
        log.info("当前时间:{},发送一条消息给两个TTL队列:{}", new Date(), message);

        rabbitTemplate.convertAndSend("X", "XA", "消息来自ttl为10ms的队列！！" + message);
        rabbitTemplate.convertAndSend("X", "XB", "消息来自ttl为40ms的队列！！" + message);
    }
}
