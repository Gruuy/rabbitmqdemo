package com.rabbitmq.rabbitmqdemo.listener;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "hello")
public class Listener {
    @RabbitHandler
    public void listen(String msg){
        System.out.println("Listen:"+msg );
    }
}
