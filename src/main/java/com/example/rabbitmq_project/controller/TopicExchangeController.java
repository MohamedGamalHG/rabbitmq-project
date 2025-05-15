package com.example.rabbitmq_project.controller;

import com.example.rabbitmq_project.model.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/topic-exchange")
@RequiredArgsConstructor
public class TopicExchangeController {

    // the defaultQueue is the name of bean that i created in DefaultExchangeConfig class then i should use the same name
    // because spring will automatically take the name of method as name of bean
    private final AmqpTemplate topicTemplate;

    @Value("${rabbitmq.topic-binding1}")
    private String bind1;
    @Value("${rabbitmq.topic-binding2}")
    private String bind2;
    @Value("${rabbitmq.topic-binding3}")
    private String bind3;

    // /topic-exchange/message/e.e.t.q.r.y.q.a => it should put pattern like you make in yml file to match the pattern
    @GetMapping("/message/{key}")
    public void sentMessage(@PathVariable String key){
        Message message = new Message("default", LocalDateTime.now());
        topicTemplate.convertAndSend(key,message);
    }

//    @GetMapping("/message")
//    public void sentMessage(@RequestParam int num){
//        String key;
//        if (num == 1) key = bind1;
//        else if (num == 2) key = bind2;
//        else if (num == 3) key = bind3;
//        else throw  new RuntimeException("there is something wrong");
//        Message message = new Message("default", LocalDateTime.now());
//        topicTemplate.convertAndSend(key,message);
//    }
}
