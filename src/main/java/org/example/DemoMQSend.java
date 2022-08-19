package org.example;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class DemoMQSend {

    // RabbitMQ Exchange and Rounting and Queue Setting
    public final static String EXCHANGE_NAME = "sample";
    public final static String ROUTING_KEY = "sample.routingkey.#";
    public final static String QUEUE_NAME = "sample.queue";

    public void Send(AgvEquipInfo agvEquipInfo) throws IOException, TimeoutException, InterruptedException {
        RabbitmqClient client = new RabbitmqClient();
        Channel channel = client.getChannel();

        // Queue Setting
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);

        String message = agvEquipInfo.toString();
        channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY, null, message.getBytes());
        System.out.println(" [x] Set '" + message + "'");
        Thread.sleep(10);

        client.close();
    }
}
