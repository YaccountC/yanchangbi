package com.yupi.springbootinit.bizmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import static com.yupi.springbootinit.bizmq.BiMqConstant.*;

public class BiInitMain {


    public static void main(String[] args){
        try{
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("localhost");
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();

            channel.exchangeDeclare(BI_EXCHANGE_NAME,"direct");
            channel.queueDeclare(BI_QUEUE_NAME,true,false,false,null);
            channel.queueBind(BI_QUEUE_NAME,BI_EXCHANGE_NAME,BI_ROUTING_KEY);
        }catch (Exception e){

        }
    }
}
