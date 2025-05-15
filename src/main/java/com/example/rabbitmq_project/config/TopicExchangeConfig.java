package com.example.rabbitmq_project.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TopicExchangeConfig {

    @Value("${rabbitmq.topic-queue1}")
    private String queue1;
    @Value("${rabbitmq.topic-queue2}")
    private String queue2;
    @Value("${rabbitmq.topic-queue3}")
    private String queue3;

    @Value("${rabbitmq.topic-exchange}")
    private String exchange;

    @Value("${rabbitmq.topic-binding1}")
    private String bind1;
    @Value("${rabbitmq.topic-binding2}")
    private String bind2;
    @Value("${rabbitmq.topic-binding3}")
    private String bind3;

    @Bean
    Queue createTopicQueue1(){
        return new Queue(queue1,true);
    }

    @Bean
    Queue createTopicQueue2(){
        return new Queue(queue2,true);
    }

    @Bean
    Queue createTopicQueue3(){
        return new Queue(queue3,true);
    }

    @Bean
    TopicExchange topicExchange(){
        return new TopicExchange(exchange,true,false);
    }

    @Bean
    Binding topic_binding1(){
        return BindingBuilder.bind(createTopicQueue1()).to(topicExchange()).with(bind1);
    }

    @Bean
    Binding topic_binding2(){
        return BindingBuilder.bind(createTopicQueue2()).to(topicExchange()).with(bind2);
    }

    @Bean
    Binding topic_binding3(){
        return BindingBuilder.bind(createTopicQueue3()).to(topicExchange()).with(bind3);
    }


    @Bean
    AmqpTemplate topicTemplate(ConnectionFactory connectionFactory, MessageConverter messageConverter){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter);
        rabbitTemplate.setExchange(exchange);
        return rabbitTemplate;
    }

}
