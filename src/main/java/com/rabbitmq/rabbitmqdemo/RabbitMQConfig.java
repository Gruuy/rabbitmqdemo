package com.rabbitmq.rabbitmqdemo;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitMQConfig {



    /** 定义队列 */
    @Bean
    public Queue helloQueue(){
        return new Queue("hello");
    }

    /** 定义等待死亡的队列 */
    public static final String waitdieQueueName="wait.order.queue";
    /** 定义转发到等待死亡队列的交换机 */
    public static final String waitdieQueueExchangeName="wait.order.exchange";
    /** 定义转发时附带的标记 */
    public static final String waitdieQueueLabel="wait.order";

    /** 定义死了之后进入的队列 */
    public static final String wasdieQueueName="die.order.queue";
    /** 定义死了之后进入的队列的交换机 */
    public static final String wasdieQueueExchangeName="die.order.exchange";
    /** 定义死了之后转发时附带的标记 */
    public static final String wasdieQueueLabel="die.order";


    /** 定义等死的队列，死了之后转发的交换机，以及附带的key */
    @Bean
    public Queue waitDieQueue(){
        Map<String,Object> map=new HashMap<>();
        map.put("x-dead-letter-exchange",wasdieQueueExchangeName);
        map.put("x-dead-letter-routing-key",wasdieQueueLabel);
        return new Queue(waitdieQueueName,true,false,false,map);
    }

    /** 定义死信队列 */
    @Bean
    public Queue wasDieQueue(){
        return new Queue(wasdieQueueName,true);
    }

    /** 定义转发到死信队列的直连交换机 */
    @Bean
    public DirectExchange wasDieExchange(){
        return new DirectExchange(wasdieQueueExchangeName);
    }

    /** 定义转发到等死队列的直连交换机 */
    @Bean
    public DirectExchange waitDieExchange(){
        return new DirectExchange(waitdieQueueExchangeName);
    }

    /** 绑定等死队列与转发的交换机 */
    @Bean
    public Binding waitDieQueueBinding(){
        return BindingBuilder.bind(waitDieQueue()).to(waitDieExchange()).with(waitdieQueueLabel);
    }

    /** 绑定死信队列与转发的交换机 */
    @Bean
    public Binding wasDieQueueBinding(){
        return BindingBuilder.bind(wasDieQueue()).to(wasDieExchange()).with(wasdieQueueLabel);
    }
}
