package com.example.rabbitmq_project.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.connection.AbstractConnectionFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class RabbitMqConfig {

    private final RabbitConfiguration rabbitConfiguration;
    @Bean
    public ConnectionFactory connectionFactory(){
        AbstractConnectionFactory factory = new CachingConnectionFactory();
        factory.setHost(rabbitConfiguration.getHost());
        factory.setPort(rabbitConfiguration.getPort());
        factory.setUsername(rabbitConfiguration.getUsername());
        factory.setPassword(rabbitConfiguration.getPassword());
        factory.setVirtualHost(rabbitConfiguration.getVirtualHost());

        return factory;
    }

    @Bean
    // this method is response for convert the message from come from server and out to server from app to json
    public MessageConverter messageConverter(){
        ObjectMapper objectMapper = new ObjectMapper().findAndRegisterModules();
        return new Jackson2JsonMessageConverter(objectMapper);
    }
}
