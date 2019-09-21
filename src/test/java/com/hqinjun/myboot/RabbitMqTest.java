package com.hqinjun.myboot;

import com.hqinjun.myboot.config.RabbitMqConfig;
import com.hqinjun.myboot.rabbit.MsgProducer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitMqTest {
    @Autowired
    RabbitTemplate rabbitTemplate ;

    @Autowired
    AmqpAdmin amqpAdmin;

    @Autowired
    MsgProducer producer;

//    @Test
//    public void creatMq(){
//        amqpAdmin.declareExchange(new DirectExchange(RabbitMqConfig.EXCHANGE_A));
//        //创建队列（如果存在同名，则不创建）
//        amqpAdmin.declareQueue(new Queue(RabbitMqConfig.QUEUE_A,true));
//        //创建绑定规则   new Binding(目的地，目的地类型，交换器名字，路由件，参数头)
//        amqpAdmin.declareBinding(new Binding(RabbitMqConfig.QUEUE_A, Binding.DestinationType.QUEUE,RabbitMqConfig.EXCHANGE_A,"rabbit.test",null));
//        //删除队列
//        //amqpAdmin.deleteQueue("amqpadmin.queue");
//    }

    @Test
    public void testmq(){
//        CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());
//        String content = "rabbit mq test A !";
//        //把消息放入ROUTINGKEY_A对应的队列当中去，对应的是队列A
//        rabbitTemplate.convertAndSend(RabbitMqConfig.EXCHANGE_A, RabbitMqConfig.ROUTINGKEY_A, content, correlationId);
        producer.sendMsg("rabbit test A2");
    }

}
