package com.example.rabbitmq_project.controller;

import com.example.rabbitmq_project.model.Message;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/direct-exchange")
@RequiredArgsConstructor
public class DirectExchangeController {

    // the defaultQueue is the name of bean that i created in DefaultExchangeConfig class then i should use the same name
    // because spring will automatically take the name of method as name of bean
    private final AmqpTemplate directTemplate;

    @Value("${rabbitmq.binding1}")
    private String bind1;
    @Value("${rabbitmq.binding2}")
    private String bind2;
    @Value("${rabbitmq.binding3}")
    private String bind3;

    @GetMapping("/message")
    public void sentMessage(@RequestParam int num){
        String key;
        if (num == 1) key = bind1;
        else if (num == 2) key = bind2;
        else if (num == 3) key = bind3;
        else throw  new RuntimeException("there is something wrong");
        Message message = new Message("default", LocalDateTime.now());
        directTemplate.convertAndSend(key,message);
    }
}
