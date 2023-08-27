package com.yupi.springbootinit.bizmq;

import static com.yupi.springbootinit.bizmq.BiMqConstant.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class BiMessageProducer {

    @Resource
    private RabbitTemplate rabbitTemplate;


    public void sendMessage(String message){
        rabbitTemplate.convertAndSend(BI_EXCHANGE_NAME,BI_ROUTING_KEY,message);
    }
}
