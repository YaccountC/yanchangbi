package com.yupi.springbootinit.mq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

public class TopicConsumer {

  private static final String EXCHANGE_NAME = "topic_logs";

  public static void main(String[] argv) throws Exception {
    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost("localhost");
    Connection connection = factory.newConnection();
    Channel channel = connection.createChannel();

    channel.exchangeDeclare(EXCHANGE_NAME, "topic");
    String queueName = "qian";
    channel.queueDeclare(queueName,true,false,false,null);
    channel.queueBind(queueName,EXCHANGE_NAME,"#.前端.#");

      String queueName2 = "hou";
      channel.queueDeclare(queueName2,true,false,false,null);
      channel.queueBind(queueName2,EXCHANGE_NAME,"#.后端.#");

    System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

    DeliverCallback deliverCallback = (consumerTag, delivery) -> {
        String message = new String(delivery.getBody(), "UTF-8");
        System.out.println(" [qian] Received '" +
            delivery.getEnvelope().getRoutingKey() + "':'" + message + "'");
    };
      DeliverCallback deliverCallback2 = (consumerTag, delivery) -> {
          String message = new String(delivery.getBody(), "UTF-8");
          System.out.println(" [hou] Received '" +
                  delivery.getEnvelope().getRoutingKey() + "':'" + message + "'");
      };
    channel.basicConsume(queueName, true, deliverCallback, consumerTag -> { });
      channel.basicConsume(queueName2, true, deliverCallback2, consumerTag -> { });
  }
}