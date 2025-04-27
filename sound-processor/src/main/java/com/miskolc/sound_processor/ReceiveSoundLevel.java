package com.miskolc.sound_processor;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeoutException;

public class ReceiveSoundLevel {
    private final String queueName = "soundLevelQueue";
    private Channel channel;

    private final Queue<Integer> queue = new LinkedList<>();
    private final int maxSize = 5;
    private long lastTic = System.currentTimeMillis();


    public ReceiveSoundLevel() {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try {
            Connection connection = factory.newConnection();
            channel = connection.createChannel();
            channel.queueDeclare(queueName, false, false, false, null);

            SendLevelAlert sendLevelAlert = new SendLevelAlert();

            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String message = new String(delivery.getBody(), "UTF-8");
//                System.out.println(" [x] Received '" + message + "'");

                if (queue.size() > maxSize) {
                    queue.poll();
                }

                long currentTic = System.currentTimeMillis();
                long timeDiff = currentTic - lastTic;

                int soundLevel = Integer.parseInt(message);
                if (soundLevel>80) {
                    queue.add(soundLevel);
                }
                if (timeDiff > 30000 && queue.size() >= 5) {
                    System.out.println(" [x] Sound level: " + Collections.max(queue) + " dB");
                    System.out.println(" [x] Time since last message: " + currentTic + " ms");
                    lastTic = currentTic;
                    queue.clear();
                    sendLevelAlert.sendMessage();
                }
            };
            channel.basicConsume(queueName, true, deliverCallback, consumerTag -> { });
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (TimeoutException e) {
            throw new RuntimeException(e);
        }
    }
}
