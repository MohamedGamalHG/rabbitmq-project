package com.example.rabbitmq_project.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/*
* I Am not Understate in this exchange and i see that it not important
* */
@Configuration
public class HeaderExchangeConfig {

    @Value("${rabbitmq.header-queue1}")
    private String queue1;
    @Value("${rabbitmq.header-queue2}")
    private String queue2;
    @Value("${rabbitmq.header-queue3}")
    private String queue3;

    @Value("${rabbitmq.header-exchange}")
    private String exchange;


    @Bean
    Queue createHeaderQueue1(){
        return new Queue(queue1,true);
    }

    @Bean
    Queue createHeaderQueue2(){
        return new Queue(queue2,true);
    }

    @Bean
    Queue createHeaderQueue3(){
        return new Queue(queue3,true);
    }

    @Bean
    HeadersExchange headerExchange(){
        return new HeadersExchange(exchange,true,false);
    }

    @Bean
    Binding bindingHeader1(){
        return BindingBuilder.bind(createHeaderQueue1()).to(headerExchange()).whereAny("test","deployt").exist();
    }

    @Bean
    Binding bindingHeader2(){
        //whereAny("test") => if this exactly match one of this
        return BindingBuilder.bind(createHeaderQueue2()).to(headerExchange()).whereAny("test").exist();
    }

    @Bean
    Binding bindingHeader3(){
        //whereAll("test","deploy","debug") => if all of this exactly match
        return BindingBuilder.bind(createHeaderQueue3()).to(headerExchange()).whereAll("test","deploy","debug").exist();
    }
    @Bean
    AmqpTemplate headerTemplate(ConnectionFactory connectionFactory, MessageConverter messageConverter){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter);
        rabbitTemplate.setExchange(exchange);
        return rabbitTemplate;
    }

}
