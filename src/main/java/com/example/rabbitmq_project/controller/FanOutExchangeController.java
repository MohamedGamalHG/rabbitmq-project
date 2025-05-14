package com.example.rabbitmq_project.controller;

import com.example.rabbitmq_project.model.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/fanOut-exchange")
@RequiredArgsConstructor
public class FanOutExchangeController {

    // the defaultQueue is the name of bean that i created in DefaultExchangeConfig class then i should use the same name
    // because spring will automatically take the name of method as name of bean
    private final AmqpTemplate fanOutTemplate;

    @GetMapping("/message")
    public void sentMessage(){
        Message message = new Message("default", LocalDateTime.now());
        fanOutTemplate.convertAndSend(message);
    }
}
