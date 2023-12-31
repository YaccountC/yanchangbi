package com.yupi.springbootinit.mq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

public class FanoutConsumer {
  private static final String EXCHANGE_NAME = "logs";

  public static void main(String[] argv) throws Exception {
    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost("localhost");
    Connection connection = factory.newConnection();
    Channel channel1 = connection.createChannel();
    Channel channel2 = connection.createChannel();
    channel1.exchangeDeclare(EXCHANGE_NAME,"fanout");
    String queueName = "__a__";
    channel1.queueDeclare(queueName,true,false,false,null);
    channel1.queueBind(queueName,EXCHANGE_NAME,"");

    String queueName2 = "__b__";
    channel1.queueDeclare(queueName2,true,false,false,null);
    channel1.queueBind(queueName2,EXCHANGE_NAME,"");

    System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

    DeliverCallback deliverCallback1 = (consumerTag, delivery) -> {
        String message = new String(delivery.getBody(), "UTF-8");
        System.out.println(" [a] Received '" + message + "'");
    };

    DeliverCallback deliverCallback2 = (consumerTag, delivery) -> {
      String message = new String(delivery.getBody(), "UTF-8");
      System.out.println(" [b] Received '" + message + "'");
    };
    channel1.basicConsume(queueName, true, deliverCallback1, consumerTag -> { });
    channel2.basicConsume(queueName, true, deliverCallback2, consumerTag -> { });
  }
}