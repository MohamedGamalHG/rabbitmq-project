package com.example.rabbitmq_project.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FanOutExchangeConfig {

    @Value("${rabbitmq.fan-queue1}")
    private String queue1;
    @Value("${rabbitmq.fan-queue2}")
    private String queue2;
    @Value("${rabbitmq.fan-queue3}")
    private String queue3;

    @Value("${rabbitmq.fan-exchange}")
    private String exchange;


    @Bean
    Queue createFanQueue1(){
        return new Queue(queue1,true);
    }

    @Bean
    Queue createFanQueue2(){
        return new Queue(queue2,true);
    }

    @Bean
    Queue createFanQueue3(){
        return new Queue(queue3,true);
    }

    @Bean
    FanoutExchange fanExchange(){
        return new FanoutExchange(exchange,true,false);
    }

    @Bean
    Binding bindingFanOut1(){
        return BindingBuilder.bind(createFanQueue1()).to(fanExchange());
    }

    @Bean
    Binding bindingFanOut2(){
        return BindingBuilder.bind(createFanQueue2()).to(fanExchange());
    }

    @Bean
    Binding bindingFanOut3(){
        return BindingBuilder.bind(createFanQueue3()).to(fanExchange());
    }
    @Bean
    AmqpTemplate fanOutTemplate(ConnectionFactory connectionFactory, MessageConverter messageConverter){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter);
        rabbitTemplate.setExchange(exchange);
        return rabbitTemplate;
    }

}
