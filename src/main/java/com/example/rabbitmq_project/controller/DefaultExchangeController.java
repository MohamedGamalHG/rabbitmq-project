package com.example.rabbitmq_project.controller;

import com.example.rabbitmq_project.model.Message;
import lombok.AllArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/default-exchange")
@AllArgsConstructor
public class DefaultExchangeController {

    // the defaultQueue is the name of bean that i created in DefaultExchangeConfig class then i should use the same name
    // because spring will automatically take the name of method as name of bean
    private final AmqpTemplate defaultQueue;
    @GetMapping("/message")
    public void sentMessage(){
        Message message = new Message("default", LocalDateTime.now());
        defaultQueue.convertAndSend(message);
    }
}
