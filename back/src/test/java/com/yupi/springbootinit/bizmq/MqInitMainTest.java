package com.yupi.springbootinit.bizmq;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MqInitMainTest {

    @Resource
    private MyMessageProducer myMessageProducer;
    @Test
    void main() {
        myMessageProducer.sendMessage("code_exchange","my_routingKey","hello");
    }
}