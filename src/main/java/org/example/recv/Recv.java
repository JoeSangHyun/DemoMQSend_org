package org.example.recv;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class Recv {
    private final static String QUEUE_NAME = "sample.queue";
//    private final static String QUEUE_NAME = "testQueue";

    public static void main(String[] argv) throws Exception {

        Map<String,Object> map = new HashMap<String,Object>();
        map.put("x-expires",5000);

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        factory.setPort(5672);
        factory.setUsername("Administrator");
        factory.setPassword("Passw0rd");
        factory.setVirtualHost("test");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        // channel.queueDeclare(QUEUE_NAME, false, false, true, null);
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);
        //channel.queueDeclare(QUEUE_NAME, false, false, true, map);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
            System.out.println(" [x] Received '" + message + "'");
        };
        channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });

        // Consumer 쪽 Autodelete 설정, Consumer 가 Data 소비후 , 접속이 끊기면 queue 삭제함.
        // 삭제된 큐 를 생성후 Exchange 의 routingkey 등록
        channel.queueBind(QUEUE_NAME,"sample","sample.routingkey.# ",null);
    }
}
