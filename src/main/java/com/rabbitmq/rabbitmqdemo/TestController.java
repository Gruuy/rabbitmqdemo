package com.rabbitmq.rabbitmqdemo;

import com.rabbitmq.rabbitmqdemo.entity.Order;
import com.rabbitmq.rabbitmqdemo.sender.WaitQueueSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private WaitQueueSender waitQueueSender;

    @RequestMapping("/test")
    public void test(){
        Order order1 = new Order();
        order1.setOrderStatus(0);
        order1.setOrderId("123456");
        order1.setOrderName("小米6");

        Order order2 = new Order();
        order2.setOrderStatus(1);
        order2.setOrderId("456789");
        order2.setOrderName("小米8");

        waitQueueSender.sendDelay(order1);
        waitQueueSender.sendDelay(order2);
    }
}
