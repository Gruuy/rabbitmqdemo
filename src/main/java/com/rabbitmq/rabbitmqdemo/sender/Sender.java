package com.rabbitmq.rabbitmqdemo.sender;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Sender {
    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send(){
        String msg="hello world!";
        System.out.println("Sender:" +msg);
        rabbitTemplate.convertAndSend("hello",msg);
    }
}
