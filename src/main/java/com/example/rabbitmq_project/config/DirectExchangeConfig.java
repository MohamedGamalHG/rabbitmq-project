package com.example.rabbitmq_project.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DirectExchangeConfig {

    @Value("${rabbitmq.queue1}")
    private String queue1;
    @Value("${rabbitmq.queue2}")
    private String queue2;
    @Value("${rabbitmq.queue3}")
    private String queue3;

    @Value("${rabbitmq.exchange}")
    private String exchange;

    @Value("${rabbitmq.binding1}")
    private String bind1;
    @Value("${rabbitmq.binding2}")
    private String bind2;
    @Value("${rabbitmq.binding3}")
    private String bind3;

    @Bean
    Queue createQueue1(){
        return new Queue(queue1,true);
    }

    @Bean
    Queue createQueue2(){
        return new Queue(queue2,true);
    }

    @Bean
    Queue createQueue3(){
        return new Queue(queue3,true);
    }

    @Bean
    DirectExchange directExchange(){
        return new DirectExchange(exchange,true,false);
    }

    @Bean
    Binding binding1(){
        return BindingBuilder.bind(createQueue1()).to(directExchange()).with(bind1);
    }

    @Bean
    Binding binding2(){
        return BindingBuilder.bind(createQueue2()).to(directExchange()).with(bind2);
    }

    @Bean
    Binding binding3(){
        return BindingBuilder.bind(createQueue3()).to(directExchange()).with(bind3);
    }


    @Bean
    AmqpTemplate directTemplate(ConnectionFactory connectionFactory, MessageConverter messageConverter){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter);
        rabbitTemplate.setExchange(exchange);
        return rabbitTemplate;
    }

}
