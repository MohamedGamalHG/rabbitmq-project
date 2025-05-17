package com.example.rabbitmq_project.controller;

import com.example.rabbitmq_project.model.Message;
import com.example.rabbitmq_project.service.ConsumerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ConsumerController {

    private final ConsumerService  consumerService;

    @GetMapping("/messages/{queueName}")
    public List<Message> getAllMessage(@PathVariable String queueName){
        return consumerService.getMessages(queueName);
    }
}
