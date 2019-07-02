package com.rabbitmq.rabbitmqdemo.sender;

import com.rabbitmq.rabbitmqdemo.RabbitMQConfig;
import com.rabbitmq.rabbitmqdemo.entity.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class WaitQueueSender {

    Logger log= LoggerFactory.getLogger(WaitQueueSender.class);

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void sendDelay(Order order) {
        log.info("【订单生成时间】" + new Date().toString() +"【5s后检查订单是否已经支付】" + order.toString() );
        this.amqpTemplate.convertAndSend(RabbitMQConfig.waitdieQueueExchangeName, RabbitMQConfig.waitdieQueueLabel, order, message -> {
            // 如果配置了 params.put("x-message-ttl", 5 * 1000); 那么这一句也可以省略,具体根据业务需要是声明 Queue 的时候就指定好延迟时间还是在发送自己控制时间
            message.getMessageProperties().setExpiration(1 * 1000 * 5 + "");
            return message;
        });
    }
}
