package com.miskolc.sound_processor;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

public class SendLevelAlert {

    private final String queueName = "soundAlertQueue";
    private Channel channel;

    public SendLevelAlert() {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("queue-server");
        try {
            Connection connection = factory.newConnection();
            channel = connection.createChannel();
            channel.queueDeclare(queueName, false, false, false, null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (TimeoutException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendMessage() {
        try {
            channel.basicPublish("", queueName, null, "High noise alert: 5 high decibel readings detected.".getBytes());
            System.out.println(" [x] High noise alert: 5 high decibel readings detected.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
