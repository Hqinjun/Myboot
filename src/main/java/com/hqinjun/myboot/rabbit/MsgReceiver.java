package com.hqinjun.myboot.rabbit;

import com.hqinjun.myboot.config.RabbitMqConfig;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
//@RabbitListener(queues = RabbitMqConfig.QUEUE_A)
public class MsgReceiver {
//    @RabbitHandler

    @RabbitListener(queues = RabbitMqConfig.QUEUE_A)
    public void process(String content) {
//        logger.info("接收处理队列A当中的消息： " + content);
        System.out.println("接收处理队列A当中的消息:" + content);
    }

}
