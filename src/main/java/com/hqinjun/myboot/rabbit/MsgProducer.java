package com.hqinjun.myboot.rabbit;

import com.hqinjun.myboot.config.RabbitMqConfig;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class MsgProducer implements RabbitTemplate.ConfirmCallback {


    private RabbitTemplate rabbitTemplate;
    @Autowired
    public MsgProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        rabbitTemplate.setConfirmCallback(this); //rabbitTemplate如果为单例的话，那回调就是最后设置的内容
    }

    public void sendMsg(String content) {
        CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());
        //把消息放入ROUTINGKEY_A对应的队列当中去，对应的是队列A
        rabbitTemplate.convertAndSend(RabbitMqConfig.EXCHANGE_A, RabbitMqConfig.ROUTINGKEY_A, content, correlationId);
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
//        logger.info(" 回调id:" + correlationData);
        System.out.println(" 回调id:" + correlationData);
        if (ack) {
//            logger.info("消息成功消费");
            System.out.println("消息成功消费");
        } else {
//            logger.info("消息消费失败:" + cause);
            System.out.println("消息消费失败:" + cause);
        }

    }
}
