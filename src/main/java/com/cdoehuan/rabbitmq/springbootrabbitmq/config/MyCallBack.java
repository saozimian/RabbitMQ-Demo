package com.cdoehuan.rabbitmq.springbootrabbitmq.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @Author: ZhangHuan created on 2022/2/28 21:34
 * @Email: codehuan@163.com
 * @Version: 1.0
 * @Description: 当发送错误时的回调类
 */
@Slf4j
@Component
public class MyCallBack implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnsCallback {

    @Resource
    private RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void init() {
        // 注入
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.setReturnsCallback(this);
    }

    /**
     * 交换机确认回调方法
     * 1.发消息 交换机接收到了 回调
     * 1.1 correlationData 保存回调消息的ID及相关信息
     * 1.2 交换机接收到了消息 ack = true
     * 1.3 cause =  null
     * 2.发消息 交换机接收消息失败了 回调
     * 2.1 correlationData 保存回调消息的ID及相关信息
     * 2.2 交换机接收到了消息 ack = false
     * 1.3 cause =  失败的原因
     *
     * @param correlationData 消息体
     * @param ack             是否成功
     * @param cause           原因
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        String id = correlationData != null ? correlationData.getId() : "";
        if (ack) {
            log.info("交换机接收到Id为:{}的消息", id);
        } else {
            log.error("交换机未接收到Id为:{}的消息,由于原因:{}", id, cause);
        }
    }

    //可以在当消息传递过程中不可达目的地时将消息返回给生产者。
    @Override
    public void returnedMessage(ReturnedMessage returned) {

        log.error("消息{},被交换机{}退回,退回原因:{},路由{},",
                new String(returned.getMessage().getBody()),
                returned.getExchange(),
                returned.getReplyText(),
                returned.getRoutingKey());
    }
}
