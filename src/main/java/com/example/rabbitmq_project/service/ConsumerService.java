package com.example.rabbitmq_project.service;

import com.example.rabbitmq_project.model.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class ConsumerService {
    private final RabbitTemplate rabbitTemplate;
    private final AmqpAdmin amqpAdmin;

    public int getCountMessage(String queueName){
        /*
        * amqpAdmin.getQueueProperties(queueName) => Tell me how many messages and consumers are currently in this queue
        *
        * the Properties here it returns object (basically a Map) with keys like:
            1- QUEUE_MESSAGE_COUNT: number of messages
            2- QUEUE_CONSUMER_COUNT: number of consumers
            3- And possibly other metadata.
        * */
        Properties properties = amqpAdmin.getQueueProperties(queueName);
        return (int)properties.get(RabbitAdmin.QUEUE_MESSAGE_COUNT);
    }
    public List<Message> getMessages(String queueName){
        int count = getCountMessage(queueName);

        /*
        * rabbitTemplate.receiveAndConvert(queueName) => By default, this method automatically acknowledges the message
        *                            which means RabbitMQ removes it from the queue immediately after it’s received.
        * */
        List<Message> messageList = IntStream.range(0,count)
                .mapToObj(item -> (Message)rabbitTemplate.receiveAndConvert(queueName))
                .toList();

        for (int i=0;i<5;i++)
        {
            if (i == 4)
                throw new RuntimeException("Something Wrong");
        }
        return messageList;
    }

}
