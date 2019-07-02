package com.rabbitmq.rabbitmqdemo;

import com.rabbitmq.rabbitmqdemo.entity.Order;
import com.rabbitmq.rabbitmqdemo.sender.Sender;
import com.rabbitmq.rabbitmqdemo.sender.WaitQueueSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitmqdemoApplicationTests {
    @Autowired
    private Sender sender;
    @Autowired
    private WaitQueueSender waitQueueSender;
    @Test
    public void contextLoads() {
        sender.send();
    }

    @Test
    public void testWaitQueueSender(){

    }
}
